package com.silence.gankio.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.silence.gankio.BuildConfig;
import com.silence.gankio.R;
import com.silence.gankio.base.BaseFragment;
import com.silence.gankio.db.GankioDb;
import com.silence.gankio.model.GankioAndroidResult;
import com.silence.gankio.adapter.GankioAndroidAdapter;
import com.silence.gankio.presenter.impl.GankioAndroidPresenterImpl;
import com.silence.gankio.view.GankioAndroidView;

import java.util.List;

import butterknife.BindView;

/**
 * @作者: PJ
 * @创建时间: 2017/12/20 / 15:14
 * @描述: 这是一个 GankioAndroidFragment 类.
 */
public class GankioAndroidFragment extends BaseFragment implements GankioAndroidView, BaseQuickAdapter.RequestLoadMoreListener {


    @BindView(R.id.recycler)
    RecyclerView mRecycler;

    private GankioAndroidAdapter mAdapter;

    private int page = 1;
    private boolean oneLoad = true;
    private GankioAndroidPresenterImpl mPresenter;

    @Override
    protected View getContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gankio_android, container, false);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new GankioAndroidAdapter(null);
        mAdapter.openLoadAnimation();
        mAdapter.setEnableLoadMore(true);
        mAdapter.setOnLoadMoreListener(this, mRecycler);
        mRecycler.setAdapter(mAdapter);
        //        mAdapter.disableLoadMoreIfNotFullPage();

        mPresenter = new GankioAndroidPresenterImpl(this);
        mPresenter.loadAndroidData(page);

        initListener();
    }

    private void initListener() {
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            GankioAndroidResult gankioAndroid = (GankioAndroidResult) adapter.getData().get(position);
            String url = gankioAndroid.getUrl();
            if (BuildConfig.DEBUG) Log.d("GankioAndroidFragment", url);

        });

        GankioDb.allAndroidListener(this, gankioAndroidResults -> onAndroidSUccess(gankioAndroidResults));
    }

    @Override
    public void NetFinish() {

    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.loadAndroidData(page);
    }

    @Override
    public void onAndroidSUccess(List<GankioAndroidResult> gankioAndroidResults) {
        page++;
        if (gankioAndroidResults != null) {
            if (oneLoad) {
                mAdapter.setNewData(gankioAndroidResults);
                oneLoad = false;
            } else {
                mAdapter.addData(gankioAndroidResults);
            }

            if (mAdapter.isLoading()) {
                mAdapter.loadMoreComplete();
            }
        }

    }
}
