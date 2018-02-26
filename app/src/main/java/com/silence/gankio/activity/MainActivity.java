package com.silence.gankio.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.silence.gankio.R;
import com.silence.gankio.base.BaseActivity;
import com.silence.gankio.base.BaseFragment;
import com.silence.gankio.di.Man;
import com.silence.gankio.fragment.GankioAndroidFragment;
import com.silence.gankio.view.MainView;

import java.util.LinkedList;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainView, NavigationView.OnNavigationItemSelectedListener, Toolbar.OnMenuItemClickListener {

    @BindView(R.id.fl_conn)
    FrameLayout mFlConn;
    @BindView(R.id.navigation)
    NavigationView mNavigation;
    @BindView(R.id.drawerlayout)
    DrawerLayout mDrawerlayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    //    @BindViews({R.id.tab_android, R.id.tab_ios, R.id.tab_welfare})
    //    List<TabView> mTabs;

    private LinkedList<BaseFragment> mTabFragments;
    private BaseFragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Man man = new Man();

    }

    @Override
    protected void initView() {
        initToolBar();

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
        //        ToolBarCommView toolBarCommView = new ToolBarCommView(this);
        //        toolBarCommView.setDefTitle().showBack(false);
        //        initToolBar(true, toolBarCommView).setDefaultRedBg();

        mToolbar.setTitle("首页");
        setSupportActionBar(mToolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerlayout, mToolbar, R.string.open_drawer, R.string.close_drawer);
        toggle.syncState();
        mDrawerlayout.addDrawerListener(toggle);

        mToolbar.setOnMenuItemClickListener(this);
        mNavigation.setItemIconTintList(null);
        mNavigation.setNavigationItemSelectedListener(this);


    }


    @Override
    protected void initDate() {
        mTabFragments = new LinkedList<>();

        mTabFragments.add(GankioAndroidFragment.newInstance());

        //        mTabFragments.add(GankioIOSFragment.newInstance());
        //        mTabFragments.add(GankioWelfareFragment.newInstance());

        //        for (int i = 0; i < mTabs.size(); i++) {
        //            TabView tabView = mTabs.get(i);
        //            final BaseFragment fragment = mTabFragments.get(i);
        //            final int finalI = i;
        //            tabView.setOnCheckedChangeListener(new TabView.OnCheckedChangeListener() {
        //                @Override
        //                public void onChecked(TabView tab, boolean isChecked) {
        //                    if (isChecked) {
        //                        changeFragment(fragment);
        //                        for (int j = 0; j < mTabs.size(); j++) {
        //                            if (finalI != j) {
        //                                mTabs.get(j).setChecked(false);
        //                            }
        //                        }
        //                    }
        //                }
        //            });
        //
        //        }
        //
        //        mTabs.get(0).setChecked(true);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_conn, GankioAndroidFragment.newInstance(), GankioAndroidFragment.class.getSimpleName()).commit();

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_0:
                changeFragment(mTabFragments.get(0));
                break;
            case R.id.item_1:
                Toast.makeText(this, "首页", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_2:
                Toast.makeText(this, "关于", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        mDrawerlayout.closeDrawers();
        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu1:
                Toast.makeText(this, "menu1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu2:
                Toast.makeText(this, "menu2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu3:
                Toast.makeText(this, "menu3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu4:
                Toast.makeText(this, "menu4", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu5:
                Toast.makeText(this, "menu5", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu6:
                Toast.makeText(this, "menu6", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;

        }
        return true;
    }
}
