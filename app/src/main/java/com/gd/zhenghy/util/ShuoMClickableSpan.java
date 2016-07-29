package com.gd.zhenghy.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import com.gd.zhenghy.activity.LoginActivityTest;

public class ShuoMClickableSpan extends ClickableSpan {
	
	String string;
	Context context;
	public ShuoMClickableSpan(String str,Context context){
		super();
		this.string = str;
		this.context = context;
	}
	
	
	@Override
	public void updateDrawState(TextPaint ds) {
		ds.setColor(Color.BLUE);
	}


	@Override
	public void onClick(View widget) {
		Intent intent
		 = new Intent();
		intent.setClass(context, LoginActivityTest.class);
		context.startActivity(intent);

	}

}
