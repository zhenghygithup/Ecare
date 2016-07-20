package com.gd.zhenghy.util;

public interface Callback {
	void onBefore();

	boolean onRun();

	void onAfter(boolean b);
}
