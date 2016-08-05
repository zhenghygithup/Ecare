package com.gd.zhenghy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.gd.zhenghy.bean.GrouplistBean;
import com.gd.zhenghy.util.Utily;
import com.gd.zhenghy.view.RoundImagview;
import com.gd.zhenghy.view.TitleBuilder;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GroupName extends BaseActivity {


    @Bind(R.id.gv_member_GroupDetail)
    GridView mGvMemberGroupDetail;
    private ArrayList<GrouplistBean> mGrouList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
    }
    @Override
    public void initView() {
        setContentView(R.layout.activity_group_detail);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        String groupName = bundle.getString("groupName");
        TitleBuilder mTitleBuilder = new TitleBuilder(this).setTitle(groupName).setTextLeft("Back").setLeftListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GroupName.this.finish();
            }
        }).setTextRight("Edit").setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(GroupName.this,EditGroupInfo.class);
                intent.putExtra("mGrouList",mGrouList);
               startActivity(intent);
                int version = Integer.valueOf(android.os.Build.VERSION.SDK);
                if(version  >= 5)
                {
                    overridePendingTransition(R.anim.zoomin, R.anim.zoomout);

                }

            }
        });
    }

    @Override
    public void initData() {
        //为gridView添加数据
        mGrouList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            GrouplistBean g = new GrouplistBean();
            g.setSelectId(R.mipmap.select_group);
            g.setGroupName("GroupName");
            g.setPhotoimg("https://123p1.sogoucdn.com/imgu/2016/07/20160727150837_122.jpg");
            g.setYourRole("Owner");
            mGrouList.add(g);
        }

    }

    @Override
    public void initEvent() {
        GrouplistAdapter adapter=new GrouplistAdapter(mGrouList);
        mGvMemberGroupDetail.setAdapter(adapter);
        mGvMemberGroupDetail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0){
                    Utily.go2Activity(GroupName.this,AddMember.class);
                }else{

                    Bundle bundle=new Bundle();
                    GrouplistBean grouplistBean=mGrouList.get(i-1);
                    bundle.putParcelable("grouplistBean", grouplistBean);
                    Utily.go2Activity(GroupName.this,Profile.class,bundle);


                }
            }
        });

    }

    @Override
    public void ViewClick(View v) {

    }


    //gridview适配器
    class GrouplistAdapter extends BaseAdapter {
        private List<GrouplistBean> list;

        public GrouplistAdapter(List list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size()+1;
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
                view = View.inflate(GroupName.this, R.layout.item_gridview_groupdetail, null);
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            if (i==0){
                viewHolder.tvmemberNamegv.setText("Add group member");
                viewHolder.tvmemberRolegv.setVisibility(View.GONE);
            }else{
                viewHolder.tvmemberNamegv.setText(list.get(i-1).getGroupName());
                viewHolder.tvmemberRolegv.setText(list.get(i-1).getYourRole());
                ImageLoader.getInstance().displayImage(list.get(i-1).getPhotoimg(), viewHolder.ivphotogroupdetail);
            }
            AbsListView.LayoutParams param = new AbsListView.LayoutParams(
                    android.view.ViewGroup.LayoutParams.FILL_PARENT,
                    700);
            view.setLayoutParams(param);


            return view;
        }

        public class ViewHolder {
            public final RoundImagview ivphotogroupdetail;
            public final TextView tvmemberNamegv;
            public final TextView tvmemberRolegv;
            public final View root;

            public ViewHolder(View root) {
                ivphotogroupdetail = (RoundImagview) root.findViewById(R.id.iv_photo_groupdetail);
                tvmemberNamegv = (TextView) root.findViewById(R.id.tv_memberName_gv);
                tvmemberRolegv = (TextView) root.findViewById(R.id.tv_memberRole_gv);
                this.root = root;
            }
        }
    }

}
