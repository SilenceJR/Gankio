package com.silence.gankio.base;

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
 * Created by mocaris on 2017/11/15.
 */

public class BaseToolbar extends Toolbar {
    public BaseToolbar(Context context) {
        this(context, null);
    }

    public BaseToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setContentInsetsAbsolute(0, 0);
        setContentInsetsRelative(0, 0);
        int paddingleft = getResources().getDimensionPixelSize(R.dimen.toolbar_padding_left);
        int paddingright = getResources().getDimensionPixelSize(R.dimen.toolbar_padding_right);
        setPadding(getPaddingLeft() + paddingleft, getPaddingTop(), getPaddingRight() + paddingright, getPaddingBottom());
    }

    /**
     * 默认
     */
    public void setDefaultRedBg() {
        setBackgroundResource(R.drawable.bg_toolbar_comm);
    }
//
//    public BaseToolbar(Context context) {
//        this(context, null);
//    }
//
//    public BaseToolbar(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//        setContentInsetsAbsolute(0, 0);
//        setContentInsetsRelative(0, 0);
//        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
//        setPadding(padding, 0, padding, 0);
////        LayoutInflater.from(context).inflate(R.layout.toolbar_base_view, this, true);
////        addView(initView(), Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.WRAP_CONTENT);
//    }

//    protected  View initView();

//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        setMeasuredDimension(LayoutParams.MATCH_PARENT, Utils.getActionBarSize(getContext()));
//    }
//
//    public void setToolbarBackgroundColorResId(int colorResId) {
////        tl.setBackgroundResource(colorResId);
//    }
//
//    public void setToolbarBackgroundColor(int color) {
////        tl.setBackgroundColor(color);
//    }

    /**
     * 适配透明状态栏
     */
    public BaseToolbar fitTranslucentStatus(boolean isShowBlack) {
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
//                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
//                            | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
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
