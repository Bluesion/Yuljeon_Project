package com.illusion.yuljeon;

import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.app.SearchManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
 
public class SubActivity extends ActionBarActivity {
	
	private DrawerLayout			mDrawerLayout;
	private ListView				mDrawerList;
	private ActionBarDrawerToggle	mDrawerToggle;

	private CharSequence			mDrawerTitle;
	private CharSequence			mTitle;
	private String[]				mPlanetTitles;
    
    boolean btBackState = false;
    Timer timer = new Timer();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	Utils.setAppTheme(this);
    	Util.setAppTheme(this);
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
            Intent guide = new Intent(SubActivity.this, Tutorial.class);
            startActivity(guide);
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("firstrun", false);
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
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }
	
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch(item.getItemId()) {
        case R.id.action_settings:
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, getSupportActionBar().getTitle());
            if (Build.VERSION.SDK_INT < 11) {
   			 startActivity(new Intent(this, PreferencesActivity.class));
   			} else {
   			 startActivity(new Intent(this, OtherPreferencesActivity.class));
   			}
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
            fragment = new MainFragment();
            break;
        case 1:
        	fragment = new SosikFragment();
            break;
        case 2:
            fragment = new WebviewFragment();
            break;
        case 3:
            fragment = new LunchFragment();
            break;
 
        default:
            break;
        }

		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();

            mDrawerList.setItemChecked(position, true);
            setTitle(mPlanetTitles[position]);
    		mDrawerLayout.closeDrawer(mDrawerList);
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
    	}
    	}, 3000);
    	btBackState = true;
    	Toast.makeText(getApplicationContext(), R.string.exit_message, Toast.LENGTH_SHORT).show();
    	} else
    	finish();
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
    
    public static class MainFragment extends Fragment {

		public MainFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		{
			View rootView = inflater.inflate(R.layout.main, container, false);

			return rootView;
		}
	}
	
	public static class SosikFragment extends Fragment {

	    ListView lv1;

	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {

	        if (container == null) {
	          return null;
	       }
	                View view = inflater.inflate(R.layout.sosik, container, false);

	                lv1 = (ListView) view.findViewById(R.id.list);

	                String[] adobe_products = getResources().getStringArray(R.array.listview);

	               ArrayAdapter<String> files = new ArrayAdapter<String>(getActivity(), 
	                        android.R.layout.simple_list_item_1, 
	                        adobe_products);

	               lv1.setAdapter(files);

	        return view;
	    }
	}
	
	public static class WebviewFragment extends Fragment {

		public WebviewFragment() {
		}

		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	        View mainView = inflater.inflate(R.layout.webview, container, false);

	        WebView webView = (WebView) mainView.findViewById(R.id.webview);
	        webView.getSettings().setJavaScriptEnabled(true);
	        webView.setWebViewClient(new WebViewClient());
	        webView.loadUrl("http://www.yuljeon.ms.kr");

	    return mainView;
	    
		}
	}
	
	public static class LunchFragment extends Fragment {

		public LunchFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.lunch, container, false);

			return rootView;
		}
	}
}