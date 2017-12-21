package com.silence.gankio.db;

import android.app.Application;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.persistence.room.Room;

import com.silence.gankio.model.GankioAndroidResult;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @作者: PJ
 * @创建时间: 2017/12/21 / 17:34
 * @描述: 这是一个 GankioAndroidDb 类.
 */
public class GankioDb {

    private static Application mContext;
    private static GankioDaoBase mBase;
    private static ExecutorService mCachedThreadPool = Executors.newCachedThreadPool();

    public static void init(Application context) {
        mContext = context;
    }

    public GankioDb() {
        if (mContext == null) {
            throw new IllegalStateException("GankioDb尚未进行初始化操作!");
        }
        mBase = Room.databaseBuilder(mContext, GankioDaoBase.class, "Gankio.db").build();
    }

    public static List<GankioAndroidResult> getAllAndroid() throws ExecutionException, InterruptedException {
        Future<List<GankioAndroidResult>> future = mCachedThreadPool.submit(() -> mBase.mGankioAndroidDao().getAllAndroid());
        return future.get();
    }

    public static void androidListener(LifecycleOwner owner, Observer<GankioAndroidResult> observer) {
        mBase.mGankioAndroidDao().getAndroidListener().observe(owner, observer);
    }

    public static GankioAndroidResult queryAndroud(String id) throws ExecutionException, InterruptedException {
        Future<GankioAndroidResult> future = mCachedThreadPool.submit(() -> mBase.mGankioAndroidDao().queryAndroid(id));
        return future.get();
    }

    public static void allAndroidListener(LifecycleOwner owner, Observer<List<GankioAndroidResult>> observer) {
        mBase.mGankioAndroidDao().getAllAndroidListener().observe(owner, observer);
    }

    public static void delete(GankioAndroidResult gankioAndroid) {
        mCachedThreadPool.submit(() -> mBase.mGankioAndroidDao().deleteGankioAndroid(gankioAndroid));
    }

//    public static void insertList(List<GankioAndroidResult> gankioAndroids) {
//        mCachedThreadPool.submit(() -> mBase.mGankioAndroidDao().insertGankioAndroids(gankioAndroids));
//    }

    public static void insert(GankioAndroidResult gankioAndroid) {
        mCachedThreadPool.submit(() -> mBase.mGankioAndroidDao().insertGankioAndroid(gankioAndroid));
    }

    public static <T extends GankioAndroidResult>void insert(Object obj) {
        if (obj instanceof List) {
            mCachedThreadPool.submit(() -> mBase.mGankioAndroidDao().insertGankioAndroid(((List<T>) obj)));
        } else {
            mCachedThreadPool.submit(() -> mBase.mGankioAndroidDao().insertGankioAndroid(((T) obj)));
        }
        mCachedThreadPool.submit(() -> mBase.mGankioAndroidDao().insertGankioAndroid(gankioAndroid));
    }

    public static <T> void add(Object obj) {
        if (obj instanceof List) {
            List<T> list = (List<T>) obj;

        }
    }

    public static void add(GankioAndroidResult gankioAndroidResult) throws ExecutionException, InterruptedException {
        GankioAndroidResult androud = queryAndroud(gankioAndroidResult.get_id());
        if (androud == null) {
            insert(gankioAndroidResult);
        } else {
            if (!androud.equals(gankioAndroidResult)) {
                updateGankioAndroid(gankioAndroidResult);
            }
        }
    }

    public static void updateGankioAndroid(GankioAndroidResult gankioAndroid) {
        mCachedThreadPool.submit(() -> mBase.mGankioAndroidDao().updateGankioAndroid(gankioAndroid));
    }


}
