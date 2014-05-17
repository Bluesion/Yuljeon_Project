package com.woncheol.yuljeon.fragment;

import com.woncheol.yuljeon.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;

public class Main extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.activity_mainfragment, container, false);
	}
	
	@Override
	public void onStart() {
		super.onStart();
	}
}