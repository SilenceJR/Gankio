package com.silence.gankio.netword;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.silence.gankio.base.BaseObserver;

/**
 * Created by Silence-Dell
 *
 * @time 2017/12/19 0:37
 * @des ${TODO}
 */

public class HttpUiTip {

    private static AlertDialog.Builder mDialog;
    private static AlertDialog sAlertDialog;

    public static void alertTip(Context context, String errorMsg) {

        if (!Thread.currentThread().getName().equals(BaseObserver.MAIN_THREAD)) {
            return;
        }



        mDialog = new AlertDialog.Builder(context);
        mDialog.setTitle("获取数据异常");
        mDialog.setMessage(errorMsg);
        mDialog.setPositiveButton("知道了", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        sAlertDialog = mDialog.create();
        if (((Activity) context).isFinishing()) {
            sAlertDialog = null;
            return;
        }
        sAlertDialog.show();

    }
}
