package com.silence.gankio.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.silence.gankio.model.GankioAndroidResult;
import com.silence.gankio.model.GankioiOSResult;
import com.silence.gankio.model.GankioWelfareResult;

/**
 * @作者: PJ
 * @创建时间: 2017/12/21 / 17:22
 * @描述: 这是一个 GankioDaoBase 类.
 */
@Database(entities = {GankioAndroidResult.class, GankioWelfareResult.class, GankioiOSResult.class}, version = 1, exportSchema = false)
public abstract class GankioDaoBase extends RoomDatabase{

    public abstract GankioAndroidDao mGankioAndroidDao();
    public abstract GankioWelfareDao mGankioWelfareDao();
    public abstract GankioiOSDao mGankioiOSDao();
}
