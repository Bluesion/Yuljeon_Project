package com.woncheol.yuljeon;

import android.content.Intent;
import android.net.Uri;
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
    
    Preference userButton = (Preference) findPreference("singo");
	userButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
	    @Override
	    public boolean onPreferenceClick(Preference arg0) {
	    	Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
	                "mailto","dnjscjf098@gmail.com", null));
	               emailIntent.putExtra(Intent.EXTRA_SUBJECT, "이름(학번)을 입력하세요");
	               startActivity(Intent.createChooser(emailIntent, "메일 보내기"));
	        return true;
	    }
	});
	
	Preference button = (Preference) findPreference("Opensource");
	button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
	    @Override
	    public boolean onPreferenceClick(Preference preference) {
	        Intent intent = new Intent(Intent.ACTION_VIEW);
	        intent.setData(Uri.parse("https://github.com/gpillusion/Yuljeon_Project"));
	        startActivity(intent);
	        return true;
	    }
	});
	
	Preference mybutton = (Preference) findPreference("builder");
	mybutton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
	    @Override
	    public boolean onPreferenceClick(Preference preference) {
	    	startActivity(new Intent(PrefsFragment.this, Builder.class));
	        return true;
	    }
	});
	
	Preference abutton = (Preference) findPreference("tutorial");
	abutton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
	    @Override
	    public boolean onPreferenceClick(Preference preference) {
	    	startActivity(new Intent(PrefsFragment.this, Tutorial.class));
	        return true;
	    }
	});
	
	Preference vbutton = (Preference) findPreference("version");
	vbutton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
	    @Override
	    public boolean onPreferenceClick(Preference preference) {
	    	startActivity(new Intent(PrefsFragment.this, Version.class));
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