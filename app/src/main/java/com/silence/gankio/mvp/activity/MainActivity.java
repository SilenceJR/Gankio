package com.silence.gankio.mvp.activity;

import android.os.Bundle;

import com.silence.gankio.R;
import com.silence.gankio.base.BaseActivity;
import com.silence.gankio.mvp.presenterImp.MainPresneterImp;
import com.silence.gankio.mvp.view.MainView;

public class MainActivity extends BaseActivity implements MainView{

    private MainPresneterImp mPresneterImp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresneterImp = new MainPresneterImp(this);

        mPresneterImp.loadAndroidData(1);

    }

    @Override
    protected int getActivityContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void setSwipeBackEnable(boolean enable) {
        super.setSwipeBackEnable(false);
    }

    @Override
    public void NetFinish() {

    }

}
