package com.gd.zhenghy.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.gd.zhenghy.bean.GrouplistBean;
import com.gd.zhenghy.util.Util;
import com.gd.zhenghy.util.Utily;
import com.gd.zhenghy.view.RoundImagview;
import com.gd.zhenghy.view.TitleBuilder;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EditGroupInfo extends Activity implements OnItemClickListener,View.OnClickListener {
    @Bind(R.id.iv_GroupPhoto_EditGroup)
    RoundImagview mIvGroupPhotoEditGroup;
    @Bind(R.id.iv_GroupCamera_EditGroup)
    ImageView mIvCameraEditGroup;
    @Bind(R.id.tv_groupName_EditGroup)
    TextView mTvGroupNameEditGroup;
    @Bind(R.id.iv_editGroupName_editGroup)
    ImageView mIvEditGroupNameEditGroup;
    @Bind(R.id.tv_numMember_editGroup)
    TextView mTvNumMemberEditGroup;
    @Bind(R.id.gv_memberDetail_EditGroup)
    GridView mGvMemberEditGroup;
    private AlertView mMAlertView;
    private Bitmap head;//头像Bitmap
    private TitleBuilder mTitleBuilder;
    private ArrayList<GrouplistBean> mGrouList;
    OnItemClickListener onGridviewitem;
    AlertView mAlertView;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_group_info);
        ButterKnife.bind(this);
        mIvCameraEditGroup.setOnClickListener(this);
        mIvEditGroupNameEditGroup.setOnClickListener(this);
        mTitleBuilder = new TitleBuilder(this).setTitle("Edit Group info").setTextRight("Done").setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             // TODO: 2016/8/1右边done的点击事件
                EditGroupInfo.this.finish();
//                int version = Integer.valueOf(android.os.Build.VERSION.SDK);
//                if(version  >= 5)
//                {
//                    overridePendingTransition(R.anim.activity_right_out, R.anim.activity_right_in);
//
//                }
            }
        });

        //gridview的点击事件
        initEventGridview();

    }

    private void initEventGridview() {

        //点击第一个gridview的点击事件
        onGridviewitem = new OnItemClickListener() {
            @Override
            public void onItemClick(Object o, int position) {
                // TODO: 2016/8/1 删除组的点击事件

                Util.t(EditGroupInfo.this,position+"");
            }
        };

        //接受传过来的数据
        mGrouList = (ArrayList<GrouplistBean>) getIntent().getSerializableExtra("mGrouList");
        GrouplistAdapter adapter = new GrouplistAdapter(mGrouList);
        mGvMemberEditGroup.setAdapter(adapter);
        mGvMemberEditGroup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0){
                    mAlertView=new AlertView("Delete group", null, "Cancel", new String[]{"Confirm"}, null, EditGroupInfo.this, AlertView.Style.ActionSheet, onGridviewitem);
                    mAlertView.show();
                }else if (i==1){
                    Util.t(EditGroupInfo.this,"you cannot remove yourself from group！");
                }
            }
        });


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_GroupCamera_EditGroup:
                mMAlertView = new AlertView("Choose Image", null, "Cancel", null,new String[]{"Camera", "Gallery"}, this, AlertView.Style.ActionSheet, this);
                mMAlertView .show();
                break;
            case R.id.iv_editGroupName_editGroup:
                Bundle bundle=new Bundle();
                String groupName=mTvGroupNameEditGroup.getText().toString();
                bundle.putString("groupName",groupName);
                Utily.go2Activity(EditGroupInfo.this,EditGroupName.class,bundle);
                break;
        }
    }

    @Override
    public void onItemClick(Object o, int position) {
        switch (position) {
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
                    if (resultCode == -1) {
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

                        if (head != null) {
                            /**
                             * 上传服务器代码
                             */
                            mIvGroupPhotoEditGroup.setImageBitmap(head);//用ImageView显示出来
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
         *
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
                view = View.inflate(EditGroupInfo.this, R.layout.item_gridview_editgroupinfor, null);
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            if (i==0){
                viewHolder.tvmemberNamegv.setText("Delete this  group");

                viewHolder.tvmemberRolegv.setVisibility(View.GONE);
                viewHolder.ivphotogroupdetail.setImageResource(R.mipmap.delete_group);
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
            public final ImageView ivphotogroupdetail;
            public final TextView tvmemberNamegv;
            public final TextView tvmemberRolegv;
            public final View root;

            public ViewHolder(View root) {
                ivphotogroupdetail = (ImageView) root.findViewById(R.id.iv_photo_groupdetail);
                tvmemberNamegv = (TextView) root.findViewById(R.id.tv_memberName_gv);
                tvmemberRolegv = (TextView) root.findViewById(R.id.tv_memberRole_gv);
                this.root = root;
            }
        }
    }


}
