package com.gd.zhenghy.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;

import com.gd.zhenghy.util.AppManager;
import com.gd.zhenghy.util.LogUtils;

/**
 * Created by zhenghy on 2016/7/8.
 */
public abstract class BaseActivity extends FragmentActivity implements
        View.OnClickListener {
    private static final int ACTIVITY_RESUME = 0;
    private static final int ACTIVITY_STOP = 1;
    private static final int ACTIVITY_PAUSE = 2;
    private static final int ACTIVITY_DESTROY = 3;
    public int activityState;



    public abstract void initView();
    public abstract void initData();
    public abstract void initEvent();


    public abstract void ViewClick(View v);


    @Override
    public void onClick(View v) {
        ViewClick(v);
    }

    /***************************************************************************
     *
     * 打印Activity生命周期
     *
     ***************************************************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.i(AppManager.getAppManager().currentActivity()+"--------------onCreate");
        requestWindowFeature(Window.FEATURE_NO_TITLE); // 取消标题
        AppManager.getAppManager().addActivity(this);
        initView();
        initData();
        initEvent();
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.i(AppManager.getAppManager().currentActivity()+"---------onStart ");

    }

    @Override
    protected void onResume() {
        super.onResume();
        activityState = ACTIVITY_RESUME;
        LogUtils.i(AppManager.getAppManager().currentActivity()+"-----------onResume ");
    }



    @Override
    protected void onStop() {
        super.onStop();
        activityState = ACTIVITY_STOP;
        LogUtils.i(AppManager.getAppManager().currentActivity()+"-------------onStop ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        activityState = ACTIVITY_PAUSE;
        LogUtils.i(AppManager.getAppManager().currentActivity()+"---------------onPause ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.i(AppManager.getAppManager().currentActivity()+"--------------onRestart ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityState = ACTIVITY_DESTROY;
        LogUtils.i(AppManager.getAppManager().currentActivity()+"-----------onDestroy ");
        AppManager.getAppManager().finishActivity(this);
    }
}
