package com.illusion.yuljeon;

import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;

public class DialogPreference extends android.preference.DialogPreference {
	  
	public DialogPreference(Context context, AttributeSet attrs) {
	    super(context, attrs);
	  }

	  public DialogPreference(Context context, AttributeSet attrs, int defStyle) {
	    super(context, attrs, defStyle);
	  }
	  
	  @Override
	  public void onClick(DialogInterface dialog, int which) {
	    super.onClick(dialog, which);

	    if (which == DialogInterface.BUTTON_POSITIVE) {
	      new CacheActivity().clearApplicationData();
	    } 
	  }
	}