package com.silence.gankio.db;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.silence.gankio.model.GankioAndroidResult;

import java.util.List;

/**
 * @作者: PJ
 * @创建时间: 2017/12/21 / 17:23
 * @描述: 这是一个 GankioAndroidDao 类.
 */
public interface GankioAndroidDao {

    @Query("SELECT * FROM GankioAndroid")
    List<GankioAndroidResult> getAllAndroid();

    @Query("SELECT * FROM GankioAndroid WHERE _id IN :id")
    GankioAndroidResult queryAndroid(String id);

    @Query("SELECT * FROM GankioAndroid WHERE _ IN :id")
    GankioAndroidResult queryAndroid(List<GankioAndroidResult> androidResults);

    @Query("SELECT * FROM GankioAndroid")
    LiveData<GankioAndroidResult> getAndroidListener();

    @Query("SELECT * FROM GankioAndroid")
    LiveData<List<GankioAndroidResult>> getAllAndroidListener();

    @Insert
    void insertGankioAndroid(GankioAndroidResult... gankioAndroid);

//    @Insert
//    void insertGankioAndroids(List<GankioAndroidResult> gankioAndroids);

    @Update
    void updateGankioAndroid(GankioAndroidResult gankioAndroid);

    @Delete
    void deleteGankioAndroid(GankioAndroidResult gankioAndroid);


}
