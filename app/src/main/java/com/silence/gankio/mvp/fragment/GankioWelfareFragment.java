package com.silence.gankio.mvp.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.silence.gankio.R;
import com.silence.gankio.base.BaseFragment;
import com.silence.gankio.model.GankioWelfareResult;
import com.silence.gankio.mvp.adapter.GankioWelfareAdapter;
import com.silence.gankio.mvp.presenterImp.GankioWelfarePresenterImpl;
import com.silence.gankio.mvp.view.GankioWelfareView;

import java.util.List;

import butterknife.BindView;

/**
 * @作者: PJ
 * @创建时间: 2017/12/20 / 15:47
 * @描述: 这是一个 GankioWelfareFragment 类.
 */
public class GankioWelfareFragment extends BaseFragment implements GankioWelfareView, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    private GankioWelfareAdapter mAdapter;
    private GankioWelfarePresenterImpl mPresenter;

    private int page = 1;
    private boolean oneLoad = true;

    @Override
    public void NetFinish() {

    }

    @Override
    protected View getContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gankio_android, container, false);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mRecycler.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mAdapter = new GankioWelfareAdapter(null);
        mAdapter.openLoadAnimation();
        //        mAdapter.disableLoadMoreIfNotFullPage();
        mAdapter.setEnableLoadMore(true);
        mAdapter.setOnLoadMoreListener(this, mRecycler);
        mRecycler.setAdapter(mAdapter);

        mPresenter = new GankioWelfarePresenterImpl(this);
        mPresenter.loadWelfareData(page);
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
}
