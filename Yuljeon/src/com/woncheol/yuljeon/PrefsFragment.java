package com.woncheol.yuljeon;

import com.woncheol.yuljeon.fragment.PreferenceCompatFragment;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
 
public class PrefsFragment extends PreferenceCompatFragment {
 
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_settings);
	
	Preference avrilbutton = (Preference) findPreference("application");
	avrilbutton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
	    @Override
	    public boolean onPreferenceClick(Preference preference) {
	    	Intent intent = new Intent(getActivity(), Prefsapplication.class);
            startActivity(intent);
	        return true;
	    }
	});
	
	Preference lavignebutton = (Preference) findPreference("tutorial");
	lavignebutton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
	    @Override
	    public boolean onPreferenceClick(Preference preference) {
	    	Intent intent = new Intent(getActivity(), Tutorial.class);
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