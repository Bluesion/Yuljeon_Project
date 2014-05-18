package com.woncheol.yuljeon.fragment;

import com.woncheol.yuljeon.R;
import com.woncheol.yuljeon.Schedule;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.v4.app.Fragment;

public class CheckSchedule extends Fragment {
    
    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.activity_check_schedule, container, false);
		
		Button a = (Button)rootView.findViewById(R.id.button1);
		a.setOnClickListener(new OnClickListener() {
		     public void onClick(View v) {
		        Intent intent = new Intent(getActivity(), Schedule.class);
		        startActivity(intent);
		     }
		});

		return rootView;
    }
}