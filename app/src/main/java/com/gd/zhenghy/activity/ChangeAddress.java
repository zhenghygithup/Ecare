package com.gd.zhenghy.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.gd.zhenghy.bean.CountryBean;
import com.gd.zhenghy.view.TitleBuilder;

import java.util.ArrayList;

public class ChangeAddress extends BaseActivity {
    private EditText etNameSettings;
    private EditText etStreetSettings;
    private EditText etAreaSettings;
    private EditText etStateSettings;
    private TextView tvCountrySettings;
    private EditText etCodeSettings;
    private View vCountryMasker;
    private TitleBuilder mTitleBuilder;
    private ArrayList<CountryBean> optionsCountryItems = new ArrayList<>();
    OptionsPickerView pvOptionsCountry;
    @Override
    public void initView() {
        setContentView(R.layout.activity_change_address);
        mTitleBuilder = new TitleBuilder(this);
        mTitleBuilder.setTitle("Change Address").setTextLeft("Back").setTextRight("Done");
            etNameSettings = (EditText) findViewById(R.id.et_name_settings);
            etStreetSettings = (EditText) findViewById(R.id.et_street_settings);
            etAreaSettings = (EditText) findViewById(R.id.et_area_settings);
            etStateSettings = (EditText) findViewById(R.id.et_state_settings);
            tvCountrySettings = (TextView) findViewById(R.id.tv_country_settings);
            tvCountrySettings.setOnClickListener(this);
            etCodeSettings = (EditText) findViewById(R.id.et_code_settings);
            vCountryMasker = findViewById(R.id.vCountryMasker);
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
                tvCountrySettings.setText(tx);
                vCountryMasker.setVisibility(View.GONE);
            }
        });
        //点击弹出选项选择器
        tvCountrySettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                ChangeAddress.this.finish();
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
        switch(v.getId()){
            case R.id.tv_country_settings:

                break;
            case 2:
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
