package com.silence.gankio.base.inter;

import io.reactivex.disposables.Disposable;

/**
 * Created by Silence-Dell
 *
 * @time 2017/12/16 3:27
 * @des ${TODO}
 */

public interface IView {

    void addDisposable(Disposable d);

    void NetFinish();
}
