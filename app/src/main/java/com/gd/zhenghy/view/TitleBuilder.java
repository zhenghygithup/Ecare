package com.gd.zhenghy.view;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gd.zhenghy.activity.R;

/**
 * Created by zhenghy on 2016/7/12.用来生成标题栏
 */
public class TitleBuilder {
        TextView tv;
        TextView tv_left;
        TextView tv_right;
        ImageView iv_left;
        ImageView iv_right;
        public TitleBuilder(Activity context) {
            tv = (TextView) context.findViewById(R.id.titlebar_tv);
            tv_left = (TextView) context.findViewById(R.id.titlebar_tv_left);
            tv_right = (TextView) context.findViewById(R.id.titlebar_tv_right);
            iv_left = (ImageView) context.findViewById(R.id.titlebar_iv_left);
            iv_right = (ImageView) context.findViewById(R.id.titlebar_iv_right);
        }
        public TitleBuilder setTitle(String str) {
            tv.setText(str);
            return this;
        }
        public TitleBuilder setTextLeft(String str) {
            tv_left.setVisibility(View.VISIBLE);
            tv_left.setText(str);
            return this;
        }
        public TitleBuilder setTextRight(String str) {
            tv_right.setVisibility(View.VISIBLE);
            tv_right.setText(str);
            return this;
        }
        public TitleBuilder setImageLeftRes(int res) {
            iv_left.setVisibility(View.VISIBLE);
            iv_left.setImageResource(res);
            return this;
        }
        public TitleBuilder setImageRightRes(int res) {
            iv_right.setVisibility(View.VISIBLE);
            iv_right.setImageResource(res);
            return this;
        }
        public TitleBuilder setLeftListener(View.OnClickListener listener) {
            if(tv_left.getVisibility() == View.VISIBLE) {
                tv_left.setOnClickListener(listener);
            }
            if(iv_left.getVisibility() == View.VISIBLE) {
                iv_left.setOnClickListener(listener);
            }
            return this;
        }
        public TitleBuilder setRightListener(View.OnClickListener listener) {
            if(tv_right.getVisibility() == View.VISIBLE) {
                tv_right.setOnClickListener(listener);
            }
            if(iv_right.getVisibility() == View.VISIBLE) {
                iv_right.setOnClickListener(listener);
            }
            return this;
        }
        public TitleBuilder initTitlebar() {
            iv_right.setVisibility(View.GONE);
            iv_left.setVisibility(View.GONE);
            tv_right.setVisibility(View.GONE);
            tv_left.setVisibility(View.GONE);

        return this;
    }

}
