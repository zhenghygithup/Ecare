package com.gd.zhenghy.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ForgotPassword extends BaseActivity {
    private EditText mEtFotgotEmail;
    private Button mBtnFotgotEmail,mBtnFotgotSignInFacebook;
    private ImageView mIvback;
    @Override
    public void initView() {
        setContentView(R.layout.activity_forgot_password);
        mEtFotgotEmail = (EditText) findViewById(R.id.et_forgotPassword_sendEmail);
        mEtFotgotEmail.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.Send || id == EditorInfo.IME_NULL) {
                    attemptSendEmail();
                    return true;
                }
                return false;
            }
        });
        mBtnFotgotEmail = (Button) findViewById(R.id.iv_forgotPassword_sendEmail);

        mBtnFotgotEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                attemptSendEmail();
            }
        });
        mBtnFotgotSignInFacebook = (Button) findViewById(R.id.btn_forgotPassword_signInFacebook);
        mBtnFotgotSignInFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mIvback= (ImageView) findViewById(R.id.iv_back);
        mIvback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ForgotPassword.this.finish();
            }
        });
    }
    private void attemptSendEmail() {


        mEtFotgotEmail.setError(null);

        // Store values at the time of the login attempt.
        String email = mEtFotgotEmail.getText().toString();
        boolean cancel = false;
        View focusView = null;

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEtFotgotEmail.setError(getString(R.string.error_field_required));
            focusView = mEtFotgotEmail;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEtFotgotEmail.setError(getString(R.string.error_invalid_email));
            focusView = mEtFotgotEmail;
            cancel = true;
        }

        }



    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
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
