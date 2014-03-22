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

		mAdapter.addItem(null, "������", "031-548-8118");
		mAdapter.addItem(null, "������ 1", "031-548-8106");
		mAdapter.addItem(null, "������ 2", "031-548-8107");
		mAdapter.addItem(null, "������ 3", "031-548-8108");

		mHelper = new CroutonHelper(this);
		mHelper.setText("��ȭ �ϰ��� �ϴ� ��ȣ�� ��ġ�ϼ���\n��ġ�Ѵٰ� �ؼ� �ٷ� ��ȭ�� �ɸ����� �ʽ��ϴ�");
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
