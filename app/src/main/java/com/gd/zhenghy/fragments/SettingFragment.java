package com.gd.zhenghy.fragments;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.gd.zhenghy.activity.ChangeAddress;
import com.gd.zhenghy.activity.ChangePassword;
import com.gd.zhenghy.activity.ChangePhoneNumber;
import com.gd.zhenghy.activity.LoginActivityTest;
import com.gd.zhenghy.activity.R;
import com.gd.zhenghy.util.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

import static android.view.View.OnClickListener;
import static android.view.View.inflate;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingFragment extends Fragment implements OnItemClickListener,OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Bitmap head;//头像Bitmap
    private static String path="/sdcard/myHead/";//sd路径
    private ImageView mIv_photo_setting;
    private ImageView mIv_photobg_setting;
    private ImageView mIv_camera_setting;
    private AlertView mMAlertView;
    private LinearLayout mLl_changePassword_settings;
    private LinearLayout mLl_changeNumber_settings;
    private LinearLayout mLl_changeAddress_settings;
    private LinearLayout mLl_signOut_settings;
    private LinearLayout mLl_termsAndConditions_settings;
    private OnFragmentSettingInteractionListener mListener;

    public SettingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingFragment newInstance(String param1, String param2) {
        SettingFragment fragment = new SettingFragment();
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
        // Inflate the layout for this fragment]
        View view= inflate(getActivity(),R.layout.fragment_setting,null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        ScrollView scrollView= (ScrollView) view.findViewById(R.id.scrollview);
        OverScrollDecoratorHelper.setUpOverScroll(scrollView);//设置可以弹性滑动
        mIv_photo_setting = ((ImageView) view.findViewById(R.id.iv_photo_setting));
        mIv_photobg_setting = ((ImageView) view.findViewById(R.id.iv_photobg_setting));
        mIv_camera_setting = ((ImageView) view.findViewById(R.id.iv_camera_setting));
        mLl_changePassword_settings = (LinearLayout) view.findViewById(R.id.ll_changePassword_settings);
        mLl_changeNumber_settings = (LinearLayout) view.findViewById(R.id.ll_changeNumber_settings);
        mLl_changeAddress_settings = (LinearLayout) view.findViewById(R.id.ll_changeAddress_settings);
        mLl_signOut_settings = (LinearLayout) view.findViewById(R.id.ll_signOut_settings);
        mLl_termsAndConditions_settings = (LinearLayout) view.findViewById(R.id.ll_termsAndConditions_settings);
        mIv_camera_setting.setOnClickListener(this);
        mLl_changePassword_settings.setOnClickListener(this);
        mLl_changeNumber_settings.setOnClickListener(this);
        mLl_changeAddress_settings.setOnClickListener(this);
        mLl_signOut_settings.setOnClickListener(this);
        mLl_termsAndConditions_settings.setOnClickListener(this);
        Bitmap bt = BitmapFactory.decodeFile(path + "head.jpg");//从Sd中找头像，转换成Bitmap
        if(bt!=null){
            @SuppressWarnings("deprecation")
            Drawable drawable = new BitmapDrawable(bt);//转换成drawable
            mIv_photo_setting.setImageDrawable(drawable);
            mIv_photobg_setting.setImageBitmap(doBlur(bt,50,false));

        }else{
            /**
             *  如果SD里面没有则需要从服务器取头像，取回来的头像再保存在SD中
             *
             */
        }

    }

    //控件点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_camera_setting:
                mMAlertView = new AlertView("Choose Image", null, "Cancel", null,new String[]{"Camera", "Gallery"}, getActivity(), AlertView.Style.ActionSheet, this);
                mMAlertView .show();
                break;
            case R.id.ll_changePassword_settings:
                Intent changePassword=new Intent(getContext(),ChangePassword.class);
                startActivity(changePassword);
                break;
            case R.id.ll_changeNumber_settings:
                Intent changePhoneNumber=new Intent(getContext(),ChangePhoneNumber.class);
                startActivity(changePhoneNumber);
                break;
            case R.id.ll_changeAddress_settings:
                Intent changeAddress=new Intent(getContext(),ChangeAddress.class);
                startActivity(changeAddress);
                break;
            case R.id.ll_signOut_settings:
                Intent signout=new Intent(getContext(),LoginActivityTest.class);
                startActivity(signout);
                break;
            case R.id.ll_termsAndConditions_settings:
               // Intent termsAndConditions=new Intent(getContext(),TermsAndConditions.class);
              //startActivity(termsAndConditions);
                termsAlertView();
                break;
        }

    }

    /**
    *设置条约和条款的点击事件
    *@author zhenghy
    *created at 2016/7/18 15:04
    */
    private void termsAlertView() {
        OnItemClickListener on=new OnItemClickListener() {
            @Override
            public void onItemClick(Object o, int position) {
                Util.t(getActivity(),position+"");
            }
        };

       AlertView termsAlert=new AlertView("Terms and Conditions", "   I accept and agree to all eCare terms and conditions",null,null,
               new String[]{"Done", "Read Terms and Conditions"},
                getActivity(), AlertView.Style.Alert, on);
        termsAlert.show();
    }


    //设置头像的点击事件
    @Override
    public void onItemClick(Object o, int position) {
        switch (position){
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
                if (resultCode ==-1) {
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

                    if(head!=null){
                        /**
                         * 上传服务器代码
                         */

                        setPicToView(head);//保存在SD卡中
                        mIv_photo_setting.setImageBitmap(head);//用ImageView显示出来
                        //Bitmap bitmap=doBlur(head,50,false);
                      mIv_photobg_setting.setImageBitmap(doBlur(head,50,false));
                        onButtonCamera(head);
                    }
                }
                break;
            default:
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    };
    /**
     * 调用系统的裁剪
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

    private void setPicToView(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return;
        }
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();// 创建文件夹
        String fileName =path + "head.jpg";//图片名字
        try {
            b = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭流
                if (b!=null){
                b.flush();
                b.close();
                }else{
                    Util.t(getActivity(),"null");}
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonCamera(Bitmap bitmap) {
        if (mListener != null) {
            mListener.OnFragmentSettingInteraction(bitmap);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentSettingInteractionListener) {
            mListener = (OnFragmentSettingInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentSettingInteractionListener {
        // TODO: Update argument type and name
        void OnFragmentSettingInteraction(Bitmap bitmap);
    }
/*
模糊背景方法
 */
    public  Bitmap doBlur(Bitmap sentBitmap, int radius, boolean canReuseInBitmap) {
        Bitmap bitmap;
        if (canReuseInBitmap) {
            bitmap = sentBitmap;
        } else {
            bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);
        }

        if (radius < 1) {
            return (null);
        }

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        int[] pix = new int[w * h];
        bitmap.getPixels(pix, 0, w, 0, 0, w, h);

        int wm = w - 1;
        int hm = h - 1;
        int wh = w * h;
        int div = radius + radius + 1;

        int r[] = new int[wh];
        int g[] = new int[wh];
        int b[] = new int[wh];
        int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
        int vmin[] = new int[Math.max(w, h)];

        int divsum = (div + 1) >> 1;
        divsum *= divsum;
        int dv[] = new int[256 * divsum];
        for (i = 0; i < 256 * divsum; i++) {
            dv[i] = (i / divsum);
        }

        yw = yi = 0;

        int[][] stack = new int[div][3];
        int stackpointer;
        int stackstart;
        int[] sir;
        int rbs;
        int r1 = radius + 1;
        int routsum, goutsum, boutsum;
        int rinsum, ginsum, binsum;

        for (y = 0; y < h; y++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            for (i = -radius; i <= radius; i++) {
                p = pix[yi + Math.min(wm, Math.max(i, 0))];
                sir = stack[i + radius];
                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);
                rbs = r1 - Math.abs(i);
                rsum += sir[0] * rbs;
                gsum += sir[1] * rbs;
                bsum += sir[2] * rbs;
                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }
            }
            stackpointer = radius;

            for (x = 0; x < w; x++) {

                r[yi] = dv[rsum];
                g[yi] = dv[gsum];
                b[yi] = dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (y == 0) {
                    vmin[x] = Math.min(x + radius + 1, wm);
                }
                p = pix[yw + vmin[x]];

                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[(stackpointer) % div];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi++;
            }
            yw += w;
        }
        for (x = 0; x < w; x++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            yp = -radius * w;
            for (i = -radius; i <= radius; i++) {
                yi = Math.max(0, yp) + x;

                sir = stack[i + radius];

                sir[0] = r[yi];
                sir[1] = g[yi];
                sir[2] = b[yi];

                rbs = r1 - Math.abs(i);

                rsum += r[yi] * rbs;
                gsum += g[yi] * rbs;
                bsum += b[yi] * rbs;

                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }

                if (i < hm) {
                    yp += w;
                }
            }
            yi = x;
            stackpointer = radius;
            for (y = 0; y < h; y++) {
                // Preserve alpha channel: ( 0xff000000 & pix[yi] )
                pix[yi] = (0xff000000 & pix[yi]) | (dv[rsum] << 16) | (dv[gsum] << 8) | dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (x == 0) {
                    vmin[y] = Math.min(y + r1, hm) * w;
                }
                p = x + vmin[y];

                sir[0] = r[p];
                sir[1] = g[p];
                sir[2] = b[p];

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[stackpointer];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi += w;
            }
        }

        bitmap.setPixels(pix, 0, w, 0, 0, w, h);

        return (bitmap);
    }


}

