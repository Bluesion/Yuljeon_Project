package com.woncheol.yuljeon;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Utils extends Activity {
	
	public static void setAppTheme(Activity a) {
		
	    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(a);
	    int mTheme = Integer.parseInt(sp.getString("theme", "0"));
	    if (mTheme == 0) {
	        a.setTheme(R.style.AppBaseTheme);
	                return;
	            }
	            a.setTheme(R.style.AppBaseTheme);
	            
	    if (mTheme == 1) {
	        a.setTheme(R.style.FullAppBaseTheme);
	              }
	         }
        }