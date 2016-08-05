package com.gd.zhenghy.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.gd.zhenghy.bean.GrouplistBean;
import com.gd.zhenghy.util.Util;
import com.gd.zhenghy.view.RoundImagview;
import com.gd.zhenghy.view.TitleBuilder;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Profile extends Activity {

    @Bind(R.id.iv_photo_Profile)
    RoundImagview mIvPhotoProfile;
    @Bind(R.id.tv_userName_profile)
    TextView mTvUserNameProfile;
    @Bind(R.id.tv_userRole_profile)
    TextView mTvUserRoleProfile;
    @Bind(R.id.tv_Email_profile)
    TextView mTvEmailProfile;
    @Bind(R.id.tv_MobileNumber_profile)
    TextView mTvMobileNumberProfile;
    @Bind(R.id.tv_Address_profile)
    TextView mTvAddressProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Util.initImageLoader(this);
        ButterKnife.bind(this);
        TitleBuilder titleBuilder=new TitleBuilder(this);
        titleBuilder.setTextLeft("Back").setLeftListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Profile.this.finish();
            }
        }).setTitle("Profile");

        initData();
    }

    private void initData() {
        GrouplistBean grouplistBean=getIntent().getParcelableExtra("grouplistBean");
        ImageLoader.getInstance().displayImage(grouplistBean.getPhotoimg(),mIvPhotoProfile);
        mTvUserNameProfile.setText(grouplistBean.getGroupName());
        mTvUserRoleProfile.setText(grouplistBean.getYourRole());


    }
}
