package com.gd.zhenghy.activity;

import android.os.Bundle;
import android.view.View;

import com.gd.zhenghy.view.TitleBuilder;

public class ChangePassword extends BaseActivity {
    private TitleBuilder mTitleBuilder;
    @Override
    public void initView() {
        setContentView(R.layout.activity_change_password);
        mTitleBuilder=new TitleBuilder(this);
        mTitleBuilder.setTitle("Change Password").setTextLeft("Back").setTextRight("Done");
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        mTitleBuilder.setLeftListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //左边back点击事件
                ChangePassword.this.finish();
            }
        }).setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //右边done点击事件
            }
        });

    }

    @Override
    public void ViewClick(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
