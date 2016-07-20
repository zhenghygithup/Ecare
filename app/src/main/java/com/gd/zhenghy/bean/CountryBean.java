package com.gd.zhenghy.bean;

import com.bigkoo.pickerview.model.IPickerViewData;

/**
 * Created by zhenghy on 2016/7/15.
 */
public class CountryBean implements IPickerViewData {
    private long id;
    private String name;

    public CountryBean(long id, String name){
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    //这个用来显示在PickerView上面的字符串,PickerView会通过IPickerViewData获取getPickerViewText方法显示出来。
    @Override
    public String getPickerViewText() {
        return name;
    }
}

