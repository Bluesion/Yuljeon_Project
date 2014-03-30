package com.woncheol.yuljeon;

import java.util.Timer;
import java.util.TimerTask;
import com.tistory.whdghks913.croutonhelper.CroutonHelper;
import de.keyboardsurfer.android.widget.crouton.Style;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	private ListView mListView;
	private ListViewAdapter mAdapter;

	private CroutonHelper mHelper;
	
	boolean btBackState = false;
    Timer timer = new Timer();
    
    long backPressedTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#666666")));

		mListView = (ListView) findViewById(R.id.mList);

		mAdapter = new ListViewAdapter(this);
		mListView.setAdapter(mAdapter);

		mAdapter.addItem(getResources().getDrawable(R.drawable.bap), "식단",
				"오늘 나오는 급식을 확인할 수 있습니다");
		mAdapter.addItem(getResources().getDrawable(R.drawable.calendar), "일정",
				"학교의 일정을 확인할 수 있습니다");
		mAdapter.addItem(getResources().getDrawable(R.drawable.call), "연락처",
				"학교로 전화를 할 수 있습니다");
		mAdapter.addItem(getResources().getDrawable(R.drawable.browser), "학교 홈페이지",
				"학교 홈페이지를 방문합니다");
		mAdapter.addItem(getResources().getDrawable(R.drawable.haksaeng), "학생부 소식",
				"학생부의 소식을 들어보세요");
		mAdapter.addItem(getResources().getDrawable(R.drawable.settings), "설정",
				"이 앱에 대한 자세한 정보를 알 수 있습니다");
		
		SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
		
		Boolean firstrun = pref.getBoolean("firstrun", true);
        if (firstrun) {
            Intent guide = new Intent(MainActivity.this, Tutorial.class);
            startActivity(guide);
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("firstrun", false);
            editor.commit();
        }
        else{
        }

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {

				if (position == 0) {
					startActivity(new Intent(MainActivity.this,
							Bap.class));
				} else if (position == 1) {
					startActivity(new Intent(MainActivity.this, Schedule.class));
				} else if (position == 2) {
					startActivity(new Intent(MainActivity.this, Call.class));
				} else if (position == 3) {
					startActivity(new Intent(MainActivity.this, Webview.class));
				} else if (position == 4) {
					startActivity(new Intent(MainActivity.this, Haksaeng.class));
				} else if (position == 5) {
					startActivity(new Intent(MainActivity.this, PrefsFragment.class));
				}
			}
		});

		mHelper = new CroutonHelper(this);
		mHelper.setText("환영합니다~!");
		mHelper.setDuration(1500);
		mHelper.setStyle(Style.INFO);
		mHelper.show();
	}

	@Override
	protected void onPause() {
		super.onPause();
		mHelper.cencle(true);
	}
	
	public void onBackPressed() {
    	if(btBackState == false) {
            timer.schedule(new TimerTask() {
            	
    public void run() {
    	btBackState = false;
    	}}, 3000);
    	btBackState = true;
    	Toast.makeText(getApplicationContext(), R.string.exit_message, Toast.LENGTH_SHORT).show();
    	} else finish();
    	}
    
    private void clearApplicationCache(java.io.File dir){
        if(dir==null)
            dir = getCacheDir();
        else;
        if(dir==null)
            return;
        else;
        java.io.File[] children = dir.listFiles();
        try{
            for(int i=0;i<children.length;i++)
                if(children[i].isDirectory())
                    clearApplicationCache(children[i]);
                else children[i].delete();
        }
        catch(Exception e){}
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        clearApplicationCache(null);
    }
}