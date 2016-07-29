package com.gd.zhenghy.view;

/**
 * *有弹性的HorizonScrollView
 * 实现下拉弹回和上拉弹回
 * Created by zhenghy on 2016/7/13.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.HorizontalScrollView;

public class ReboundHorizonScrollView extends HorizontalScrollView {

        private static final int MAX_X_OVERSCROLL_DISTANCE = 100;
        private Context mContext;
        private int mMaxXOverscrollDistance;

        public ReboundHorizonScrollView(Context context) {
            super(context);
            // TODO Auto-generated constructor stub
            mContext = context;
            initBounceDistance();
        }
        public ReboundHorizonScrollView(Context context, AttributeSet attrs) {
            super(context, attrs);
            // TODO Auto-generated constructor stub
            mContext = context;
            initBounceDistance();
        }
        public ReboundHorizonScrollView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            // TODO Auto-generated constructor stub
            mContext = context;
            initBounceDistance();
        }

        private void initBounceDistance(){
            final DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
            mMaxXOverscrollDistance = (int) (metrics.density * MAX_X_OVERSCROLL_DISTANCE);
        }

        @Override
        protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent){
            //这块是关键性代码
            return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, mMaxXOverscrollDistance, maxOverScrollY, isTouchEvent);
        }

    }