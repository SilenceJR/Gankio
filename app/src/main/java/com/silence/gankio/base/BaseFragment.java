package com.silence.gankio.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.silence.gankio.base.inter.IView;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @作者: PJ
 * @创建时间: 2017/12/20 / 14:20
 * @描述: 这是一个 BaseFragment 类.
 */
public abstract class BaseFragment extends Fragment implements IView {

    private Unbinder mUnbinder;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private View mContentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = getContentView(inflater, container, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, mContentView);
        return mContentView;
    }

    protected abstract View getContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }


    public void initRefreshLayout(SmartRefreshLayout smartRefreshLayout, OnRefreshListener onRefreshListener, boolean loadMoreEnable, OnLoadmoreListener onLoadmoreListener) {
        smartRefreshLayout.setOnRefreshListener(onRefreshListener);
        if (loadMoreEnable) {
            smartRefreshLayout.setOnLoadmoreListener(onLoadmoreListener)
                    .setEnableLoadmore(loadMoreEnable);
        }
    }

    public void onRefreshFinish(SmartRefreshLayout smartRefreshLayout) {
        if (smartRefreshLayout.isRefreshing()) {
            smartRefreshLayout.finishRefresh();
        }

        if (!smartRefreshLayout.isEnableRefresh()) {
            smartRefreshLayout.setEnableRefresh(true);
        }
    }

    @Override
    public void onError(String msg, int code) {
        assert ((BaseActivity) getActivity()) != null;
        ((BaseActivity) getActivity()).onError(msg, code);
    }

    protected abstract void initData();

    protected abstract void initView();

    @Override
    public void addDisposable(Disposable d) {
        mCompositeDisposable.add(d);
    }

    @Override
    public void onDestroy() {
        mCompositeDisposable.clear();
        mUnbinder.unbind();
        super.onDestroy();
    }
}
