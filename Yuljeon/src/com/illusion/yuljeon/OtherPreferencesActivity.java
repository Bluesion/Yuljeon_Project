package com.illusion.yuljeon;

import java.util.List;

import android.os.Build;
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
		Utils.setAppTheme(this);
		Util.setAppTheme(this);
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            getActionBar().setDisplayHomeAsUpEnabled(true);
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