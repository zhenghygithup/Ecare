package com.gd.zhenghy.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.gd.zhenghy.view.TitleBuilder;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddMember extends BaseActivity implements NumberPicker.OnValueChangeListener {

    @Bind(R.id.et_MemberName_AddMember)
    EditText mEtMemberNameAddMember;
    @Bind(R.id.et_MemberEmail_AddMember)
    EditText mEtMemberEmailAddMember;
    @Bind(R.id.pick_selectRole_AddMember)
    NumberPicker mPickSelectRoleAddMember;
    @Bind(R.id.tv_pickContacts_addMember)
    TextView mTvPickContactsAddMember;
    @Bind(R.id.tv_choose_AddMember)
    TextView mTvChooseAddMember;
    private String[] mRoles;
    private TitleBuilder mTitleBuilder;

    @Override
    public void initView() {
        setContentView(R.layout.activity_add_member);
        ButterKnife.bind(this);
        mRoles = new String[]{"Family", "Owner"};
        mPickSelectRoleAddMember.setDisplayedValues(mRoles);
        mPickSelectRoleAddMember.setMinValue(0);
        mPickSelectRoleAddMember.setMaxValue(mRoles.length - 1);
        mPickSelectRoleAddMember.setValue(0);
        mPickSelectRoleAddMember.setOnValueChangedListener(this);
        mPickSelectRoleAddMember.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        mTitleBuilder = new TitleBuilder(this).setTitle("Add Member").setTextLeft("Back").setLeftListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddMember.this.finish();
            }
        }).setTextRight("Done").setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置右边done的点击事件
            }
        });
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


    @Override
    public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
        mTvChooseAddMember.setText(mRoles[newVal]);
    }

    @OnClick({R.id.tv_choose_AddMember, R.id.tv_pickContacts_addMember})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_choose_AddMember:
                if (mPickSelectRoleAddMember.getVisibility()==View.GONE){
                    mPickSelectRoleAddMember.setVisibility(View.VISIBLE);
                }
                else if (mPickSelectRoleAddMember.getVisibility()==View.VISIBLE){
                    mPickSelectRoleAddMember.setVisibility(View.GONE);
                }
                break;

        }
    }

}
