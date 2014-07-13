package com.woncheol.yuljeon;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
 
public class Prefsapplication extends PreferenceActivity {
 
	@SuppressWarnings("deprecation")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_application);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            getActionBar().setDisplayHomeAsUpEnabled(true);
        
        Preference a = (Preference) findPreference("singo");
        a.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
    	    @Override
    	    public boolean onPreferenceClick(Preference arg0) {
    	    	Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
    	                "mailto","dnjscjf098@gmail.com", null));
    	               emailIntent.putExtra(Intent.EXTRA_SUBJECT, "이름(학번)을 입력하세요");
    	               startActivity(Intent.createChooser(emailIntent, "메일 보내기"));
    	        return true;
    	    }
    	});
    	
    	Preference v = (Preference) findPreference("Opensource");
    	v.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
    	    @Override
    	    public boolean onPreferenceClick(Preference preference) {
    	        Intent intent = new Intent(Intent.ACTION_VIEW);
    	        intent.setData(Uri.parse("https://github.com/gpillusion/Yuljeon_Project"));
    	        startActivity(intent);
    	        return true;
    	    }
    	});
        
        Preference r = (Preference) findPreference("builder");
    	r.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
    	    @Override
    	    public boolean onPreferenceClick(Preference preference) {
    	    	startActivity(new Intent(Prefsapplication.this, Builder.class));
    	        return true;
    	    }
    	});
    	
    	Preference i = (Preference) findPreference("version");
    	i.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
    	    @Override
    	    public boolean onPreferenceClick(Preference preference) {
    	    	startActivity(new Intent(Prefsapplication.this, Version.class));
    	        return true;
    	    }
    	});
    	
    	Preference l = (Preference) findPreference("techhelp");
    	l.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
    	    @Override
    	    public boolean onPreferenceClick(Preference preference) {
    	    	startActivity(new Intent(Prefsapplication.this, Prefstechhelp.class));
    	        return true;
    	    }
    	});
    	
    	Preference love = (Preference) findPreference("license");
    	love.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
    	    @Override
    	    public boolean onPreferenceClick(Preference preference) {
    	    	startActivity(new Intent(Prefsapplication.this, License.class));
    	        return true;
    	    }
    	});
    	
    	Preference forever = (Preference) findPreference("changelog");
    	forever.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
    	    @Override
    	    public boolean onPreferenceClick(Preference preference) {
    	    	startActivity(new Intent(Prefsapplication.this, Changelog.class));
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