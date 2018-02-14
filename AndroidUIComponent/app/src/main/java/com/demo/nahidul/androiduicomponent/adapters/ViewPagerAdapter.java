package com.demo.nahidul.androiduicomponent.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nahidul on 22/1/18.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mFragmentItems;
    private Context mContext;

    public ViewPagerAdapter(FragmentManager fm, Context mContext) {
        super(fm);

        mFragmentItems = new ArrayList<>();
        this.mContext = mContext;
    }

    @Override
    public Fragment getItem(int position) {
        return (mFragmentItems.size() == 0)? null : mFragmentItems.get(position);
    }

    @Override
    public int getCount() {
        return 1;
    }

    public void addFragmentItem(Fragment f){
        mFragmentItems.add(f);
        notifyDataSetChanged();
    }
}
