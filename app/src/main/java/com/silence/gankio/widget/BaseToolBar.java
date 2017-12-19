package com.silence.gankio.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.silence.gankio.R;
import com.silence.gankio.utils.Utils;

/**
 * Created by Silence-Dell
 *
 * @time 2017/12/20 0:11
 * @des ${TODO}
 */

public class BaseToolBar extends Toolbar{
    public BaseToolBar(Context context) {
        super(context, null);
    }

    public BaseToolBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setContentInsetsAbsolute(0, 0);
        setContentInsetsRelative(0, 0);
        int paddingLeft = getResources().getDimensionPixelSize(R.dimen.toolbar_padding_left);
        int paddingRight = getResources().getDimensionPixelSize(R.dimen.toolbar_padding_right);
        setPadding(getPaddingLeft() + paddingLeft, getPaddingTop(), getPaddingLeft() + paddingRight, getPaddingBottom());
    }

    public void setDefaultRedBg() {
        setBackgroundResource(R.drawable.bg_toolbar_comm);
    }

    /**
     * 适配透明状态栏
     */
    public BaseToolBar fitTranslucentStatus(boolean isShowBlack) {
        Context context = getContext();
        if (context instanceof Activity) {
            Window window = ((Activity) context).getWindow();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                //状态栏透明
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.flags = attributes.flags
                        | WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                        | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.setStatusBarColor(Color.TRANSPARENT);
                    if (isShowBlack) {
                        attributes.flags = attributes.flags
                                | WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            //黑色字符状态栏
                            window.getDecorView()
                                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                            | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                        }
                    }
                }

                //状态栏适配
                int actionBarSize = Utils.getActionBarSize(context);
                int statusBarH = Utils.getStatusBarH(context);
                getLayoutParams().height = statusBarH + actionBarSize;
                setPadding(getPaddingLeft(), getPaddingTop() + statusBarH, getPaddingRight(), getPaddingBottom());
            }
        }
        return this;
    }
}
