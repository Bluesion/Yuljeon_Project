package com.illusion.yuljeon;

import java.util.List;

import android.os.Bundle;
import android.preference.PreferenceActivity;

@android.annotation.TargetApi(16)
public class OtherPreferencesActivity extends PreferenceActivity {
	@Override
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.preference_headers, target);
    }
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		Util.setAppTheme(this);
        super.onCreate(savedInstanceState);
	}
}