package com.woncheol.yuljeon;

import java.util.Timer;
import java.util.TimerTask;
import com.woncheol.yuljeon.fragment.Bap;
import com.woncheol.yuljeon.fragment.Call;
import com.woncheol.yuljeon.fragment.Haksaeng;
import com.woncheol.yuljeon.fragment.Main;
import com.woncheol.yuljeon.fragment.Schedule;
import com.woncheol.yuljeon.fragment.Webview;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
        setContentView(R.layout.drawer);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#666666")));
 
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
    }
	
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
		return false;
    }
 
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
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
            fragment = new Schedule();
            break;
        case 3:
            fragment = new Call();
            break;
        case 4:
            fragment = new Webview();
            break;
        case 5:
            fragment = new Haksaeng();
            break;
        case 6:
            fragment = new PrefsFragment();
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
    
private class DrawerItemClickListener implements ListView.OnItemClickListener {
    	
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id)
		{
			displayView(position);
		}
	}
}