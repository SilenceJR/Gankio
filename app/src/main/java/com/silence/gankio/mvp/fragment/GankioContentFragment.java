package com.silence.gankio.mvp.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.silence.gankio.R;
import com.silence.gankio.base.BaseFragment;
import com.silence.gankio.mvp.adapter.GankioContentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @作者: PJ
 * @创建时间: 2017/12/20 / 14:20
 * @描述: 这是一个 GankioContentFragment 类.
 */
public class GankioContentFragment extends BaseFragment {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private String[] mGankioContent = {"Android", "福利", "IOS", "全部"};
    private List<Fragment> mGankioFragment;

    @Override
    protected View getContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gankio_content, container, false);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mGankioFragment = new ArrayList<>();

        mGankioFragment.add(new GankioAndroidFragment());
        mGankioFragment.add(new GankioWelfareFragment());
        mGankioFragment.add(new GankioIOSFragment());

        GankioContentAdapter gankioContentAdapter = new GankioContentAdapter(getChildFragmentManager(), mGankioFragment, mGankioContent);
        mViewPager.setAdapter(gankioContentAdapter);
        mViewPager.setOffscreenPageLimit(mGankioContent.length);

        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setSelectedTabIndicatorHeight(0);
        ViewCompat.setElevation(mTabLayout, 5);
        mTabLayout.setupWithViewPager(mViewPager);

    }


    @Override
    public void NetFinish() {

    }



}
