package com.woncheol.yuljeon.fragment;

import java.util.ArrayList;

import com.tistory.whdghks913.croutonhelper.CroutonHelper;
import com.woncheol.yuljeon.R;
import com.woncheol.yuljeon.adapter.EntryAdapter;
import com.woncheol.yuljeon.adapter.EntryItem;
import com.woncheol.yuljeon.adapter.Item;
import com.woncheol.yuljeon.adapter.SectionItem;

import de.keyboardsurfer.android.widget.crouton.Style;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class Schedule extends Fragment {

	ArrayList<Item> items = new ArrayList<Item>();
	ListView listview=null;
	
	private CroutonHelper mHelper;
	 
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_schedule, container, false);
      
       listview = (ListView) rootView.findViewById(R.id.listView_main);
      
       items.add(new SectionItem("2014년 3월 일정"));
       items.add(new EntryItem("입학식", "2014.03.03 (월)"));
       items.add(new EntryItem("학부모 총회", "2014.03.20 (목)"));
              
       items.add(new SectionItem("2014년 4월 일정"));
       items.add(new EntryItem("1학년 영어듣기 평가", "2014.04.08 (화)"));
       items.add(new EntryItem("2학년 영어듣기 평가", "2014.04.09 (수)"));
       items.add(new EntryItem("3학년 영어듣기 평가", "2014.04.10 (목)"));
       items.add(new EntryItem("현장 체험 학습", "2014.04.11 (금)"));
       items.add(new EntryItem("양원철 왕자님 탄신일♥", "2014.04.23 (수)"));
       items.add(new EntryItem("1학기 중간고사", "2014.04.30 (수)"));
       
       items.add(new SectionItem("2014년 5월 일정"));
       items.add(new EntryItem("1학기 중간고사", "2014.05.01 (목)"));
       items.add(new EntryItem("1학기 중간고사", "2014.05.02 (금)"));
       
       items.add(new SectionItem("2014년 6월 일정"));
       items.add(new EntryItem("개교기념일", "2014.06.05 (목)"));
       items.add(new EntryItem("3학년 학업성취도 평가", "2014.06.24 (화)"));
       
       items.add(new SectionItem("2014년 7월 일정"));
       items.add(new EntryItem("1학기 기말고사", "2014.07.02 (수)"));
       items.add(new EntryItem("1학기 기말고사", "2014.07.03 (목)"));
       items.add(new EntryItem("1학기 기말고사", "2014.07.04 (금)"));
       items.add(new EntryItem("방학식", "2014.07.21 (월)"));
       
       items.add(new SectionItem("2014년 8월 일정"));
       items.add(new EntryItem("개학식", "2014.08.18 (월)"));
       
       items.add(new SectionItem("2014년 9월 일정"));
       items.add(new EntryItem("1학년 영어듣기 평가", "2014.09.02 (화)"));
       items.add(new EntryItem("2학년 영어듣기 평가", "2014.09.03 (수)"));
       items.add(new EntryItem("3학년 영어듣기 평가", "2014.09.04 (목)"));
       items.add(new EntryItem("라빈 여신님 탄신일♥", "2014.09.27 (토)"));
       items.add(new EntryItem("2학기 중간고사", "2014.09.29 (월)"));
       items.add(new EntryItem("2학기 중간고사", "2014.09.30 (화)"));
       
       items.add(new SectionItem("2014년 10월 일정"));
       items.add(new EntryItem("2학기 중간고사", "2014.10.01 (수)"));
       items.add(new EntryItem("학교 축제", "2014.10.23 (목)"));
       items.add(new EntryItem("현장 체험 학습", "2014.10.24 (금)"));
       
       items.add(new SectionItem("2014년 11월 일정"));
       items.add(new EntryItem("특별한 일정이 존재하지 않습니다", "방학도 아닌데 특별한 일정이 없는 이유가 뭘까요"));
       
       items.add(new SectionItem("2014년 12월 일정"));
       items.add(new EntryItem("2학기 기말고사", "2014.12.03 (수)"));
       items.add(new EntryItem("2학기 기말고사", "2014.12.04 (목)"));
       items.add(new EntryItem("2학기 기말고사", "2014.12.05 (금)"));
       items.add(new EntryItem("3학년 졸업여행", "2014.12.17 (수)"));
       items.add(new EntryItem("방학식", "2014.12.31 (수)"));
       
       items.add(new SectionItem("2015년 1월 일정"));
       items.add(new EntryItem("특별한 일정이 존재하지 않습니다", "1월 전체가 방학 기간이라 일정이 없습니다"));
       
       items.add(new SectionItem("2015년 2월 일정"));
       items.add(new EntryItem("개학식", "2015.02.02 (월)"));
       items.add(new EntryItem("3학년 졸업식", "2015.02.06 (금)"));
       items.add(new EntryItem("1,2학년 종업식", "2015.02.09 (월)"));
       
       EntryAdapter adapter = new EntryAdapter(getActivity(), items);
       listview.setAdapter(adapter);
       
       mHelper = new CroutonHelper(getActivity());
	   mHelper.setText("일정은 학사력을 기준으로 작성되었으며\n학교 사정에 의해 언제든지 바뀔 수 있습니다\n\n※ 세월호 사건으로 인해 삭제된 일정이 존재합니다");
	   mHelper.setDuration(2500);
	   mHelper.setStyle(Style.INFO);
	   mHelper.show();
       
       return rootView;
   }
}