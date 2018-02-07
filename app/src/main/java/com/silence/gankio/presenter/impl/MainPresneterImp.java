package com.silence.gankio.presenter.impl;

import android.util.Log;

import com.silence.gankio.BuildConfig;
import com.silence.gankio.base.GankIoObserver;
import com.silence.gankio.base.BasePresenter;
import com.silence.gankio.model.GankioAndroidResult;
import com.silence.gankio.presenter.MainPresenter;
import com.silence.gankio.view.MainView;
import com.silence.gankio.utils.HttpUtil;

import java.util.List;

/**
 * Created by Silence-Dell
 *
 * @time 2017/12/19 1:24
 * @des ${TODO}
 */

public class MainPresneterImp extends BasePresenter<MainView> implements MainPresenter {

    public MainPresneterImp(MainView view) {
        super(view);
    }

    @Override
    public void loadAndroidData(int page) {
        HttpUtil.load(HttpUtil.post().getAndroidData(10, page), new GankIoObserver<List<GankioAndroidResult>>(getView()) {
            @Override
            protected void onSuccess(List<GankioAndroidResult> gankioAndroidResults) {
                for (GankioAndroidResult gankioAndroidResult : gankioAndroidResults) {
                    if (BuildConfig.DEBUG) Log.d("MainActivity", gankioAndroidResult.toString());
                }
            }
        });
    }
}
