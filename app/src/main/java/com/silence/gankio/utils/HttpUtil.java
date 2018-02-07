package com.silence.gankio.utils;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.silence.gankio.api.GankioApi;
import com.silence.gankio.base.GankIoObserver;
import com.silence.gankio.model.GankioResult;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Silence-Dell
 *
 * @time 2017/12/18 1:15
 * @des ${TODO}
 */

public class HttpUtil {

    private static final int TIME_OUT = 10;
    public static final String BASE_URL = "http://gank.io";

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build();

    private static GankioApi gankioApi = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GankioApi.class);

    /**
     * 请求
     *
     * @return
     */
    public static GankioApi post() {
        return gankioApi;
    }

    public static <T> void load(Observable<GankioResult<T>> observable, GankIoObserver<T> observer) {
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }

}
