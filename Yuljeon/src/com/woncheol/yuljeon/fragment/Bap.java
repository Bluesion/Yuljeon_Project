package com.woncheol.yuljeon.fragment;

import java.lang.ref.WeakReference;
import toast.library.meal.MealLibrary;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.woncheol.yuljeon.About;
import com.woncheol.yuljeon.R;
import com.woncheol.yuljeon.adapter.BapListViewAdapter;

@SuppressLint("HandlerLeak")
public class Bap extends Fragment {

	private BapListViewAdapter mAdapter;
	public static ListView mListView;
	private Handler mHandler;

	private String[] calender, morning, lunch, night;

	private SharedPreferences bapList;
	private SharedPreferences.Editor bapListeditor;

	private ProgressDialog mDialog;

	private final String noMessage = "네트워크 연결 상태가 좋지 않아 정보를 불러오지 못했습니다";
	private final String loadingList = "급식 정보를 받아오고 있습니다...";

	private boolean isSync = false;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		setHasOptionsMenu(true);
		View rootView = inflater.inflate(R.layout.fragment_bap, container, false);
		
		mListView = (ListView) rootView.findViewById(R.id.mBapList);
		mAdapter = new BapListViewAdapter(getActivity());
		mListView.setAdapter(mAdapter);

		bapList = getActivity().getSharedPreferences("bapList", 0);
		bapListeditor = bapList.edit();

		mHandler = new MyHandler(this);

		if (bapList.getBoolean("checker", false)) {
			calender = restore("calender");
			morning = restore("morning");
			lunch = restore("lunch");
			night = restore("night");

			mHandler.sendEmptyMessage(1);
		} else {
			if (isNetwork()) {
				calender = new String[7];
				morning = new String[7];
				lunch = new String[7];
				night = new String[7];

				sync();
			} else {
				addErrorList();
			}
		}
		return rootView;
	}
	
	@Override
	public void onStart() {
		super.onStart();
	}

	private void sync() {
		isSync = true;

		mAdapter.clearData();

		new Thread() {

			@Override
			public void run() {
				mHandler.sendEmptyMessage(0);

				try {
					calender = MealLibrary.getDate("goe.go.kr", "J100002436",
							"4", "03", "1");
					lunch = MealLibrary.getMeal("goe.go.kr", "J100002436", "4",
							"03", "2");

					save("calender", calender);
					save("morning", morning);
					save("lunch", lunch);
					save("night", night);

					mHandler.sendEmptyMessage(1);
				} catch (Exception ex) {
					ex.printStackTrace();

					mAdapter.clearData();

					addErrorList();
				}
				mHandler.sendEmptyMessage(2);
				isSync = false;
			}
		}.start();
	}

	private String getDate(int num) {
		if (num == 0)
			return "일요일";
		else if (num == 1)
			return "월요일";
		else if (num == 2)
			return "화요일";
		else if (num == 3)
			return "수요일";
		else if (num == 4)
			return "목요일";
		else if (num == 5)
			return "금요일";
		else if (num == 6)
			return "토요일";
		return null;
	}

	private void save(String name, String[] value) {
		for (int i = 0; i < value.length; i++) {
			bapListeditor.putString(name + "_" + i, value[i]);
		}
		bapListeditor.putBoolean("checker", true);
		bapListeditor.putInt(name, value.length);
		bapListeditor.commit();
	}

	private String[] restore(String name) {
		int length = bapList.getInt(name, 0);
		String[] string = new String[length];

		for (int i = 0; i < length; i++) {
			string[i] = bapList.getString(name + "_" + i, "");
		}
		return string;
	}

	private boolean isNetwork() {
		ConnectivityManager manager = (ConnectivityManager) getActivity()
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mobile = manager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		NetworkInfo wifi = manager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

		if (wifi.isConnected() || mobile.isConnected())
			return true;
		else
			return false;
	}

	private void addErrorList() {
		mAdapter.addItem("알수 없음", "알수 없음", noMessage, noMessage, noMessage);
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	    inflater.inflate(R.menu.bap, menu);
	    super.onCreateOptionsMenu(menu,inflater);
	}

	@Override
	public boolean onOptionsItemSelected(android.view.MenuItem item) {
		int ItemId = item.getItemId();

		if (ItemId == R.id.sync) {
			if (isNetwork()) {
				if (!isSync) {
					sync();
					item.setEnabled(false);
				} else {
				}
			} else {
				addErrorList();
			}
		}
		
		if (ItemId == R.id.about) {
			Intent i = new Intent(getActivity(), About.class);
			startActivity(i);
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onPause() {
		super.onPause();

		if (mDialog != null)
			mDialog.dismiss();
	}

	private class MyHandler extends Handler {
		private final WeakReference<Bap> mActivity;

		public MyHandler(Bap bap) {
			mActivity = new WeakReference<Bap>(bap);
		}

		@Override
		public void handleMessage(Message msg) {
			Bap activity = mActivity.get();
			if (activity != null) {

				if (msg.what == 0) {
					if (mDialog == null) {
						mDialog = ProgressDialog
								.show(getActivity(), "", loadingList);
					}
				} else if (msg.what == 1) {
					for (int i = 0; i < 7; i++) {
						mAdapter.addItem(calender[i], getDate(i), morning[i],
								lunch[i], night[i]);
					}
					mAdapter.notifyDataSetChanged();
				} else if (msg.what == 2) {
					mDialog.dismiss();
				}
			}
		}
	}
}