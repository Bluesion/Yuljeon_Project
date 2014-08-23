package com.woncheol.yuljeon;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;

public class Util extends ActionBarActivity {
	
	public static void setAppTheme(ActionBarActivity a) {
		
	    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(a);
	    int mTheme = Integer.parseInt(sp.getString("actionbar", "0"));
	    if (mTheme == 0) {
	    	a.setTheme(R.style.Gray);
	    }
	    if (mTheme == 1) {
	        a.setTheme(R.style.Green);
	    }
	    if (mTheme == 2) { 
	        a.setTheme(R.style.Red);
	    }
	    if (mTheme == 3) {
	        a.setTheme(R.style.Orange);
	    }
	    if (mTheme == 4) {
	        a.setTheme(R.style.Blue);
	    }
	    if (mTheme == 5) {
	        a.setTheme(R.style.Purple);
	    }
	}
}