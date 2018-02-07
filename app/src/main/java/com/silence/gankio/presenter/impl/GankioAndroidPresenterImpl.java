package com.silence.gankio.presenter.impl;

import com.silence.gankio.base.GankIoObserver;
import com.silence.gankio.base.BasePresenter;
import com.silence.gankio.db.GankioDb;
import com.silence.gankio.model.GankioAndroidResult;
import com.silence.gankio.presenter.GankioAndroidPresenter;
import com.silence.gankio.view.GankioAndroidView;
import com.silence.gankio.utils.HttpUtil;

import java.util.List;

/**
 * @作者: PJ
 * @创建时间: 2017/12/20 / 15:34
 * @描述: 这是一个 GankioAndroidPresenterImpl 类.
 */
public class GankioAndroidPresenterImpl extends BasePresenter<GankioAndroidView> implements GankioAndroidPresenter {

    public GankioAndroidPresenterImpl(GankioAndroidView view) {
        super(view);
    }

    @Override
    public void loadAndroidData(int page) {
        HttpUtil.load(HttpUtil.post()
                .getAndroidData(10, page), new GankIoObserver<List<GankioAndroidResult>>(getView()) {
            @Override
            protected void onSuccess(List<GankioAndroidResult> gankioAndroidResults) {
                GankioDb.add(gankioAndroidResults);
//                getView().onAndroidSUccess(gankioAndroidResults);
            }
        });
    }
}
