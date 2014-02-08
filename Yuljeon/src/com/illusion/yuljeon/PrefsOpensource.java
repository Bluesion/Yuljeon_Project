package com.illusion.yuljeon;

import android.os.Bundle;
import android.preference.PreferenceFragment;

@android.annotation.TargetApi(16)
public class PrefsOpensource extends PreferenceFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.pref_opensource);
	}
}