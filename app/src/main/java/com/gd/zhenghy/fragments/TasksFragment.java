package com.gd.zhenghy.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.gd.zhenghy.activity.R;
import com.gd.zhenghy.util.LogUtils;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;
import java.util.List;


public class TasksFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ExpandableListView mLv_tasks;
    private TabLayout mTabLayout;
    private RecyclerView mRecyclerView;
    private List<String> listTasksDetail;
    private int mPositon;

    private OnAddTaskListener mListener;//点击传值。

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
        initdata();
        initEvent();
        return view;
    }
    private void initEvent() {
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mPositon=  tab.getPosition();

                final Toast toast = Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.setText(tab.getPosition() + ":" + tab.getText());
                toast.show();
                // TODO: 2016/8/4 根据每一个item点击来更新listview;
                //listTasksDetail=listTasksDetail.set(0,positon+"");

                mRecyclerView.setAdapter(new SimpleAdapter(mRecyclerView,mPositon,getActivity()));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void initdata() {
        String[] mData = new String[20];
        listTasksDetail=new ArrayList<>();
        {
            for(int i=0;i<titles.length;i++) {
                mTabLayout.addTab(mTabLayout.newTab().setText((titles[i])));
                mData[i] = "data" + i;
            }
        }

    }
    public static final String[] titles = {"Medication", "Exercise", "Diet", "Personal care",
            "Appointment", "Change in condition", "Behaviour Tracking", "Hygiene", "Safety and Security"};
    private void initView(View view) {
        // mLv_tasks = (ExpandableListView) view.findViewById(R.id.lv_tasks);
        // OverScrollDecoratorHelper.setUpOverScroll(mLv_tasks);//设置可以弹性滑动
        mTabLayout = (TabLayout) view.findViewById(R.id.tabs_task_title);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_task);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new SimpleAdapter(mRecyclerView,mPositon,getActivity()));

    }



    private class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.ViewHolder> {
        private static final int UNSELECTED = -1;
        private RecyclerView recyclerView;
        private int selectedItem = UNSELECTED;
        private int index = 0;
        Activity mContext;

        public SimpleAdapter(RecyclerView recyclerView,int position, Activity mContext) {
            this.recyclerView = recyclerView;
            this.index=position;
            this.mContext=mContext;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_recycleview_task, parent, false);
            return new ViewHolder(itemView);
        }
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.bind(position);
        }

        @Override
        public int getItemCount() {
            return 100;
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private ImageButton mImageView;
            private ExpandableLayout expandableLayout;
            private TextView expandButton;
            private int position;
            public ViewHolder(View itemView) {
                super(itemView);
                mImageView= (ImageButton) itemView.findViewById(R.id.iv_add_task);
                expandableLayout = (ExpandableLayout) itemView.findViewById(R.id.expandable_layout);
                expandButton = (TextView) itemView.findViewById(R.id.expand_button);
                expandButton.setOnClickListener(this);
                mImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        TextView circle= (TextView) mContext.findViewById(R.id.tv_selectTask_count);
                        int num=Integer.valueOf(circle.getText().toString());
                        LogUtils.e(num+"");
                        num++;
                        onAddImageViewPressed(num);

                    }
                });
            }

            public void bind(int position) {
                this.position = position;
                mImageView.setImageResource(R.mipmap.add_task);
                expandButton.setText(position + ". Tap to expand");
                expandButton.setSelected(false);
                expandableLayout.collapse(false);
                ((TextView)( expandableLayout.getChildAt(0))).setText("这是第"+index+"的内容");
            }
            @Override
            public void onClick(View view) {
                ViewHolder holder = (ViewHolder) recyclerView.findViewHolderForAdapterPosition(selectedItem);
                if (holder != null) {
                    holder.expandButton.setSelected(false);
                    holder.expandableLayout.collapse();
                }
                if (position == selectedItem) {
                    selectedItem = UNSELECTED;
                } else {
                    expandButton.setSelected(true);
                    expandableLayout.expand();
                    selectedItem = position;
                }
            }
        }
    }


    //    // TODO: Rename method, update argument and hook method into UI event
    public void onAddImageViewPressed(int num) {
        if (mListener != null) {
            mListener.onaddTask(num);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAddTaskListener) {
            mListener = (OnAddTaskListener) context;
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
    public interface OnAddTaskListener {
        // TODO: Update argument type and name
        void onaddTask(int num);
    }


}
