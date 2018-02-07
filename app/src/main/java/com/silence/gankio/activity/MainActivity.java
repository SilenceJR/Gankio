package com.silence.gankio.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import com.silence.gankio.R;
import com.silence.gankio.base.BaseActivity;
import com.silence.gankio.base.BaseFragment;
import com.silence.gankio.fragment.GankioAndroidFragment;
import com.silence.gankio.fragment.GankioIOSFragment;
import com.silence.gankio.fragment.GankioWelfareFragment;
import com.silence.gankio.view.MainView;
import com.silence.gankio.widget.TabView;
import com.silence.gankio.widget.ToolBarCommView;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;

public class MainActivity extends BaseActivity implements MainView {

    @BindView(R.id.fl_conn)
    FrameLayout mFlConn;
    @BindViews({R.id.tab_android, R.id.tab_ios, R.id.tab_welfare})
    List<TabView> mTabs;

    private LinkedList<BaseFragment> mTabFragments;
    private BaseFragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void initView() {
        initToolBar();

        mTabFragments = new LinkedList<>();

        mTabFragments.add(GankioAndroidFragment.newInstance());
        mTabFragments.add(GankioIOSFragment.newInstance());
        mTabFragments.add(GankioWelfareFragment.newInstance());

        for (int i = 0; i < mTabs.size(); i++) {
            TabView tabView = mTabs.get(i);
            final BaseFragment fragment = mTabFragments.get(i);
            final int finalI = i;
            tabView.setOnCheckedChangeListener(new TabView.OnCheckedChangeListener() {
                @Override
                public void onChecked(TabView tab, boolean isChecked) {
                    if (isChecked) {
                        changeFragment(fragment);
                        for (int j = 0; j < mTabs.size(); j++) {
                            if (finalI != j) {
                                mTabs.get(j).setChecked(false);
                            }
                        }
                    }
                }
            });

        }

        mTabs.get(0).setChecked(true);




    }

    private void changeFragment(BaseFragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (null != mCurrentFragment) {
            transaction.hide(mCurrentFragment);
        }
        this.mCurrentFragment = fragment;
        if (!fragment.isAdded()) {
            transaction.add(R.id.fl_conn, fragment, fragment.getClass().getSimpleName());
        }
        transaction.show(fragment).commit();

    }

    private void initToolBar() {
        ToolBarCommView toolBarCommView = new ToolBarCommView(this);
        toolBarCommView.setDefTitle().showBack(false);
        initToolBar(true, toolBarCommView).setDefaultRedBg();
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
