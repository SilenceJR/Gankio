package com.silence.gankio.di;

import javax.inject.Inject;

/**
 * @作者: PJ
 * @创建时间: 2018/2/23 / 16:32
 * @描述: 这是一个 Man 类.
 */
public class Man {

    @Inject
    Car mCar;

    public Man() {
        System.out.println("我是man");
        DaggerManComponent.create().injectMan(this);
        
    }
}
