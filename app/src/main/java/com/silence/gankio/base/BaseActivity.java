package com.silence.gankio.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.silence.gankio.R;
import com.silence.gankio.base.inter.IView;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Silence-Dell
 *
 * @time 2017/12/16 3:27
 * @des ${TODO}
 */

public abstract class BaseActivity extends AppCompatActivity implements IView, BGASwipeBackHelper.Delegate {

    private BaseToolbar mToolbar;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    LinearLayout mBaseLlRoot;
    private Unbinder mBind;
    private BGASwipeBackHelper mBgaSwipeBackHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initSwipeLayout();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_view);

        mBaseLlRoot = findViewById(R.id.base_ll_root);

        FrameLayout baseFl = findViewById(R.id.base_fl);
        baseFl.addView(LayoutInflater.from(this).inflate(getActivityContentView(), baseFl, false));
        mBind = ButterKnife.bind(this);
    }

    protected BaseToolbar initToolBar(boolean showBlack, View toolbarView) {
        if (null != mToolbar) {
            new IllegalArgumentException("ToolBar is not null");
        }

        mToolbar = new BaseToolbar(this);
        mToolbar.addView(toolbarView);
        mBaseLlRoot.addView(mToolbar, 0);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        mToolbar.fitTranslucentStatus(showBlack);
        return mToolbar;
    }

    protected void setToolbarBgColor(int color) {
        if (null != mToolbar)
            mToolbar.setBackgroundColor(color);
    }

    protected void hideToolbar() {
        if (null != mToolbar)
            mToolbar.setVisibility(View.GONE);
    }

    protected void initToolbarDefaultBg() {
        if (null != mToolbar)
            mToolbar.setDefaultRedBg();
    }

    protected void initSwipeLayout() {
        mBgaSwipeBackHelper = new BGASwipeBackHelper(this, this);
        mBgaSwipeBackHelper.setSwipeBackEnable(true);//是否可用
        mBgaSwipeBackHelper.setSwipeBackThreshold(0.4f);//滑动关闭阈值
        mBgaSwipeBackHelper.setIsWeChatStyle(false);//wechat样式
    }



    public void initRefreshLayout(SwipeRefreshLayout swipeRefreshLayout, boolean loadMoreEnable, SwipeRefreshLayout.OnRefreshListener onRefreshListener) {
        swipeRefreshLayout.setOnRefreshListener(onRefreshListener);
        swipeRefreshLayout.setEnabled(loadMoreEnable);
    }
    /**
     * 获取Activity的界面
     *
     * @return
     */
    protected abstract int getActivityContentView();

    @Override
    public void addDisposable(Disposable d) {
        mCompositeDisposable.add(d);
    }

    @Override
    protected void onDestroy() {
        mCompositeDisposable.clear();
        mBind.unbind();
        super.onDestroy();
    }

    protected void setSwipeBackEnable(boolean enable) {
        mBgaSwipeBackHelper.setSwipeBackEnable(enable);
    }

    @Override
    public boolean isSupportSwipeBack() {
        return true;
    }

    @Override
    public void onSwipeBackLayoutSlide(float slideOffset) {

    }

    @Override
    public void onSwipeBackLayoutCancel() {

    }

    @Override
    public void onSwipeBackLayoutExecuted() {
        mBgaSwipeBackHelper.swipeBackward();
    }

    @Override
    public void onBackPressed() {
        if (mBgaSwipeBackHelper.isSliding()) {
            return;
        }
        mBgaSwipeBackHelper.backward();
    }
}
