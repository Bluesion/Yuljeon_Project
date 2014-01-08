package com.illusion.yuljeon;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.TextView;

public class PrefActivity extends Activity {
 
 CheckBox prefCheckBox;
 TextView prefEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pref_main);
        
     prefCheckBox = (CheckBox)findViewById(R.id.prefCheckBox);
     prefEditText = (TextView)findViewById(R.id.prefEditText);
     
     loadPref();
    }

 @Override
 public boolean onOptionsItemSelected(MenuItem item) {
  
  Intent intent = new Intent();
        intent.setClass(PrefActivity.this, SetPreferenceActivity.class);
        startActivityForResult(intent, 0); 
  
        return true;
 }

 @Override
 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
  // TODO Auto-generated method stub
  //super.onActivityResult(requestCode, resultCode, data);
  
  /*
   * To make it simple, always re-load Preference setting.
   */
  
  loadPref();
 }
    
 private void loadPref(){
  SharedPreferences mySharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
  
  boolean my_checkbox_preference = mySharedPreferences.getBoolean("checkbox_preference", false);
  prefCheckBox.setChecked(my_checkbox_preference);

  String my_edittext_preference = mySharedPreferences.getString("edittext_preference", "");
     prefEditText.setText(my_edittext_preference);

 }
        
}