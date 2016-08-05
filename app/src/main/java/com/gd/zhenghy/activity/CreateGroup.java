package com.gd.zhenghy.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.gd.zhenghy.view.RoundImagview;
import com.gd.zhenghy.view.TitleBuilder;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public  class CreateGroup extends BaseActivity implements NumberPicker.OnValueChangeListener,OnItemClickListener {
    @Bind(R.id.titlebar_tv)
    TextView mTitlebarTv;
    @Bind(R.id.titlebar_tv_left)
    TextView mTitlebarTvLeft;
    @Bind(R.id.titlebar_tv_right)
    TextView mTitlebarTvRight;
    @Bind(R.id.titlebar_iv_left)
    ImageView mTitlebarIvLeft;
    @Bind(R.id.titlebar_iv_right)
    ImageView mTitlebarIvRight;
    @Bind(R.id.iv_photo_CreateGroup)
    RoundImagview mIvPhotoCreateGroup;
    @Bind(R.id.iv_camera_CreateGroup)
    ImageView mIvCameraCreateGroup;
    @Bind(R.id.et_groupName_CreateGroup)
    EditText mEtGroupNameCreateGroup;
    @Bind(R.id.tv_chooseRole_CreateGroup)
    TextView mTvChooseRoleCreateGroup;
    @Bind(R.id.pick_selectRole_Create)
    NumberPicker mPickSelectRoleCreate;
    private String[] mRoles;
    private AlertView mMAlertView;
    private Bitmap head;//头像Bitmap
    private TitleBuilder mTitleBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
        mTvChooseRoleCreateGroup.setText(mRoles[newVal]);
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_create_group);
        ButterKnife.bind(this);
        mRoles = new String[]{"Myself", "Someone Else"};
        mPickSelectRoleCreate.setDisplayedValues(mRoles);
        mPickSelectRoleCreate.setMinValue(0);
        mPickSelectRoleCreate.setMaxValue(mRoles.length - 1);
        mPickSelectRoleCreate.setValue(0);
        mPickSelectRoleCreate.setOnValueChangedListener(this);
        mPickSelectRoleCreate.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        mTitleBuilder = new TitleBuilder(this).setTitle("Create Group").setTextLeft("Back").setLeftListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateGroup.this.finish();
            }
        });

    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        mEtGroupNameCreateGroup.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mTitleBuilder.setTextRight("Done");
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if ( editable.length()==0){
                    mTitlebarTvRight.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    public void ViewClick(View v) {

    }

    @OnClick({R.id.titlebar_tv_left, R.id.titlebar_tv_right, R.id.iv_camera_CreateGroup, R.id.tv_chooseRole_CreateGroup})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titlebar_tv_left:
                break;
            case R.id.titlebar_tv_right:
                break;
            case R.id.iv_camera_CreateGroup:
                mMAlertView = new AlertView("Choose Image", null, "Cancel", null,new String[]{"Camera", "Gallery"}, this, AlertView.Style.ActionSheet, this);
                mMAlertView .show();
                break;
            case R.id.tv_chooseRole_CreateGroup:
                if (mPickSelectRoleCreate.getVisibility()==View.GONE){
                    mPickSelectRoleCreate.setVisibility(View.VISIBLE);
                }
               else if (mPickSelectRoleCreate.getVisibility()==View.VISIBLE){
                    mPickSelectRoleCreate.setVisibility(View.GONE);
                }

                break;
        }
    }

    //设置组的头像
    @Override
    public void onItemClick(Object o, int position) {
        switch (position){
            case 0://Camera
                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                        "head.jpg")));
                startActivityForResult(intent2, 2);//采用ForResult打开
                break;
            case 1://Gallery
                Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent1, 1);
                break;
            case -1://Cancel
                break;
            default:
                break;
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode ==-1) {
                    cropPhoto(data.getData());//裁剪图片
                }
                break;
            case 2:
                if (resultCode == -1) {
                    File temp = new File(Environment.getExternalStorageDirectory()
                            + "/head.jpg");
                    cropPhoto(Uri.fromFile(temp));//裁剪图片
                }

                break;
            case 3:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    head = extras.getParcelable("data");

                    if(head!=null){
                        /**
                         * 上传服务器代码
                         */
                        mIvPhotoCreateGroup.setImageBitmap(head);//用ImageView显示出来
                    }
                }
                break;
            default:
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 调用系统的裁剪
     * @param uri
     */
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }
}
