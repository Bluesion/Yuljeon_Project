package com.woncheol.yuljeon;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.tistory.whdghks913.croutonhelper.CroutonHelper;

import de.keyboardsurfer.android.widget.crouton.Style;

public class Call extends ActionBarActivity {

	private ListView mListView;
	private ListViewAdapter mAdapter;

	private CroutonHelper mHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_call);
		getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#c74b46")));

		mListView = (ListView) findViewById(R.id.mContactsList);

		mAdapter = new ListViewAdapter(this);
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

		mAdapter.addItem(null, "교무실", "031-548-8118");
		mAdapter.addItem(null, "행정실 1", "031-548-8106");
		mAdapter.addItem(null, "행정실 2", "031-548-8107");
		mAdapter.addItem(null, "행정실 3", "031-548-8108");

		mHelper = new CroutonHelper(this);
		mHelper.setText("전화 하고자 하는 번호를 터치하세요\n터치한다고 해서 바로 전화가 걸리지는 않습니다");
		mHelper.setDuration(1500);
		mHelper.setStyle(Style.INFO);
		mHelper.show();
	}

	@Override
	protected void onPause() {
		super.onPause();

		mHelper.cencle(true);
	}
}
