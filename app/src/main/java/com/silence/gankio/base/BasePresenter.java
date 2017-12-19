package com.silence.gankio.base;

import com.silence.gankio.base.inter.IPresenter;
import com.silence.gankio.base.inter.IView;

import java.lang.ref.WeakReference;

/**
 * Created by Silence-Dell
 *
 * @time 2017/12/16 3:37
 * @des ${TODO}
 */

public class BasePresenter<V extends IView> implements IPresenter {

    private WeakReference<V> mWeakReference;

    public BasePresenter(V view) {
        mWeakReference = new WeakReference<>(view);
    }

    @Override
    public V getView() {
        V v = mWeakReference.get();
        if (v != null) {
            return v;
        }
        return null;
    }

    @Override
    public void unAttach() {
        mWeakReference.clear();
    }
}
