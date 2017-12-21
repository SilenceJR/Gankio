package com.silence.gankio.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.silence.gankio.model.GankioiOSResult;

import java.util.List;

/**
 * @作者: PJ
 * @创建时间: 2017/12/21 / 17:23
 * @描述: 这是一个 GankioiOSDao 类.
 */
public interface GankioiOSDao {
    @Query("SELECT * FROM GankioiOS")
    List<GankioiOSResult> getAlliOS();

    @Query("SELECT * FROM GankioiOS")
    LiveData<GankioiOSResult> getiOSListener();

    @Insert
    void insertGankioiOS(GankioiOSResult gankioiOS);

    @Update
    void updateGankioiOS(GankioiOSResult gankioiOS);

    @Delete
    void deleteGankioiOS(GankioiOSResult gankioiOS);
}
