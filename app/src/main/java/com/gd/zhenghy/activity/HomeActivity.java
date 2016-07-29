package com.gd.zhenghy.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.gd.zhenghy.adapter.AdapterforHomeMenuList;
import com.gd.zhenghy.bean.MenuList;
import com.gd.zhenghy.fragments.GroupFragment;
import com.gd.zhenghy.fragments.GuidesFragment;
import com.gd.zhenghy.fragments.JournalFragment;
import com.gd.zhenghy.fragments.NotificationsFragment;
import com.gd.zhenghy.fragments.ReminderSettingsFragment;
import com.gd.zhenghy.fragments.SettingFragment;
import com.gd.zhenghy.fragments.TaskSchedulingFragment;
import com.gd.zhenghy.fragments.TasksFragment;
import com.gd.zhenghy.util.Constant;
import com.gd.zhenghy.util.Util;
import com.gd.zhenghy.view.DragLayout;
import com.gd.zhenghy.view.DragLayout.DragListener;
import com.gd.zhenghy.view.TitleBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomeActivity extends BaseActivity implements NotificationsFragment.OnFragmentNOtificationInteractionListener,SettingFragment.OnFragmentSettingInteractionListener{

    private DragLayout dl;
    private FrameLayout fl_content_home;
    private ListView lv_menulist;
    private ImageView iv_setting_dl,iv_photo_dl;
    private List<MenuList> mMenulist;
    private List<Fragment> mFranmentList=new ArrayList<Fragment>();
    private int currentId=2;
    private long exitTime = 0;
    private AdapterforHomeMenuList mAdapterforHomeMenuList;
    private FragmentTransaction mFt;
    private TitleBuilder mTitleBuilder;
    private static String path="/sdcard/myHead/";//sd路径
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initDragLayout();
        initFragment();
        initData();
        initEvent();
    }

    public void initView() {
        Util.initImageLoader(this);
        setContentView(R.layout.activity_home);
        iv_setting_dl = (ImageView) findViewById(R.id.iv_setting_dl);
        iv_photo_dl= ((ImageView) findViewById(R.id.iv_photo_dl));
        //设置头像
        Bitmap bt = BitmapFactory.decodeFile(path + "head.jpg");//从Sd中找头像，转换成Bitmap
        if(bt!=null){
            @SuppressWarnings("deprecation")
            Drawable drawable = new BitmapDrawable(bt);//转换成drawable
            iv_photo_dl.setImageDrawable(drawable);

        }else{
            /**
             *  如果SD里面没有则需要从服务器取头像，取回来的头像再保存在SD中
             *
             */
        }
        fl_content_home = ((FrameLayout) findViewById(R.id.fl_content_home));
        lv_menulist = (ListView) findViewById(R.id.lv_menulist);
        mTitleBuilder = new TitleBuilder(this).setImageLeftRes(R.mipmap.ic_launcher);
    }
    private void initFragment() {
        for (int i = 0; i < 8; i++) {
            Bundle bundle = new Bundle();
            bundle.putInt("index", i);
            switch (i) {
                case 0:
                    NotificationsFragment notificationsFragment = new NotificationsFragment();
                    notificationsFragment.setArguments(bundle);
                    mFranmentList.add(notificationsFragment);
                    break;
                case 1:
                    GroupFragment groupFragment = new GroupFragment();
                    groupFragment.setArguments(bundle);
                    mFranmentList.add(groupFragment);
                    break;
                case 2:
                    JournalFragment JournalFragment = new JournalFragment();
                    JournalFragment.setArguments(bundle);
                    mFranmentList.add(JournalFragment);
                    break;
                case 3:
                    GuidesFragment guidesFragment1 = new GuidesFragment();
                    guidesFragment1.setArguments(bundle);
                    mFranmentList.add(guidesFragment1);
                    break;
                case 4:
                    TasksFragment tasksFragment = new TasksFragment();
                    tasksFragment.setArguments(bundle);
                    mFranmentList.add(tasksFragment);
                    break;
                case 5:
                    TaskSchedulingFragment taskSchedulingFragment = new TaskSchedulingFragment();
                    taskSchedulingFragment.setArguments(bundle);
                    mFranmentList.add(taskSchedulingFragment);
                    break;
                case 6:
                    ReminderSettingsFragment reminderSettingsFragment = new ReminderSettingsFragment();
                    reminderSettingsFragment.setArguments(bundle);
                    mFranmentList.add(reminderSettingsFragment);
                    break;
                case 7:
                    SettingFragment settingsFragment = new SettingFragment();
                    settingsFragment.setArguments(bundle);
                    mFranmentList.add(settingsFragment);
                    break;
            }

        }
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_content_home, mFranmentList.get(2)).commit();
        initTitle(2);
    }

    private void initDragLayout() {
        dl = (DragLayout) findViewById(R.id.dl);
        dl.setDragListener(new DragListener() {
            @Override
            public void onOpen() {
                lv_menulist.smoothScrollToPosition(new Random().nextInt(20));
            }

            @Override
            public void onClose() {
            }

            @Override
            public void onDrag(float percent) {
                // ViewHelper.setAlpha(iv_icon, 1 - percent);
            }
        });
    }

    //设置左边菜单数据
    @Override
    public void initData() {
        String menu[] = new String[]{"Notification", "Groups",
                "journal", "Guides", "Tasks", "Task Scheduling",
                "Reminder settings"};
        int drambleId[]=new int[]{R.drawable.selector_notification_dl,R.drawable.selector_groups_dl,R.drawable.selector_journal_dl,R.drawable.selector_guides_dl,R.drawable.selector_tasks_dl,R.drawable.selector_taskscheduling_dl,R.drawable.selector_reminder_dl};
        int highlightid=R.mipmap.highlighter;
        mMenulist = new ArrayList<MenuList>();
        for (int i = 0; i <menu.length ; i++) {
            MenuList menuList=new MenuList();
            menuList.setTitle(menu[i]);
            menuList.setDrawableId(drambleId[i]);
           // menuList.setHighlighter(highlightid);
            mMenulist.add(menuList);
        }

        mAdapterforHomeMenuList = new AdapterforHomeMenuList(this,mMenulist);
        lv_menulist.setAdapter(mAdapterforHomeMenuList);

    }

    @Override
    public void initEvent() {
        /*
          根据不同的标题切换不同fragment
         */
        lv_menulist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {

                changeFragment(position);
                dl.close();

            }
        });
        mTitleBuilder.setLeftListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dl.open();
            }
        });
        iv_setting_dl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dl.close();
                changeFragment(Constant.IV_SETTING_DL);
            }
        });

    }




    @Override
    public void ViewClick(View v) {

    }

    @Override
    protected void onResume() {
        super.onResume();
      //  loadImage();

    }
