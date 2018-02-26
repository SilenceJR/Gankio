package com.silence.gankio.di;

import dagger.Module;
import dagger.Provides;

/**
 * @作者: PJ
 * @创建时间: 2018/2/23 / 16:40
 * @描述: 这是一个 CarModule 类.
 */

@Module
public class CarModule {

    @Provides
    static Car providesCar() {
        return new Car();
    }
}
