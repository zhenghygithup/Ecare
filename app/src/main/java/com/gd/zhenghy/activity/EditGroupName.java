package com.gd.zhenghy.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.gd.zhenghy.view.TitleBuilder;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EditGroupName extends Activity implements View.OnClickListener {

    @Bind(R.id.et_groupName_EditGroupName)
    EditText mEtGroupNameEditGroupName;
    private TitleBuilder mTitleBuilder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_group_name);
        ButterKnife.bind(this);
        mTitleBuilder = new TitleBuilder(this).setTitle("Edit Group name").setTextLeft("Close").setLeftListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditGroupName.this.finish();
            }
        });


        //设置EditText的默认值
        String groupName=getIntent().getExtras().getString("groupName");
        mEtGroupNameEditGroupName.setText(groupName);

        mEtGroupNameEditGroupName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mTitleBuilder.setTextRight("Save").setRightListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // TODO: 2016/8/1 save的点击事件
                    }
                });
            }
            @Override
            public void afterTextChanged(Editable editable) {
            
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_groupName_EditGroupName:

                break;

        }
    }
}
