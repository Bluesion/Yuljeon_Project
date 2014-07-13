package com.woncheol.yuljeon;

import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

public class Changelog extends ActionBarActivity {
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changelog);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#AA66CC")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
 
        TextView txtContent = (TextView) findViewById(R.id.txtContent);
 
        AssetManager assetManager = getAssets();
        
        InputStream input;
        try {
            input = assetManager.open("Changelog.txt");
 
             int size = input.available();
             byte[] buffer = new byte[size];
             input.read(buffer);
             input.close();
 
             String text = new String(buffer);
 
             txtContent.setText(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
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