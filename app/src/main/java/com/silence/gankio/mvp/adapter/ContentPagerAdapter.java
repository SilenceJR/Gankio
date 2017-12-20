package com.silence.gankio.mvp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @作者: PJ
 * @创建时间: 2017/12/20 / 14:12
 * @描述: 这是一个 ContentPagerAdapter 类.
 */
public class ContentPagerAdapter extends FragmentPagerAdapter{

    private List<Fragment> mFragments;
    private String[] mTabName;

    public ContentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public ContentPagerAdapter(FragmentManager fm, List<Fragment> fragments, String[] tabName) {
        super(fm);
        mFragments = fragments;
        mTabName = tabName;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabName[position];
    }
}