/*
切换页面
 */

    private void changeFragment(int i) {
        // TODO Auto-generated method stub
        mFt = getSupportFragmentManager().beginTransaction();
        Fragment targetFragment = mFranmentList.get(i);

        Fragment currentFragment = mFranmentList.get(currentId);

        if (i==currentId) {
            mFt.show(targetFragment);
        } else if(targetFragment.isAdded()) {
            mFt.show(targetFragment).hide(currentFragment).commit();
        } else {
            mFt.add(R.id.fl_content_home, targetFragment).hide(currentFragment)
                    .commit();
        }

        initTitle(i);
        currentId = i;
    }

/*
根据页面切换不同的标题栏
 */
    private void initTitle(int i) {
        mTitleBuilder.initTitlebar();
        if (i<7){
             mTitleBuilder.setTitle(mMenulist.get(i).getTitle()).setImageLeftRes(R.mipmap.menu);
        }else {
            mTitleBuilder.setTitle(null);
        }
        switch (i){
            case 0:
                mTitleBuilder.setTextRight("Clear All");
                break;
            case 1:
                mTitleBuilder.setTextRight("Create").setRightListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(HomeActivity.this,CreateGroup.class));
                    }
                });

                break;
            case 2:

                mTitleBuilder.setTextRight("Post");
                break;
            case 3:
                break;
            case 4:
                mTitleBuilder.setImageRightRes(R.mipmap.downloaded);
                break;
            case 5:
                break;
            case 6:
                mTitleBuilder.setImageRightRes(R.mipmap.sync_cal);
                break;
            case 7:
                mTitleBuilder.setTitle("settings").setTextRight("Done").setRightListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dl.open();
                    }
                });
                break;
        }
   }


    @Override
    public void OnFragmentNotificationInteraction(String uri) {
        Util.t(HomeActivity.this, uri);
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出应用程序",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);// 退出代码
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void OnFragmentSettingInteraction(Bitmap bitmap) {
        iv_photo_dl.setImageBitmap(bitmap);
    }


    //    private void loadImage() {
//        new Invoker(new Callback() {
//            @Override
//            public boolean onRun() {
//                adapter.addAll(Util.getGalleryPhotos(HomeActivity.this));
//                return adapter.isEmpty();
//            }
//
//            @Override
//            public void onAfter(boolean b) {
//                adapter.notifyDataSetChanged();
//                if (b) {
//                    tv_noimg.setVisibility(View.VISIBLE);
//                } else {
//                    tv_noimg.setVisibility(View.GONE);
//                    String s = "file://" + adapter.getItem(0);
//                    ImageLoader.getInstance().displayImage(s, iv_icon);
//                    ImageLoader.getInstance().displayImage(s, iv_bottom);
//                }
//            }
//
//
//            @Override
//            public void onBefore() {
//            }
//
//        }).start();


   // }


}
