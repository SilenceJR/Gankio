package com.silence.gankio.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.silence.gankio.model.GankioWelfareResult;

import java.util.List;

/**
 * @作者: PJ
 * @创建时间: 2017/12/21 / 17:23
 * @描述: 这是一个 GankioAndroidDao 类.
 */
public interface GankioWelfareDao {
    @Query("SELECT * FROM GankioWelfare")
    List<GankioWelfareResult> getAllWelfare();

    @Query("SELECT * FROM GankioWelfare")
    LiveData<GankioWelfareResult> getWelfareListener();

    @Insert
    void insertGankioWelfare(GankioWelfareResult gankioWelfare);

    @Update
    void updateGankioWelfare(GankioWelfareResult gankioWelfare);

    @Delete
    void deleteGankioWelfare(GankioWelfareResult gankioWelfare);
}
