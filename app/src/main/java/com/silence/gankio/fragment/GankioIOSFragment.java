package com.silence.gankio.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.silence.gankio.BuildConfig;
import com.silence.gankio.R;
import com.silence.gankio.adapter.GankioIOSAdapter;
import com.silence.gankio.base.BaseFragment;
import com.silence.gankio.model.GankioiOSResult;
import com.silence.gankio.presenter.GankioIOSPresenter;
import com.silence.gankio.presenter.impl.GankioIOSPresenterImpl;
import com.silence.gankio.view.GankioIOSView;

import java.util.List;

import butterknife.BindView;

/**
 * @作者: PJ
 * @创建时间: 2017/12/20 / 15:14
 * @描述: 这是一个 GankioAndroidFragment 类.
 */
public class GankioIOSFragment extends BaseFragment implements GankioIOSView, BaseQuickAdapter.RequestLoadMoreListener, OnRefreshListener {


    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout mSmartRefreshLayout;

    private GankioIOSAdapter mAdapter;

    private int page = 1;
    private boolean oneLoad = true;
    private GankioIOSPresenter mPresenter;

    public GankioIOSFragment() {
    }

    public static GankioIOSFragment newInstance() {

        Bundle args = new Bundle();

        GankioIOSFragment fragment = new GankioIOSFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View getContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gankio_android, container, false);
    }

    @Override
    protected void initData() {
        mPresenter = new GankioIOSPresenterImpl(this);
        mPresenter.loadIOSData(page);
    }

    @Override
    protected void initView() {
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new GankioIOSAdapter(null);
        mAdapter.openLoadAnimation();
        mAdapter.setEnableLoadMore(true);
        mAdapter.setOnLoadMoreListener(this, mRecycler);
        mRecycler.setAdapter(mAdapter);
        //        mAdapter.disableLoadMoreIfNotFullPage();

        initRefreshLayout(mSmartRefreshLayout, this, false, null);

        initListener();
    }

    private void initListener() {
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            GankioiOSResult gankioIOS = (GankioiOSResult) adapter.getData().get(position);
            String url = gankioIOS.getUrl();
            if (BuildConfig.DEBUG) Log.d("GankioiOSFragment", url);

        });
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.loadIOSData(page);
    }

    @Override
    public void onIOSSUccess(List<GankioiOSResult> gankioiOSResults) {
        page++;
        if (gankioiOSResults != null) {
            if (oneLoad) {
                mAdapter.setNewData(gankioiOSResults);
                oneLoad = false;
            } else {
                mAdapter.addData(gankioiOSResults);
            }

            if (mAdapter.isLoading()) {
                mAdapter.loadMoreComplete();
            }
        }

    }

    @Override
    public void onNetFinish() {
        onRefreshFinish(mSmartRefreshLayout);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        page = 1;
        oneLoad = true;
        mPresenter.loadIOSData(page);
    }
}
