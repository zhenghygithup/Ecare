package com.gd.zhenghy.activity;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gd.zhenghy.util.ShuoMClickableSpan;
import com.gd.zhenghy.util.Utily;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SignUp extends BaseActivity {

    @Bind(R.id.et_signUp_email)
    EditText mEtSignUpEmail;
    @Bind(R.id.et_signUp_password)
    EditText mEtSignUpPassword;
    @Bind(R.id.et_signUp_confirmPassword)
    EditText mEtSignUpConfirmPassword;
    @Bind(R.id.btn_signUp_next)
    Button mBtnSignUpNext;
    @Bind(R.id.btn_signUp_withFacebook)
    Button mBtnSignUpWithFacebook;
    @Bind(R.id.tv_signUp_signInPage)
    TextView mTvSignUpSignInPage;

    public SignUp() {
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        mBtnSignUpNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utily.go2Activity(SignUp.this, SignUpPersonalInfor.class);
            }
        });

        mBtnSignUpWithFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        String signInPage = "Sign in page";
        SpannableString spansignInPage = new SpannableString(signInPage);
        ClickableSpan clickttt = new ShuoMClickableSpan(signInPage, this);
        spansignInPage.setSpan(clickttt, 0, signInPage.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        mTvSignUpSignInPage.setText("if you have an existing account but don't remember password then click forgot password on ");
        mTvSignUpSignInPage.append(spansignInPage);
        mTvSignUpSignInPage.append(".");
        mTvSignUpSignInPage.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    public void ViewClick(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
