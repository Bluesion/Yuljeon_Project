package com.woncheol.yuljeon.fragment;

import com.woncheol.yuljeon.R;
import com.woncheol.yuljeon.TutorialWebview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.SlidingDrawer;
 
@SuppressWarnings("deprecation")
public class Webview extends Fragment {
     
	private WebView webView;
	private SlidingDrawer slide;
	ProgressBar progressBar;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		setHasOptionsMenu(true);
		View rootView = inflater.inflate(R.layout.fragment_webview, container, false);

		webView = (WebView) rootView.findViewById(R.id.webview);
		progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar1);
		webView.loadUrl("http://www.yuljeon.ms.kr");
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setBuiltInZoomControls(true);
		webView.getSettings().setSupportZoom(true);
		webView.setWebViewClient(new myWebClient());
		
		ImageButton illusion = (ImageButton) rootView.findViewById(R.id.backBtn);
		illusion.setOnClickListener(new View.OnClickListener() {
          public void onClick(View paramAnonymousView)
          {
            if (Webview.this.webView.canGoBack())
            	Webview.this.webView.goBack();
          }
        });
		
		ImageButton avril = (ImageButton) rootView.findViewById(R.id.forwardBtn);
		avril.setOnClickListener(new View.OnClickListener() {
          public void onClick(View paramAnonymousView)
          {
            if (Webview.this.webView.canGoForward())
            	Webview.this.webView.goForward();
          }
        });
		
		ImageButton lavigne = (ImageButton) rootView.findViewById(R.id.reload);
		lavigne.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramAnonymousView)
	          {
				Webview.this.webView.reload();
	          }
        });
		
		slide = (SlidingDrawer)rootView.findViewById(R.id.SD);
	    slide.open();
	    
		return rootView;
	}
	
	@Override
	public void onStart() {
		super.onStart();
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	    inflater.inflate(R.menu.webview, menu);
	    super.onCreateOptionsMenu(menu,inflater);
	}
	
	@Override
	public boolean onOptionsItemSelected(android.view.MenuItem item) {
		int ItemId = item.getItemId();
		
		if (ItemId == R.id.about) {
			Intent i = new Intent(getActivity(), TutorialWebview.class);
			startActivity(i);
		}
		
		return super.onOptionsItemSelected(item);
	};
	
public class myWebClient extends WebViewClient {
	
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
      super.onPageStarted(view, url, favicon);
     }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
      view.loadUrl(url);
      return true;
     }

     @Override
     public void onPageFinished(WebView view, String url) {
       super.onPageFinished(view, url);

      progressBar.setVisibility(View.GONE);
     }
    }
}