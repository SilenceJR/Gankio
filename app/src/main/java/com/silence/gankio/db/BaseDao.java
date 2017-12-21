package com.silence.gankio.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.silence.gankio.model.GankioAndroidResult;

import java.util.List;

/**
 * Created by Silence-Dell
 *
 * @time 2017/12/22 1:22
 * @des ${TODO}
 */

public interface BaseDao {



    @Query("SELECT * FROM GankioAndroid")
    List<GankioAndroidResult> quertAll();

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
