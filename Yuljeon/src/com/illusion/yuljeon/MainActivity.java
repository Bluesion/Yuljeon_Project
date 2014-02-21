package com.illusion.yuljeon;

import java.util.Timer;
import java.util.TimerTask;
import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.app.SearchManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
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
            Intent guide = new Intent(MainActivity.this, Tutorial.class);
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
	
	public static class SosikFragment extends ListFragment implements OnRefreshListener {

		private static String[] ITEMS = {"3월 3일 (월) : 율전중 개학식"};

		private PullToRefreshLayout mPullToRefreshLayout;

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view,savedInstanceState);
            ViewGroup viewGroup = (ViewGroup) view;
            TextView tv = new TextView(getActivity());
            tv.setText("율전중학교 3월 소식");
            tv.setTextSize(getResources().getDimension(R.dimen.textsize));
            getListView().addHeaderView(tv);
            setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,
                    new String[] { "Hello world" }));

            mPullToRefreshLayout = new PullToRefreshLayout(viewGroup.getContext());

            ActionBarPullToRefresh.from(getActivity())
                    .insertLayoutInto(viewGroup)
                    .theseChildrenArePullable(android.R.id.list, android.R.id.empty)
                    .listener(this)
                    .setup(mPullToRefreshLayout);
        }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, ITEMS));
        setListShownNoAnimation(true);
    }
    
    @Override
    public void onDestroyView() {
        setListAdapter(null);
        super.onDestroyView();
    }

    @Override
    public void onRefreshStarted(View view) {
        setListShown(false);

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(Constants.SIMULATED_REFRESH_LENGTH);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);

                mPullToRefreshLayout.setRefreshComplete();

                if (getView() != null) {
                    setListShown(true);
                }
            }
        }.execute();
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
	        webView.setOnKeyListener(new OnKeyListener()
			{
			    @Override
			    public boolean onKey(View v, int keyCode, KeyEvent event)
			    {
			        if(event.getAction() == KeyEvent.ACTION_DOWN)
			        {
			            WebView webView = (WebView) v;

			            switch(keyCode)
			            {
			                case KeyEvent.KEYCODE_BACK:
			                    if(webView.canGoBack())
			                    {
			                        webView.goBack();
			                        return true;
			                    }
			                    break;
			            }
			        }

			        return false;
			    }
			});

	    return mainView;
	    
		}
	}
	
	public static class LunchFragment extends ListFragment implements OnRefreshListener {

		private static String[] ITEMS = {"3월 3일 (월) : 데이터가 없습니다",
			"3월 4일 (화) : 데이터가 없습니다",
			"3월 5일 (수) : 데이터가 없습니다",
            "3월 6일 (목) : 데이터가 없습니다",
            "3월 7일 (금) : 데이터가 없습니다",
            "3월 10일 (월) : 데이터가 없습니다",
            "3월 11일 (화) : 데이터가 없습니다",
            "3월 12일 (수) : 데이터가 없습니다",
            "3월 13일 (목) : 데이터가 없습니다",
            "3월 14일 (금) : 데이터가 없습니다",
            "3월 17일 (월) : 데이터가 없습니다",
            "3월 18일 (화) : 데이터가 없습니다",
            "3월 19일 (수) : 데이터가 없습니다",
            "3월 20일 (목) : 데이터가 없습니다",
            "3월 21일 (금) : 데이터가 없습니다",
            "3월 24일 (월) : 데이터가 없습니다",
            "3월 25일 (화) : 데이터가 없습니다",
            "3월 26일 (수) : 데이터가 없습니다",
            "3월 27일 (목) : 데이터가 없습니다",
            "3월 28일 (금) : 데이터가 없습니다",
            "3월 31일 (월) : 데이터가 없습니다"};

		private PullToRefreshLayout mPullToRefreshLayout;

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view,savedInstanceState);
            ViewGroup viewGroup = (ViewGroup) view;
            TextView tv = new TextView(getActivity());
            tv.setText("율전중학교 3월 급식");
            tv.setTextSize(getResources().getDimension(R.dimen.textsize));
            getListView().addHeaderView(tv);
            setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,
                    new String[] { "Hello world" }));

            mPullToRefreshLayout = new PullToRefreshLayout(viewGroup.getContext());

            ActionBarPullToRefresh.from(getActivity())
                    .insertLayoutInto(viewGroup)
                    .theseChildrenArePullable(android.R.id.list, android.R.id.empty)
                    .listener(this)
                    .setup(mPullToRefreshLayout);
        }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, ITEMS));
        setListShownNoAnimation(true);
    }
    
    @Override
    public void onDestroyView() {
        setListAdapter(null);
        super.onDestroyView();
    }

    @Override
    public void onRefreshStarted(View view) {
        setListShown(false);

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(Constants.SIMULATED_REFRESH_LENGTH);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);

                mPullToRefreshLayout.setRefreshComplete();

                if (getView() != null) {
                    setListShown(true);
                }
            }
        }.execute();
    }
}
	
	public class Constants {

	    public static final int SIMULATED_REFRESH_LENGTH = 700;
	}
}