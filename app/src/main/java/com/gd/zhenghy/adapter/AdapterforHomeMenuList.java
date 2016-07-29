package com.gd.zhenghy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gd.zhenghy.activity.R;
import com.gd.zhenghy.bean.MenuList;

import java.util.List;

/**
 * Created by zhenghy on 2016/7/8.
 */
public class AdapterforHomeMenuList extends BaseAdapter {
    private List<MenuList> mList;
    private Context mContext;

    public AdapterforHomeMenuList(Context mContext, List<MenuList> stringList) {
        this.mContext = mContext;
        mList = stringList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = View.inflate(mContext, R.layout.item_home_menulist, null);
            viewHolder = new ViewHolder();
            viewHolder.tv_title_dl = ((TextView) view.findViewById(R.id.tv_title_dl));
            viewHolder.iv_drawable_dl = ((ImageView) view.findViewById(R.id.iv_drawable_dl));
            viewHolder.iv_highlighter_dl = ((ImageView) view.findViewById(R.id.iv_highlighter_dl));
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.tv_title_dl.setText(mList.get(i).getTitle());
        viewHolder.iv_drawable_dl.setImageResource(mList.get(i).getDrawableId());
        //viewHolder.iv_highlighter_dl.setImageResource(mList.get(i).getHighlighter());
        //
        return view;
    }

    class ViewHolder {
        TextView tv_title_dl;
        ImageView iv_drawable_dl;
        ImageView iv_highlighter_dl;
    }

}
