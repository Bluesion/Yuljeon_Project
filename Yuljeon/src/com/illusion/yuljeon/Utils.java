package com.illusion.yuljeon;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;

public class Utils extends Activity {
	
	public static void setAppTheme(Activity a) {
		
	    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(a);
	    int mTheme = Integer.parseInt(sp.getString("theme", "0"));
	    if (mTheme == 0) {
	        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
	            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
	                a.setTheme(R.style.AppBaseTheme);
	                return;
	            }
	            a.setTheme(R.style.AppBaseTheme);
	        }
	    }
	    if (mTheme == 1) {
	        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
	        a.setTheme(R.style.Dark);
	        }
	    }
	    if (mTheme == 2) {
	        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
	        a.setTheme(R.style.FullAppBaseTheme);
	        }
	    }
	    if (mTheme == 3) {
	        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
	        a.setTheme(R.style.FullDark);
	        }
	    }
	}
}