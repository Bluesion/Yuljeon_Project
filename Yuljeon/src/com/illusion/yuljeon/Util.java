package com.illusion.yuljeon;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;

public class Util extends Activity {
	
	public static void setAppTheme(Activity a) {
		
	    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(a);
	    int mTheme = Integer.parseInt(sp.getString("actionbar", "0"));
	    if (mTheme == 0) {
	        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
	            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
	                a.setTheme(R.style.Gray);
	                return;
	            }
	            a.setTheme(R.style.Gray);
	        }
	    }
	    if (mTheme == 1) {
	        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
	        a.setTheme(R.style.Green);
	        }
	    }
	    if (mTheme == 2) {
	        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
	        a.setTheme(R.style.Red);
	        }
	    }
	    if (mTheme == 3) {
	        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
	        a.setTheme(R.style.Orange);
	        }
	    }
	    if (mTheme == 4) {
	        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
	        a.setTheme(R.style.Blue);
	        }
	    }
	    if (mTheme == 5) {
	        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
	        a.setTheme(R.style.Purple);
	        }
	    }
	}
}