package com.woncheol.yuljeon;

import java.util.Timer;
import java.util.TimerTask;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ActionViewTarget;
import com.woncheol.yuljeon.fragment.Bap;
import com.woncheol.yuljeon.fragment.Call;
import com.woncheol.yuljeon.fragment.Haksaeng;
import com.woncheol.yuljeon.fragment.CheckSchedule;
import com.woncheol.yuljeon.fragment.Main;
import com.woncheol.yuljeon.fragment.Webview;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.app.SearchManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
 
public class MainActivity extends ActionBarActivity {
	
	private DrawerLayout			mDrawerLayout;
	private ListView				mDrawerList;
	private ActionBarDrawerToggle	mDrawerToggle;

	private CharSequence			mDrawerTitle;
	private CharSequence			mTitle;
	private String[]				mPlanetTitles;
    
    boolean btBackState = false;
    Timer timer = new Timer();

	long backPressedTime;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
        mTitle = mDrawerTitle = getTitle();
		mPlanetTitles = getResources().getStringArray(R.array.nav_drawer_items);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
		mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mPlanetTitles));
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
		SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this,
		mDrawerLayout,
		R.drawable.ic_drawer,
		R.string.app_name,
		R.string.app_name
		)
		{
			public void onDrawerClosed(View view) {
				getSupportActionBar().setTitle(mTitle);
			}

			public void onDrawerOpened(View drawerView) {
				getSupportActionBar().setTitle(mDrawerTitle);
			}
		};
		
        mDrawerLayout.setDrawerListener(mDrawerToggle);
 
        if (savedInstanceState == null) {
            displayView(0);
        }
        
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
        
        Boolean view = pref.getBoolean("view", true);
        if (view) {
        	new ShowcaseView.Builder(this)
            .setTarget(new ActionViewTarget(this, ActionViewTarget.Type.HOME))
            .setContentTitle("안녕하세요!")
            .setContentText("앱 아이콘을 누르거나 화면의 왼쪽에서 오른쪽으로 스와이프하면 율전중학교 앱의 다양한 기능을 만나실 수 있습니다")
            .setStyle(R.style.CustomShowcaseTheme)
            .hideOnTouchOutside()
            .build();
        	SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("view", false);
            editor.commit();
        }
        else{
        }
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id)
		{
			displayView(position);
		}
	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
	
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch(item.getItemId()) {
        case R.id.action_settings:
        	Intent i = new Intent(MainActivity.this, PrefsFragment.class);
        	startActivity(i);
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, getSupportActionBar().getTitle());
   		return false;
   	}
		return false;
    }
 
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
 
    private void displayView(int position) {
		Fragment fragment = null;
        switch (position) {
        case 0:
            fragment = new Main();
            break;
        case 1:
        	fragment = new Bap();
            break;
        case 2:
            fragment = new CheckSchedule();
            break;
        case 3:
            fragment = new Call();
            break;
        case 4:
            fragment = new Haksaeng();
            break;
        case 5:
            fragment = new Webview();
            break;
 
        default:
            break;
        }
        
        if (fragment != null) {
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();

            mDrawerList.setItemChecked(position, true);
            setTitle(mPlanetTitles[position]);
    		mDrawerLayout.closeDrawer(mDrawerList);
        }
    }
 
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }
 
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }
 
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
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