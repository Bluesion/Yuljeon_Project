package com.woncheol.yuljeon;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
 
public class TutorialWebview extends ActionBarActivity {
     
	private ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorial_webview);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3f9fe0")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(new PagerAdapterClass(getApplicationContext()));
    }
    
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				finish();
				return true;
		}
		return super.onOptionsItemSelected(item);
	};

    @SuppressWarnings("unused")
    private void setCurrentInflateItem(int type){
        if(type==0){
            mPager.setCurrentItem(0);
        }
        else if(type==1){
            mPager.setCurrentItem(1);
        }
        else if(type==2){
            mPager.setCurrentItem(2);
        }
        else if(type==3){
            mPager.setCurrentItem(3);
        }
        else{
            mPager.setCurrentItem(4);
        }
    }

private class PagerAdapterClass extends PagerAdapter {

        private LayoutInflater mInflater;
        public PagerAdapterClass(Context c){
            super();
            mInflater = LayoutInflater.from(c);
        }

        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public Object instantiateItem(View pager, int position) {
            View v = null;
            if(position == 0){
                v = mInflater.inflate(R.layout.webview_tutorial_0, null);
            }
            else if(position == 1){
            	v = mInflater.inflate(R.layout.webview_tutorial_1, null);
            }
            else if(position == 2){
                v = mInflater.inflate(R.layout.webview_tutorial_2, null);
            }
            else if(position == 3){
                v = mInflater.inflate(R.layout.webview_tutorial_done, null);
            }
            else{
                v = mInflater.inflate(R.layout.webview_tutorial_done, null);
                finish();
            }
            ((ViewPager)pager).addView(v, 0);
            return v;
        }
        @Override
        public void destroyItem(View pager, int position, Object view){
            ((ViewPager)pager).removeView((View)view);
        }

        @Override
        public boolean isViewFromObject(View pager, Object obj){
            return pager == obj;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if(position == 0){
                return getString(R.string.webview_tutorial_0);
            }
            else if(position == 1){
               return getString(R.string.webview_tutorial_1);
            }
            else if(position == 2){
               return getString(R.string.webview_tutorial_2);
            }
            else if(position == 3){
                return getString(R.string.webview_tutorial_done);
            }
            else{
                return getString(R.string.webview_tutorial_done);
            }
        }
    }
}