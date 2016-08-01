package com.gd.zhenghy.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.gd.zhenghy.activity.GroupDetail;
import com.gd.zhenghy.activity.R;
import com.gd.zhenghy.bean.GrouplistBean;
import com.gd.zhenghy.util.Util;
import com.gd.zhenghy.view.RoundImagview;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GroupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GroupFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private LinearLayout mLl_nothing;
    private ListView mLv_group;
    private List<GrouplistBean> mGrouList;


    public GroupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GroupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GroupFragment newInstance(String param1, String param2) {
        GroupFragment fragment = new GroupFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_group, container, false);
        mLl_nothing = (LinearLayout) view.findViewById(R.id.ll_nothing);
        mLv_group = (ListView) view.findViewById(R.id.lv_group_selectGroup);
        initdata();
        initEvent();
        return view;
    }

    private void initEvent() {
        GrouplistAdapter adapter=new GrouplistAdapter(mGrouList);
        mLv_group.setAdapter(adapter);
        //OverScrollDecoratorHelper.setUpOverScroll(mLv_group);
        mLv_group.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle=new Bundle();
                bundle.putString("groupName",mGrouList.get(i).getGroupName());
                Intent intent=new Intent(getActivity(), GroupDetail.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void initdata() {
        //联网获取数据
        mGrouList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            GrouplistBean g = new GrouplistBean();
            g.setSelectId(R.mipmap.select_group);
            g.setGroupName("GroupName");
            g.setPhotoimg("https://123p1.sogoucdn.com/imgu/2016/07/20160727150837_122.jpg");
            g.setYourRole("Owner");
            mGrouList.add(g);
        }
        //设置是否有数据？
        if (mGrouList.size()>6){
            mLl_nothing.setVisibility(View.VISIBLE);
            mLv_group.setVisibility(View.GONE);
        }else {
            mLl_nothing.setVisibility(View.GONE);
        }
    }


    //listview适配器
    class GrouplistAdapter extends BaseAdapter {
        private List<GrouplistBean> list;
        private int clickPosition = -1;

        public GrouplistAdapter(List list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder = null;
            if (view == null) {
                view = View.inflate(getActivity(), R.layout.item_groups_listview, null);
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.tvlvgroupName.setText(list.get(i).getGroupName());
            viewHolder.tvlvgroupRole.setText(list.get(i).getYourRole());
            viewHolder.rblvgroups.setImageResource(list.get(i).getSelectId());
            ImageLoader.getInstance().displayImage(list.get(i).getPhotoimg(), viewHolder.ivphotogroup);

            viewHolder.rblvgroups.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkAlertView(i);
                }
            });
            return view;
        }

        private void checkAlertView(final int i) {
            OnItemClickListener on=new OnItemClickListener() {
                @Override
                public void onItemClick(Object o, int position) {
                    switch (position){
                        case 0:
                            for (int i = 0; i <list.size() ; i++) {
                                list.get(i).setSelectId(R.mipmap.select_group);
                            }
                            list.get(i).setSelectId(R.mipmap.selected_group);
                            notifyDataSetChanged();
                            break;
                        case -1:
                            break;
                    }
                    Util.t(getActivity(),position+"");
                }
            };
            new AlertView("Do you want to switch groups", "Switching group will change all settings accosciated with that group.", "Cancel", null, new String[]{"Yes I want to"}, getActivity(), AlertView.Style.ActionSheet,on).setCancelable(true).show();
        }
        public class ViewHolder {
            public final RoundImagview ivphotogroup;
            public final TextView tvlvgroupName;
            public final TextView tvlvgroupRole;
            public final ImageView rblvgroups;
            public final View root;
            public ViewHolder(View root) {
                ivphotogroup = (RoundImagview) root.findViewById(R.id.iv_photo_group);
                tvlvgroupName = (TextView) root.findViewById(R.id.tv_lv_groupName);
                tvlvgroupRole = (TextView) root.findViewById(R.id.tv_lv_groupRole);
                rblvgroups = (ImageView) root.findViewById(R.id.iv_lv_groups);
                this.root = root;
            }
        }
    }


}
