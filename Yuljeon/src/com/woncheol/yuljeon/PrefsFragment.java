package com.woncheol.yuljeon;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
 
public class PrefsFragment extends PreferenceActivity {
 
	@SuppressWarnings("deprecation")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_settings);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            getActionBar().setDisplayHomeAsUpEnabled(true);
	
	Preference avrilbutton = (Preference) findPreference("application");
	avrilbutton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
	    @Override
	    public boolean onPreferenceClick(Preference preference) {
	    	startActivity(new Intent(PrefsFragment.this, Prefsapplication.class));
	        return true;
	    }
	});
	
	Preference lavignebutton = (Preference) findPreference("tutorial");
	lavignebutton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
	    @Override
	    public boolean onPreferenceClick(Preference preference) {
	    	startActivity(new Intent(PrefsFragment.this, Tutorial.class));
	        return true;
	    }
	});
	
	}
    
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				finish();
				return true;
		}
		return super.onOptionsItemSelected(item);
	};
}