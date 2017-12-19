package com.silence.gankio.base;

import android.content.Context;
import android.support.annotation.CallSuper;

import com.silence.gankio.base.inter.IView;
import com.silence.gankio.model.GankioResult;
import com.silence.gankio.netword.HttpUiTip;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Silence-Dell
 *
 * @time 2017/12/16 3:30
 * @des ${TODO}
 */

public abstract class BaseObserver<T> implements Observer<GankioResult<T>> {

    private static final String TAG = BaseObserver.class.getSimpleName();
    public static final String MAIN_THREAD = "main";
    private static final int RESPONSE_CODE_FAILED = -1;
    private final IView mView;

    public BaseObserver(IView v) {
        mView = v;
    }

    @Override
    public void onSubscribe(Disposable d) {
        mView.addDisposable(d);
    }

    @Override
    public void onNext(GankioResult<T> value) {
        if (value.isError()) {
            onSuccess((value.getResults()));
        } else {
            onFailure("GankIo网站异常。。。。？？！", 0);
        }
    }

    @CallSuper
    private void onFailure(String s, int i) {
        if (null != mView && i == RESPONSE_CODE_FAILED) {
            HttpUiTip.alertTip(((Context) mView), s);
        }
    }

    /**
     * 返回期望得到的类
     * @param t
     */
    protected abstract void onSuccess(T t);


    @Override
    public void onError(Throwable e) {
        onFailure("网络异常。。。？？", -1);
    }

    @Override
    public void onComplete() {
        mView.NetFinish();
    }
}
