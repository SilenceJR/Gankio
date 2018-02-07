package com.silence.gankio.base;

import com.google.gson.JsonParseException;
import com.silence.gankio.base.inter.IView;
import com.silence.gankio.model.GankioResult;

import org.json.JSONException;

import java.net.ConnectException;

import javax.net.ssl.SSLHandshakeException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Created by Silence-Dell
 *
 * @time 2017/12/16 3:30
 * @des ${TODO}
 */

public abstract class GankIoObserver<T> implements Observer<GankioResult<T>> {

    private static final String TAG = GankIoObserver.class.getSimpleName();
    public static final String MAIN_THREAD = "main";
    private static final int RESPONSE_CODE_FAILED = -1;
    private final IView mView;

    public GankIoObserver(IView v) {
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
            onError("GankIo网站异常。。。。？？！", 0);
        }
    }

    private void onError(String msg, int code) {
        if (null != mView) {
            mView.onError(msg, code);
        }
        onNetFinish();
    }

    private void onNetFinish() {
        if (null != mView) {
            mView.onNetFinish();
        }
    }

    /**
     * 返回期望得到的类
     * @param t
     */
    protected abstract void onSuccess(T t);


    @Override
    public void onError(Throwable e) {
        String msg;
        int code = RESPONSE_CODE_FAILED;
        if (e instanceof HttpException) {
            HttpException exception = (HttpException) e;
//            msg = exception.response().message();
            msg = "网络错误";
        } else if (e instanceof JSONException || e instanceof JsonParseException) {
            msg = "解析错误";
        } else if (e instanceof ConnectException) {
            msg = "连接失败";
        } else if (e instanceof SSLHandshakeException) {
            msg = "证书验证失败";
        } else {
            msg = "未知错误";
        }
        onError(msg, code);
    }

    @Override
    public void onComplete() {
        onNetFinish();
    }
}
