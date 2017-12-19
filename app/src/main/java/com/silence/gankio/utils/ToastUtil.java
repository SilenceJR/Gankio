package com.silence.gankio.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Silence-Dell
 *
 * @time 2017/12/16 3:50
 * @des ${TODO}
 */

public class ToastUtil {

    private static Context sContext;
    private static volatile ToastUtil mInstance;

    private static final int DEFAULT_STYLE = 1;
    private static final int WAMING_STYLE = 2;
    private static final int ERROR_STYLE = 3;
    private final Toast mToast;

    public static void register(Context context) {
        sContext = context;
    }

    public static void getInstance() {
        if (mInstance == null) {
            synchronized (ToastUtil.class) {
                mInstance = new ToastUtil();
            }
        }
    }

    private ToastUtil() {
        if (sContext == null) {
            throw new IllegalStateException("ToastUtil尚未进行初始化！");
        }

        mToast = new Toast(sContext);
    }

    public void showToast(String msg) {
        mToast.setText(msg);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.show();
    }
}
