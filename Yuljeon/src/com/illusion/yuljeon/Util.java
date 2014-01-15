package com.illusion.yuljeon;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;

public class Util extends Activity {
	
	public static void setAppTheme(Activity a) {
		
	    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(a);
	    int mTheme = Integer.parseInt(sp.getString("theme", "0"));
	    if (mTheme == 0) {
	        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
	            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
	                a.setTheme(android.R.style.Theme_Holo_Light);
	                return;
	            }
	            a.setTheme(android.R.style.Theme_Holo_Light);
	        }
	        else {
	            a.setTheme(android.R.style.Theme_Light);
	        }
	    }
	    else {
	        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
	        a.setTheme(android.R.style.Theme_Holo_Light_DarkActionBar);
	    } else {
	        a.setTheme(android.R.style.Theme_Black);
	        }
	    }
	}
}