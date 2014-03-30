package com.woncheol.yuljeon;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.SlidingDrawer;
 
@SuppressWarnings("deprecation")
public class Webview extends ActionBarActivity {
     
	private WebView webView;
	private SlidingDrawer slide;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);
		getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3f9fe0")));
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            getActionBar().setDisplayHomeAsUpEnabled(true);

		webView = (WebView) findViewById(R.id.webview);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl("http://www.yuljeon.ms.kr");
		webView.setWebViewClient(new myWebViewClient() {
		    @Override
		    public boolean shouldOverrideUrlLoading(WebView view, String url) {
		        view.loadUrl(url);
		        return false;
		    }
		});
		
		ImageButton illusion = (ImageButton) findViewById(R.id.backBtn);
		illusion.setOnClickListener(new View.OnClickListener() {
          public void onClick(View paramAnonymousView)
          {
            if (Webview.this.webView.canGoBack())
            	Webview.this.webView.goBack();
          }
        });
		
		ImageButton avril = (ImageButton) findViewById(R.id.forwardBtn);
		avril.setOnClickListener(new View.OnClickListener() {
          public void onClick(View paramAnonymousView)
          {
            if (Webview.this.webView.canGoForward())
            	Webview.this.webView.goForward();
          }
        });
		
		ImageButton lavigne = (ImageButton) findViewById(R.id.reload);
		lavigne.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramAnonymousView)
	          {
				Webview.this.webView.reload();
	          }
        });
		
		slide = (SlidingDrawer)this.findViewById(R.id.SD);
	    slide.open();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.webview, menu);

		return super.onCreateOptionsMenu(menu);
	}
	
	public boolean onOptionsItemSelected(android.view.MenuItem item) {
		int ItemId = item.getItemId();
		
		if (ItemId == R.id.about) {
			startActivity(new Intent(Webview.this, TutorialWebview.class));
		}
		
		switch (item.getItemId()) {
			case android.R.id.home:
				finish();
				return true;
		}
		
		return super.onOptionsItemSelected(item);
	};
	
private class myWebViewClient extends WebViewClient {

		 @Override
	     public void onPageStarted(WebView view, String url, Bitmap favicon) {
	      super.onPageStarted(view, url, favicon);
	      findViewById(R.id.progressBar1).setVisibility(View.VISIBLE);

	     }

		@Override
		public void onPageFinished(WebView view, String url) {
			findViewById(R.id.progressBar1).setVisibility(View.GONE);
		}
	}
}