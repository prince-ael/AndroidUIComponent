package com.demo.nahidul.androiduicomponent.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.demo.nahidul.androiduicomponent.R;
import com.demo.nahidul.androiduicomponent.adapters.ViewPagerAdapter;
import com.demo.nahidul.androiduicomponent.interfaces.ControlPanelListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class SampleFragment extends Fragment {


    ImageView mIndexTable;
    ImageView mMessage;
    ImageView mAdd;

    ViewPager mViewPager;

    ViewPagerAdapter mAdapter;

    ControlPanelListener mCpListener;

    Context mContext;

    int currentPage = 0;

    public SampleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sample, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mIndexTable = view.findViewById(R.id.iv_btn_index_table);
        mMessage = view.findViewById(R.id.iv_btn_message);
        mAdd = view.findViewById(R.id.iv_btn_add);

        mViewPager = view.findViewById(R.id.vp_sample);

        mAdapter = new ViewPagerAdapter(getFragmentManager(),mContext);

        mViewPager.setAdapter(mAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mAdapter.addFragmentItem(new IndexTableFragment());
        mAdapter.addFragmentItem(new MessageFragment());
        mAdapter.addFragmentItem(new AddFragment());

        mIndexTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentPage != 0){
                    mViewPager.setCurrentItem(0);
                    IndexTableFragment itf = (IndexTableFragment) mAdapter.getItem(0);
                    mCpListener = itf;
                    mCpListener.onControl();
                }
            }
        });

        mMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentPage != 1){
                    mViewPager.setCurrentItem(1);
                    MessageFragment mf = (MessageFragment) mAdapter.getItem(1);
                    mCpListener = mf;
                    mCpListener.onControl();
                }
            }
        });

        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentPage != 2){
                    mViewPager.setCurrentItem(2);
                }
            }
        });
    }
}
