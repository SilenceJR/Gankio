package com.silence.gankio.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.silence.gankio.R;
import com.silence.gankio.base.inter.IView;
import com.silence.gankio.widget.BaseToolBar;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;
import es.dmoral.toasty.Toasty;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Silence-Dell
 *
 * @time 2017/12/16 3:27
 * @des ${TODO}
 */

public abstract class BaseActivity extends AppCompatActivity implements IView, BGASwipeBackHelper.Delegate {

    private BaseToolBar mToolbar;
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

        initView();
        initDate();
    }

    protected abstract void initView();

    protected abstract void initDate();

    protected BaseToolBar initToolBar(boolean showBlack, View toolbarView) {
        if (null != mToolbar) {
            new IllegalArgumentException("ToolBar is not null");
        }

        mToolbar = new BaseToolBar(this);
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

    protected void initSwipeLayout() {
        mBgaSwipeBackHelper = new BGASwipeBackHelper(this, this);
        mBgaSwipeBackHelper.setSwipeBackEnable(true);//是否可用
        mBgaSwipeBackHelper.setSwipeBackThreshold(0.4f);//滑动关闭阈值
        mBgaSwipeBackHelper.setIsWeChatStyle(false);//wechat样式
    }



    public void initRefreshLayout(SmartRefreshLayout smartRefreshLayout, OnRefreshListener onRefreshListener, boolean loadMoreEnable, OnLoadmoreListener onLoadmoreListener) {
        smartRefreshLayout.setOnRefreshListener(onRefreshListener);
        if (loadMoreEnable) {
            smartRefreshLayout.setOnLoadmoreListener(onLoadmoreListener);
        }
        smartRefreshLayout.setEnableLoadmore(loadMoreEnable);
    }

    public void onRefreshFinish(SmartRefreshLayout smartRefreshLayout) {
        if (smartRefreshLayout.isRefreshing()) {
            smartRefreshLayout.finishRefresh();
        }

        if (!smartRefreshLayout.isEnableRefresh()) {
            smartRefreshLayout.setEnableRefresh(true);
        }
    }

    /**
     * 获取Activity的界面
     *
     * @return
     */
    protected abstract int getActivityContentView();

    public void showToast(String msg, int type) {
        switch (type) {
            case ToastType.ERROR :
                Toasty.error(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                break;
            case ToastType.INFO:
                Toasty.info(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                break;
            case ToastType.NORMAL:
                Toasty.normal(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                break;
            case ToastType.WARNING:
                Toasty.warning(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                break;
            case ToastType.SUCCESS:
                Toasty.success(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onError(String msg, int code) {
        showToast(msg, ToastType.ERROR);
    }

    public class ToastType {
        public static final int ERROR = 0x01;
        public static final int INFO = 0x02;
        public static final int NORMAL = 0x03;
        public static final int WARNING = 0x04;
        public static final int SUCCESS = 0x05;

    }

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
