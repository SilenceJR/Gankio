package com.silence.gankio.utils;

import android.support.annotation.NonNull;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.silence.gankio.api.GankioApi;
import com.silence.gankio.base.GankIoObserver;
import com.silence.gankio.model.GankioResult;

import java.io.File;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
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

    public static void downloadFile(@NonNull String url, final String fileDir, final String fileName, final FileDownLoadObserver<File> fileDownLoadObserver) {
        gankioApi.download(url)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(new Function<ResponseBody, File>() {
                    @Override
                    public File apply(ResponseBody responseBody) throws Exception {
                        return fileDownLoadObserver.savaFile(responseBody, fileDir, fileName);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(fileDownLoadObserver);

    }

}
