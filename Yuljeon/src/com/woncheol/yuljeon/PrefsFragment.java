package com.woncheol.yuljeon;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
 
public class PrefsFragment extends PreferenceActivity {
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_settings);
    
    Preference userButton = (Preference) findPreference("singo");
	userButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
	    @Override
	    public boolean onPreferenceClick(Preference arg0) {
	    	Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
	                "mailto","dnjscjf098@gmail.com", null));
	               emailIntent.putExtra(Intent.EXTRA_SUBJECT, "이름(학번)을 입력하세요");
	               emailIntent.putExtra(Intent.EXTRA_TEXT, "오류, 아이디어, 저작권 위반 등 중대한 사항이 아니면 절대 보내지 마세요");
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
	
	}
}