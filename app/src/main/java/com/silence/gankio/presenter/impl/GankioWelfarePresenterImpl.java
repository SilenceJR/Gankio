package com.silence.gankio.presenter.impl;

import com.silence.gankio.base.GankIoObserver;
import com.silence.gankio.base.BasePresenter;
import com.silence.gankio.model.GankioWelfareResult;
import com.silence.gankio.presenter.GankioWelfarePresenter;
import com.silence.gankio.view.GankioWelfareView;
import com.silence.gankio.utils.HttpUtil;

import java.util.List;

/**
 * @作者: PJ
 * @创建时间: 2017/12/20 / 15:34
 * @描述: 这是一个 GankioAndroidPresenterImpl 类.
 */
public class GankioWelfarePresenterImpl extends BasePresenter<GankioWelfareView> implements GankioWelfarePresenter {

    public GankioWelfarePresenterImpl(GankioWelfareView view) {
        super(view);
    }


    @Override
    public void loadWelfareData(int page) {
        HttpUtil.load(HttpUtil.post()
                .getWelfareData(10, page), new GankIoObserver<List<GankioWelfareResult>>(getView()) {
            @Override
            protected void onSuccess(List<GankioWelfareResult> gankioWelfareResults) {
                getView().onWelfareSuccess(gankioWelfareResults);
            }

        });
    }
}
