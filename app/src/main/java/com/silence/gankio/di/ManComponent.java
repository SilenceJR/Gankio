package com.silence.gankio.di;

import dagger.Component;

/**
 * @作者: PJ
 * @创建时间: 2018/2/23 / 16:35
 * @描述: 这是一个 ManComponent 类.
 */

@Component(modules = CarModule.class)
public interface ManComponent {

    void injectMan(Man man);
}
