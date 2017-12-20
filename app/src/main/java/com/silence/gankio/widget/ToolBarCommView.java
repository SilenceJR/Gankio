package com.silence.gankio.widget;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silence.gankio.R;

/**
 * Created by Silence-Dell
 *
 * @time 2017/12/20 0:21
 * @des ${TODO}
 */

public class ToolBarCommView extends RelativeLayout {

    private Button mBtnBack;
    private TextView mTvTitle;
    private TextView mTvRightTitle;
    private ImageView mIvRightIcon;

    public ToolBarCommView(Context context) {
        this(context, null);
    }

    public ToolBarCommView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.toolbar_comm, this);
        mBtnBack = findViewById(R.id.toolbar_back);
        mTvTitle = findViewById(R.id.toolbar_title);
        mTvRightTitle = findViewById(R.id.toolbar_right_title);
        mIvRightIcon = findViewById(R.id.toolbar_right_icon);
        mBtnBack.setOnClickListener(v -> onBack(v));
    }

    private void onBack(View v) {
        Context context = getContext();
        if (context instanceof Activity) {
            ((Activity) context).onBackPressed();
        }
    }

    public void setIvRightIcon(int resid) {
        mIvRightIcon.setImageResource(resid);
    }

    public void setIvRightIconEvent(OnClickListener listener) {
        mIvRightIcon.setOnClickListener(listener);
    }

    /**
     * 显示退出按钮
     */
    public ToolBarCommView showBack(boolean isShow) {
        if (!isShow) {
            mBtnBack.setVisibility(GONE);
        } else {
            mBtnBack.setVisibility(VISIBLE);
        }
        return this;
    }

    public ToolBarCommView setTitlte(String title) {
        mTvTitle.setText(title);
        return this;
    }

    public ToolBarCommView setTitlte(int title) {
        mTvTitle.setText(title);
        return this;
    }

    /**
     * 默认标题
     */
    public ToolBarCommView setDefTitle() {
        Context context = getContext();
        if (context instanceof Activity) {
            try {
                PackageManager packageManager = context.getPackageManager();
                ActivityInfo activityInfo = packageManager.getActivityInfo(((Activity) context).getComponentName(), PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
                CharSequence charSequence = activityInfo.loadLabel(packageManager);
                setTitlte(charSequence.toString());
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    /**
     * 右边按钮
     */
    public ToolBarCommView setRightTitle(String rightTitle) {
        mTvRightTitle.setText(rightTitle);
        return this;
    }

    /**
     * 右边点击事件
     */
    public ToolBarCommView setRightEvent(OnClickListener listener) {
        mTvRightTitle.setOnClickListener(listener);
        return this;
    }

}
