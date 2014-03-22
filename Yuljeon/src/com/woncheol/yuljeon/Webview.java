package com.woncheol.yuljeon;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
 
public class Webview extends ActionBarActivity {
     
	private WebView webView;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);
		getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3f9fe0")));

		webView = (WebView) findViewById(R.id.webview);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl("http://www.yuljeon.ms.kr");
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
	    super.onCreateOptionsMenu(menu);
	    menu.add(0, 1, 0, "Back");
	    menu.add(0, 2, 0, "Refresh");
	    menu.add(0, 3, 0, "Forward");
	    return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item){
	    switch (item.getItemId()){
	        case 1:
	            webView.goBack();
	        return true;
	        case 2:
	            webView.reload();
	        return true;
	        case 3:
	            webView.goForward();
	        return true;
	        }
	    return false;
	    }
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
	        webView.goBack();
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}
}