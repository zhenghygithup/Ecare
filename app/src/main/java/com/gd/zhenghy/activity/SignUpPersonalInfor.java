package com.gd.zhenghy.activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.gd.zhenghy.util.Utily;
import com.gd.zhenghy.view.RoundImagview;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SignUpPersonalInfor extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.iv_back_signUp)
    ImageView mIvBackSignUp;
    @Bind(R.id.iv_photo_signUp)
    RoundImagview mIvPhotoSignUp;
    @Bind(R.id.iv_camera_signUp)
    ImageView mIvCameraSignUp;
    @Bind(R.id.et_firstName_signUp)
    EditText mEtFirstNameSignUp;
    @Bind(R.id.et_surName_signUp)
    EditText mEtSurNameSignUp;
    @Bind(R.id.rb_signup_male)
    RadioButton mRbSignupMale;
    @Bind(R.id.rb_signup_female)
    RadioButton mRbSignupFemale;
    @Bind(R.id.radioGroup_signup_select)
    RadioGroup mRadioGroupSignupSelect;
    @Bind(R.id.tv_dateBirth_signUp)
    TextView mTvDateBirthSignUp;
    @Bind(R.id.iv_countryFlag_signUp)
    ImageView mIvCountryFlagSignUp;
    @Bind(R.id.tv_countryCode_signUp)
    TextView mTvCountryCodeSignUp;
    @Bind(R.id.et_mobile_signUp)
    EditText mEtMobileSignUp;
    @Bind(R.id.btn_signUp_signUp)
    Button mBtnSignUpSignUp;
    @Bind(R.id.tv_signIn_signUp)
    TextView mTvSignInSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public void initView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sign_up_personal_infor);
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        mTvSignInSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utily.go2Activity(SignUpPersonalInfor.this,LoginActivityTest.class);
            }
        });
    }

    @Override
    public void ViewClick(View v) {

    }

    @Override
    public void onClick(View view) {

    }
}
