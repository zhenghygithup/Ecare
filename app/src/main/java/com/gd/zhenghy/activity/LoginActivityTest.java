package com.gd.zhenghy.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivityTest extends BaseActivity implements View.OnClickListener{
    private EditText mEtLoginEmail;
    private EditText mEtLoginPassword;
    private Button mBtnLoginSignIn;
    private Button mBtnLoginSignFacebook;
    private Button mBtnLoginSignUp;
    private TextView mTvFotgotPassword;
    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    @Override
    public void initView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login_activity_test);
            mEtLoginEmail = (EditText) findViewById(R.id.et_login_email);
            mEtLoginPassword = (EditText) findViewById(R.id.et_login_password);
            mEtLoginPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                    if (id == R.id.login || id == EditorInfo.IME_NULL) {
                        attemptLogin();
                        return true;
                    }
                    return false;
                }
            });
        mBtnLoginSignIn = (Button) findViewById(R.id.btn_login_signIn);
        mBtnLoginSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
        mBtnLoginSignFacebook = (Button) findViewById(R.id.btn_login_signFacebook);
        mBtnLoginSignUp = (Button) findViewById(R.id.btn_Login_signUp);
        mBtnLoginSignUp.setOnClickListener(this);
        mTvFotgotPassword= (TextView) findViewById(R.id.tv_forgotPassword_login);
        mTvFotgotPassword.setOnClickListener(this);
    }

    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEtLoginEmail.setError(null);
        mEtLoginPassword.setError(null);

        // Store values at the time of the login attempt.
        String email = mEtLoginEmail.getText().toString();
        String password = mEtLoginPassword.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mEtLoginPassword.setError(getString(R.string.error_invalid_password));
            focusView = mEtLoginPassword;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEtLoginEmail.setError(getString(R.string.error_field_required));
            focusView = mEtLoginEmail;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEtLoginEmail.setError(getString(R.string.error_invalid_email));
            focusView = mEtLoginEmail;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            //showProgress(true);
            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);
        }
    }


    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }

            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            //showProgress(false);

            if (success) {
                //  finish();
                startActivity(new Intent(LoginActivityTest.this,HomeActivity.class));
            } else {
                mEtLoginPassword.setError(getString(R.string.error_incorrect_password));
                mEtLoginPassword.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            //howProgress(false);
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    public void ViewClick(View v) {

        switch(v.getId()){
            case R.id.tv_forgotPassword_login:
                Intent ForgotPassword=new Intent(LoginActivityTest.this,ForgotPassword.class);
                startActivity(ForgotPassword);
                break;
            case R.id. btn_Login_signUp:
                Intent SignUp=new Intent(LoginActivityTest.this,SignUp.class);
                startActivity(SignUp);
                break;
            case 3:
                break;
            default:
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
}
