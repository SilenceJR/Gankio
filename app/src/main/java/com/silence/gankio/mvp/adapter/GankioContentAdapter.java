package com.silence.gankio.mvp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @作者: PJ
 * @创建时间: 2017/12/20 / 14:37
 * @描述: 这是一个 GankioContentAdapter 类.
 */
public class GankioContentAdapter extends FragmentPagerAdapter {

    private List<Fragment> mGankioFragment;
    private String[] mGankioContent;

    public GankioContentAdapter(FragmentManager fm) {
        super(fm);
    }

    public GankioContentAdapter(FragmentManager fm, List<Fragment> gankioFragment, String[] gankioContent) {
        super(fm);
        mGankioFragment = gankioFragment;
        mGankioContent = gankioContent;
    }

    @Override
    public Fragment getItem(int position) {
        return mGankioFragment.get(position);
    }

    @Override
    public int getCount() {
        return mGankioFragment == null ? 0 : mGankioFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mGankioContent[position];
    }
}
