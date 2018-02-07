package com.silence.gankio.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.silence.gankio.R;
import com.silence.gankio.adapter.GankioWelfareAdapter;
import com.silence.gankio.base.BaseFragment;
import com.silence.gankio.model.GankioWelfareResult;
import com.silence.gankio.presenter.GankioWelfarePresenter;
import com.silence.gankio.presenter.impl.GankioWelfarePresenterImpl;
import com.silence.gankio.view.GankioWelfareView;

import java.util.List;

import butterknife.BindView;

/**
 * @作者: PJ
 * @创建时间: 2017/12/20 / 15:47
 * @描述: 这是一个 GankioWelfareFragment 类.
 */
public class GankioWelfareFragment extends BaseFragment implements GankioWelfareView, BaseQuickAdapter.RequestLoadMoreListener, OnRefreshListener {
    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout mSmartRefreshLayout;
    private GankioWelfareAdapter mAdapter;
    private GankioWelfarePresenter mPresenter;

    private int page = 1;
    private boolean oneLoad = true;

    public GankioWelfareFragment() {
    }

    public static GankioWelfareFragment newInstance() {

        Bundle args = new Bundle();

        GankioWelfareFragment fragment = new GankioWelfareFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View getContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gankio_android, container, false);
    }

    @Override
    protected void initData() {
        mPresenter = new GankioWelfarePresenterImpl(this);
        mPresenter.loadWelfareData(page);
    }

    @Override
    protected void initView() {
        mRecycler.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mAdapter = new GankioWelfareAdapter(null);
        mAdapter.openLoadAnimation();
        //        mAdapter.disableLoadMoreIfNotFullPage();
        mAdapter.setEnableLoadMore(true);
        mAdapter.setOnLoadMoreListener(this, mRecycler);
        mRecycler.setAdapter(mAdapter);

        initRefreshLayout(mSmartRefreshLayout, this, false, null);

    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.loadWelfareData(page);
    }

    @Override
    public void onWelfareSuccess(List<GankioWelfareResult> gankioWelfareResults) {
        page++;
        if (gankioWelfareResults != null) {
            if (oneLoad) {
                mAdapter.setNewData(gankioWelfareResults);
                oneLoad = false;
            } else {
                mAdapter.addData(gankioWelfareResults);
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
        mPresenter.loadWelfareData(page);
    }
}
