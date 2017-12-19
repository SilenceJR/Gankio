package com.silence.gankio.base.inter;

/**
 * Created by Silence-Dell
 *
 * @time 2017/12/16 3:28
 * @des ${TODO}
 */

public interface IPresenter<V extends IView> {

    V getView();

    /**
     * 解绑View
     */
    void unAttach();

}
