package com.gd.zhenghy.activity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.gd.zhenghy.bean.CountryBean;
import com.gd.zhenghy.view.TitleBuilder;

import java.util.ArrayList;

public class ChangePhoneNumber extends BaseActivity{

    private TitleBuilder mTitleBuilder;
    private ArrayList<CountryBean> optionsCountryItems = new ArrayList<>();
    OptionsPickerView pvOptionsCountry;
    View vCountryMasker;
    private TextView mTv_selectCountry;

    @Override
    public void initView() {
        setContentView(R.layout.activity_change_phone_number);
        vCountryMasker=findViewById(R.id.vCountryMasker);
        mTitleBuilder = new TitleBuilder(this);
        mTitleBuilder.setTitle("Change Phone Number").setTextLeft("Back").setTextRight("Done");
        EditText et_countryCode= (EditText) findViewById(R.id.et_countryCode);
        EditText et_phoneNumber= (EditText) findViewById(R.id.et_phoneNumber);
        mTv_selectCountry = (TextView) findViewById(R.id.tv_selectCountry);
        mTv_selectCountry.setOnClickListener(this);


    }

    @Override
    public void initData() {
        /*
        国家数据
         */
        pvOptionsCountry=new OptionsPickerView(this);
        optionsCountryItems.add(new CountryBean(0,"中国"));
        optionsCountryItems.add(new CountryBean(1,"日本"));
        optionsCountryItems.add(new CountryBean(2,"菲律宾"));
        optionsCountryItems.add(new CountryBean(3,"埃及"));
        optionsCountryItems.add(new CountryBean(4,"埃塞俄比亚"));
        optionsCountryItems.add(new CountryBean(5,"加拿大"));
        optionsCountryItems.add(new CountryBean(6,"韩国"));
        optionsCountryItems.add(new CountryBean(7,"美国"));
        optionsCountryItems.add(new CountryBean(8,"古巴"));


        //三级联动效果
        pvOptionsCountry.setPicker(optionsCountryItems,null,null,true);
        pvOptionsCountry.setTitle("选择");
        pvOptionsCountry.setCyclic(false, false, true);
        //设置默认选中的三级项目
        //监听确定选择按钮
        pvOptionsCountry.setSelectOptions(0, 1, 1);
        pvOptionsCountry.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                //返回的分别是三个级别的选中位置
                String tx = optionsCountryItems.get(options1).getPickerViewText();
                //                        + options2Items.get(options1).get(option2)
                //                        + options3Items.get(options1).get(option2).get(options3).getPickerViewText();
                mTv_selectCountry.setText(tx);
                vCountryMasker.setVisibility(View.GONE);
            }
        });
        //点击弹出选项选择器
        mTv_selectCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //先隐藏键盘再弹出view
                ((InputMethodManager)getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(ChangePhoneNumber.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                pvOptionsCountry.show();
            }
        });

    }

    @Override
    public void initEvent() {
        mTitleBuilder.setLeftListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //左边back点击事件
                ChangePhoneNumber.this.finish();
            }
        }).setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //右边done点击事件
            }
        });
    }

    @Override
    public void ViewClick(View v) {
        switch (v.getId()){
            case R.id.tv_selectCountry:


                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
