package com.gd.zhenghy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TestActivity extends BaseActivity {

    private Button mButton;
    private Button mButton1;

    @Override
    public void initView() {
        setContentView(R.layout.activity_test);
        mButton = ((Button) findViewById(R.id.btn));
        mButton1 = ((Button) findViewById(R.id.btn1));
        mButton.setOnClickListener(this);
        mButton1.setOnClickListener(this);


    }

    @Override
    public void initData() {
        mButton.setText("按钮");
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void ViewClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                startActivity(new Intent(this,HomeActivity.class));
                break;
            case R.id.btn1:
                startActivity(new Intent(this,LoginActivity.class));
                break;
            default:
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
