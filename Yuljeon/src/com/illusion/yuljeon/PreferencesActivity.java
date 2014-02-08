package com.illusion.yuljeon;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class PreferencesActivity extends PreferenceActivity {
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_settings);
        addPreferencesFromResource(R.xml.pref_about);
        addPreferencesFromResource(R.xml.pref_opensource);
    } 
}