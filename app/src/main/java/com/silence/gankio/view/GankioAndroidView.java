package com.silence.gankio.view;

import com.silence.gankio.base.inter.IView;
import com.silence.gankio.model.GankioAndroidResult;

import java.util.List;

/**
 * @作者: PJ
 * @创建时间: 2017/12/20 / 15:34
 * @描述: 这是一个 GankioAndroidView 类.
 */
public interface GankioAndroidView extends IView{
    void onAndroidSUccess(List<GankioAndroidResult> gankioAndroidResults);
}
