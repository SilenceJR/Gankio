package com.silence.gankio.adapter;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.silence.gankio.R;
import com.silence.gankio.model.GankioWelfareResult;
import com.silence.gankio.utils.ImageUtils;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @作者: PJ
 * @创建时间: 2017/12/20 / 15:50
 * @描述: 这是一个 GankioWelfareAdapter 类.
 */
public class GankioWelfareAdapter extends BaseQuickAdapter<GankioWelfareResult, BaseViewHolder> {

    public GankioWelfareAdapter(@Nullable List<GankioWelfareResult> data) {
        super(R.layout.item_gankio_welfare, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GankioWelfareResult item) {
        ImageView imageView = helper.getView(R.id.item_iv);

        int width = ImageUtils.getWindowWidth((Activity) mContext) / 2;

        ImageUtils.getImageWidthHeight(item.getUrl())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BitmapFactory.Options>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BitmapFactory.Options options) {
                        float ratio = (float) ((options.outWidth * 1.0) / (width * 1.0));
                        int height = (int) (options.outHeight * 1.0 / ratio);

                        Glide.with(mContext)
                                .load(item.getUrl())
                                .asBitmap()
                                .thumbnail(0.1F)
                                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                                .override(width, height)
                                .into(imageView);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


        //        if (!item.getUrl().equals(imageView.getTag())) {
        //            imageView.setTag(item.getUrl());
        //        }
        //
        //        if (item.getUrl().equals(imageView.getTag())) {
        //            Glide.with(mContext)
        //                    .load(item.getUrl())
        //                    .asBitmap()
        //                    .thumbnail(0.1f)
        //                    .into(new SimpleTarget<Bitmap>() {
        //                        @Override
        //                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
        //                            int resourceHeight = resource.getHeight();
        //                            int resourceWidth = resource.getWidth();
        //                            int max = 500;
        //
        //                            ViewGroup.LayoutParams params = imageView.getLayoutParams();
        //                            if (resourceHeight > max) {
        //                                params.height = max;
        //                                params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        //                            } else if (resourceWidth > max) {
        //                                params.width = max;
        //                                params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        //                            }
        //                            //                        params.width = resourceWidth / 5;
        //                            //                        params.height = resourceHeight / 5;
        //                            imageView.setLayoutParams(params);
        //
        //                            imageView.setImageBitmap(resource);
        //                        }
        //
        //                    });
        //        }

        //        Glide.with(mContext)
        //                .load(item.getUrl())
        //                .override(250, 250)
        //                .into(imageView);
    }
}
