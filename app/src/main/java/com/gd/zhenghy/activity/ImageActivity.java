package com.gd.zhenghy.activity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.gd.zhenghy.util.Callback;
import com.gd.zhenghy.util.Invoker;
import com.gd.zhenghy.util.Util;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ImageActivity extends AppCompatActivity {

    private RelativeLayout rl;
    private ImageView iv;
    private TextView tv;
    private EditText et;
    private SeekBar sb1;
    private SeekBar sb2;
    private String path;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        initView();
        iv.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @SuppressWarnings("deprecation")
                    @Override
                    public void onGlobalLayout() {
                        iv.getLayoutParams().height = iv.getWidth();
                        iv.getViewTreeObserver().removeGlobalOnLayoutListener(
                                this);
                    }
                });
    }

    private void initView() {
        path = getIntent().getStringExtra("path");
        rl = (RelativeLayout) findViewById(R.id.rl);
        iv = (ImageView) findViewById(R.id.iv);
        tv = (TextView) findViewById(R.id.tv);
        et = (EditText) findViewById(R.id.et);
        sb1 = (SeekBar) findViewById(R.id.sb1);
        sb2 = (SeekBar) findViewById(R.id.sb2);
        ImageLoader.getInstance().displayImage("file://" + path, iv);
        sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                int padding = 200 - progress;
                iv.setPadding(padding, padding, padding, padding);
                iv.invalidate();
            }
        });
        sb2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                tv.setTextSize(30 + progress);
            }
        });
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tv.setText(s.toString());
            }
        });
    }

    private Bitmap bmp;

    public void onSave(View v) {
        new Invoker(new Callback() {
            @Override
            public boolean onRun() {
                return Util.saveImageToGallery(getApplicationContext(), bmp,
                        path.endsWith(".png"));
            }

            @Override
            public void onBefore() {
                pd = new ProgressDialog(ImageActivity.this);
                pd.setCancelable(false);
                pd.show();
                bmp = Util.convertViewToBitmap(rl);
            }

            @Override
            public void onAfter(boolean b) {
                if (b) {
                    Util.t(getApplicationContext(), "保存成功");
                    pd.dismiss();
                    finish();
                } else {
                    Util.t(getApplicationContext(), "保存失败");
                    pd.dismiss();
                }
            }
        }).start();

    }

}