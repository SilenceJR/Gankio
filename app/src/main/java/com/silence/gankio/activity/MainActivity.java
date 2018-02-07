package com.silence.gankio.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import com.silence.gankio.R;
import com.silence.gankio.adapter.ContentPagerAdapter;
import com.silence.gankio.base.BaseActivity;
import com.silence.gankio.fragment.GankioContentFragment;
import com.silence.gankio.view.MainView;
import com.silence.gankio.widget.ToolBarCommView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainView {

    @BindView(R.id.fl_conn)
    FrameLayout mFlConn;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    private List<Fragment> mTabFragments;
    private String[] mTabIndicators = {"首页", "看看", "逛逛", "我的"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initContent();

    }

    @Override
    protected void initView() {
        initToolBar();

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setSelectedTabIndicatorHeight(0);
        ViewCompat.setElevation(mTabLayout, 10);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initToolBar() {
        ToolBarCommView toolBarCommView = new ToolBarCommView(this);
        toolBarCommView.setDefTitle().showBack(false);
        initToolBar(true, toolBarCommView).setDefaultRedBg();
    }

    private void initContent() {
        mTabFragments = new ArrayList<>();

        mTabFragments.add(new GankioContentFragment());
        mTabFragments.add(new Fragment());
        mTabFragments.add(new Fragment());
        mTabFragments.add(new Fragment());

        ContentPagerAdapter contentAdapter = new ContentPagerAdapter(getSupportFragmentManager(), mTabFragments, mTabIndicators);
        mViewPager.setAdapter(contentAdapter);
        //调用这个方法 保持viewpager 不会回收view的个数  OK
        mViewPager.setOffscreenPageLimit(mTabFragments.size());
    }

    @Override
    protected void initDate() {

    }

    @Override
    protected int getActivityContentView() {
        return R.layout.activity_main;
    }

    /**
     * 监听用户按返回键
     *
     * @param keyCode
     * @param event
     * @return
     */

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(false);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onNetFinish() {

    }
}
