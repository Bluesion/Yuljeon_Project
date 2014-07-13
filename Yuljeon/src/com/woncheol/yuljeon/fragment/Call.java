package com.woncheol.yuljeon.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.tistory.whdghks913.croutonhelper.CroutonHelper;
import com.woncheol.yuljeon.R;
import com.woncheol.yuljeon.adapter.ListViewAdapter;
import de.keyboardsurfer.android.widget.crouton.Style;

public class Call extends Fragment {

	private ListView mListView;
	private ListViewAdapter mAdapter;

	private CroutonHelper mHelper;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_call, container, false);
		
		mListView = (ListView) rootView.findViewById(R.id.mContactsList);

		mAdapter = new ListViewAdapter(getActivity());
		mListView.setAdapter(mAdapter);

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				try {
					startActivity(new Intent(Intent.ACTION_VIEW, Uri
							.parse("tel:"
									+ mAdapter.mListData.get(position).mText)));
				} catch (Exception e) {

				}
			}
		});

		mAdapter.addItem(null, "������", "031-548-8118");
		mAdapter.addItem(null, "������ 1", "031-548-8106");
		mAdapter.addItem(null, "������ 2", "031-548-8107");
		mAdapter.addItem(null, "������ 3", "031-548-8108");

		mHelper = new CroutonHelper(getActivity());
		mHelper.setText("��ȭ �ϰ��� �ϴ� ��ȣ�� ��ġ�ϼ���\n��ġ�Ѵٰ� �ؼ� �ٷ� ��ȭ�� �ɸ����� �ʽ��ϴ�");
		mHelper.setDuration(1500);
		mHelper.setStyle(Style.INFO);
		mHelper.show();
		
		return rootView;
	}
	
	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onPause() {
		super.onPause();

		mHelper.cencle(true);
	}
}