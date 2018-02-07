package com.silence.gankio.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.CompoundButton;

import com.silence.gankio.R;

/**
 * Created by Silence-Dell
 *
 * @time 2018/2/3 16:19
 * @des ${TODO}
 */

public class TabView extends View implements View.OnClickListener {
    //默认图标
    private Bitmap mIcon;

    //选中图标
    private Bitmap mSelectIcon;

    private Bitmap mCurrentIcon = mIcon;

    //是否选中
    private boolean mIsChecked = false;

    //图片宽
    private int mIconWidth;
    //图片高
    private int mIconHeight;

    private String mText = "";

    //文字大小
    private int mTextSize;

    private int mTextColor;

    private int mTextSelectColor;

    //间距
    private int mInterval;

    //提示文字颜色
    private int mTipTextColor = Color.WHITE;

    //提示点大小
    private float mTipRadius;

    //提示点文字大小
    private float mTipTextSize = 10;

    //提示点文字
    private String mTipText;

    //是否显示提示点
    private boolean mShowTip = false;

    //提示点颜色
    private int mTipCircleColor = Color.RED;

    private Paint mTextPaint;

    private Rect mTextBounds;

    private Rect mIconRect;

    private Paint mTipPaint;

    private CompoundButton.OnCheckedChangeListener mListener;

    public TabView(Context context) {
        this(context, null);
    }

    public TabView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TabView);

        //图片大小
        mIconWidth = (int) typedArray.getDimension(R.styleable.TabView_tab_icon_width, getDp(15));
        mIconHeight = (int) typedArray.getDimension(R.styleable.TabView_tab_icon_height, getDp(15));
        //图片和文字的间距
        mInterval = (int) typedArray.getDimension(R.styleable.TabView_tab_icon_text_interval, 0);
        //图片
        mIcon = ((BitmapDrawable) typedArray.getDrawable(R.styleable.TabView_tab_icon)).getBitmap();
        if (null == mIcon) {
            throw new NullPointerException("app: tab_icon not found!");
        }

        mSelectIcon = ((BitmapDrawable) typedArray.getDrawable(R.styleable.TabView_tab_select_icon)).getBitmap();
        if (null == mSelectIcon) {
            throw new NullPointerException("app:tab_select_icon not fount!");
        }

        //文字
        mText = typedArray.getString(R.styleable.TabView_tab_text);
        //默认文字颜色
        mTextColor = typedArray.getColor(R.styleable.TabView_tab_text_color, Color.GRAY);
        //选中文字颜色
        mTextSelectColor = typedArray.getColor(R.styleable.TabView_tab_text_select_color, Color.RED);
        //文字大小
        mTextSize = (int) typedArray.getDimension(R.styleable.TabView_tab_text_size, 12);
        //选中状态
        mIsChecked = typedArray.getBoolean(R.styleable.TabView_tab_checked, false);

        //是否显示提示点
        mShowTip = typedArray.getBoolean(R.styleable.TabView_tab_show_tip_circle, false);

        if (mShowTip) {

            //提示点数字
            mTipText = typedArray.getString(R.styleable.TabView_tab_tip_text);
            //提示点颜色
            mTipCircleColor = typedArray.getColor(R.styleable.TabView_tab_tip_circle_color, Color.RED);
            //提示点文字颜色
            mTipTextColor = typedArray.getColor(R.styleable.TabView_tab_tip_text_color, Color.WHITE);
            //提示点文字大小
            mTipTextSize = typedArray.getDimension(R.styleable.TabView_tab_tip_text_size, 10);
            //提示点大小
            mTipRadius = typedArray.getDimension(R.styleable.TabView_tab_tip_circle_radius, getDp(8));

        }

        typedArray.recycle();

        mTextPaint = new Paint();
        //文字大小
        mTextPaint.setTextSize(mTextSize);
        //文字颜色
        mTextPaint.setColor(mIsChecked ? mTextSelectColor : mTextColor);
        //抗锯齿
        mTextPaint.setAntiAlias(true);
        mTextBounds = new Rect();
        mTextPaint.getTextBounds(mText, 0, mText.length(), mTextBounds);
        setOnClickListener(this);
    }

    private float getDp(int dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int left = (getMeasuredWidth()) / 2
                - mIconWidth / 2 + getPaddingLeft() - getPaddingRight();
        int top = getMeasuredHeight() / 2
                - (mIconHeight + mInterval + mTextBounds.height()) / 2
                + getPaddingTop()
                - getPaddingBottom();
        //图标位置
        mIconRect = new Rect(left, top, mIconWidth, top + mIconHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mCurrentIcon = mIsChecked ? mSelectIcon : mIcon;
        mTextPaint.setColor(mIsChecked ? mTextSelectColor : mTextColor);
        drawIcon(canvas);
        drawText(canvas);
        drawTip(canvas);
    }

    private void drawTip(Canvas canvas) {
        if (mShowTip) {
            int x = ((int) (getMeasuredWidth() - mTipRadius)) - getPaddingRight();
            int y = (int) (mTipRadius + getPaddingTop());
            mTipPaint.setColor(mTipCircleColor);
            canvas.drawCircle(x, y, mTipRadius, mTipPaint);

            if (!TextUtils.isEmpty(mTipText)) {
                Rect textBound = new Rect();
                mTipPaint.setTextSize(mTipTextSize);
                mTipPaint.getTextBounds(mTipText, 0, mTipText.length(), textBound);
                mTipPaint.setColor(mTipTextColor);
                Paint.FontMetrics fontMetrics = mTipPaint.getFontMetrics();
                int tx = (int) (getMeasuredWidth() - getPaddingRight() - textBound.width() / 2 - mTipRadius);
                int ty = (int) (getPaddingTop() + mTipRadius + (Math.abs(fontMetrics.ascent) - Math.abs(fontMetrics.descent) / 2));
                canvas.drawText(mTipText, tx, ty, mTipPaint);
            }

        }
    }

    //画图片
    private void drawIcon(Canvas canvas) {
        canvas.drawBitmap(mCurrentIcon, null, mIconRect, null);
    }

    //画文字
    private void drawText(Canvas canvas) {
        float x = getMeasuredWidth() / 2 - mTextBounds.width() / 2 + getPaddingLeft() - getPaddingRight();
        float y = mIconRect.bottom + mInterval + mTextBounds.height();
        canvas.drawText(mText, x, y, mTextPaint);
    }

    public void setChecked(boolean isChecked) {
        this.mIsChecked = isChecked;
        if (null != mListener) {
            mListener.onchecked();
            mListener.on

        }
    }

    public void setOnCheckedChangeLinstener(CompoundButton.OnCheckedChangeListener linstener) {
        this.mListener = linstener;
    }

    @Override
    public void onClick(View view) {

    }
}
