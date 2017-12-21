package com.silence.gankio.mvp.presenterImp;

import com.silence.gankio.base.BaseObserver;
import com.silence.gankio.base.BasePresenter;
import com.silence.gankio.model.GankioiOSResult;
import com.silence.gankio.mvp.presenter.GankioIOSPresenter;
import com.silence.gankio.mvp.view.GankioIOSView;
import com.silence.gankio.utils.HttpUtil;

import java.util.List;

/**
 * @作者: PJ
 * @创建时间: 2017/12/20 / 15:34
 * @描述: 这是一个 GankioAndroidPresenterImpl 类.
 */
public class GankioIOSPresenterImpl extends BasePresenter<GankioIOSView> implements GankioIOSPresenter {

    public GankioIOSPresenterImpl(GankioIOSView view) {
        super(view);
    }

    @Override
    public void loadIOSData(int page) {
        HttpUtil.load(HttpUtil.post()
                .getiOSData(10, page), new BaseObserver<List<GankioiOSResult>>(getView()) {
            @Override
            protected void onSuccess(List<GankioiOSResult> gankioiOSResults) {
                getView().onIOSSUccess(gankioiOSResults);
            }
        });
    }
}
