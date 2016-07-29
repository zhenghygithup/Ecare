package com.gd.zhenghy.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.gd.zhenghy.activity.R;
import com.gd.zhenghy.util.ScreenUtils;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;


public class TasksFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView mLv_tasks;
    private HorizontalScrollView mHorizontalScrollView;
    private RadioGroup mRadio_group;

    // private OnFragmentInteractionListener mListener;

    public TasksFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TasksFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TasksFragment newInstance(String param1, String param2) {
        TasksFragment fragment = new TasksFragment();
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
        View view=inflater.inflate(R.layout.fragment_tasks, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mHorizontalScrollView = (HorizontalScrollView) view.findViewById(R.id.hscro_title_tasks);
        OverScrollDecoratorHelper.setUpOverScroll(mHorizontalScrollView);//设置可以弹性滑动
        mLv_tasks = (ListView) view.findViewById(R.id.lv_tasks);
        mRadio_group = (RadioGroup) view.findViewById(R.id.radio_group);
        final int screenHalf = ScreenUtils.getScreenWidth(getActivity())/2;
        mRadio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int scrollX = mHorizontalScrollView.getScrollX();
                System.out.println("scrollX----->"+scrollX);
                RadioButton rb = (RadioButton)mRadio_group.findViewById(checkedId);
                int left = rb.getLeft();
                int leftScreen = left-scrollX;
               mHorizontalScrollView.smoothScrollBy((leftScreen-screenHalf), 0);
               // mHorizontalScrollView.smoothScrollBy(screenHalf, 0);
            }});


    }



    @Override
    public void onClick(View view) {
        switch(view.getId()){
//            case R.id.ll_title_task:
//                int index=mLl_titile_tasks.indexOfChild(view);
//                Util.t(getActivity(),index+"");
//                break;
            case 2:
                break;
            case 3:
                break;
            default:
        }
    }

//    //    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p/>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
