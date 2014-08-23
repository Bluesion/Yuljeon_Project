package com.woncheol.yuljeon;

import com.woncheol.yuljeon.fragment.PreferenceCompatFragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
 
public class PrefsFragment extends PreferenceCompatFragment {
 
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_settings);
	
	Preference a = (Preference) findPreference("tutorial");
	a.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
	    @Override
	    public boolean onPreferenceClick(Preference preference) {
	    	Intent intent = new Intent(getActivity(), Tutorial.class);
            startActivity(intent);
	        return true;
	    }
	});
	
	Preference v = (Preference) findPreference("singo");
    v.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
	    @Override
	    public boolean onPreferenceClick(Preference arg0) {
	    	Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
	                "mailto","dnjscjf098@gmail.com", null));
	               emailIntent.putExtra(Intent.EXTRA_SUBJECT, "이름(학번)을 입력하세요");
	               startActivity(Intent.createChooser(emailIntent, "메일 보내기"));
	        return true;
	    }
	});
	
	Preference r = (Preference) findPreference("Opensource");
	r.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
	    @Override
	    public boolean onPreferenceClick(Preference preference) {
	        Intent intent = new Intent(Intent.ACTION_VIEW);
	        intent.setData(Uri.parse("https://github.com/gpillusion/Yuljeon_Project"));
	        startActivity(intent);
	        return true;
	    }
	});
	
	Preference i = (Preference) findPreference("license");
	i.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
	    @Override
	    public boolean onPreferenceClick(Preference preference) {
	        Intent intent = new Intent(getActivity(), License.class);
            startActivity(intent);
            return true;
	    }
	});
	
	Preference l = (Preference) findPreference("changelog");
	l.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
	    @Override
	    public boolean onPreferenceClick(Preference preference) {
	    	Intent intent = new Intent(getActivity(), Changelog.class);
            startActivity(intent);
	        return true;
	    }
	});
	
	}
    
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				getActivity().finish();
				return true;
		}
		return super.onOptionsItemSelected(item);
	};
}