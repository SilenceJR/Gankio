package com.silence.gankio.mvp.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.silence.gankio.R;
import com.silence.gankio.model.GankioiOSResult;

import java.util.List;

/**
 * @作者: PJ
 * @创建时间: 2017/12/20 / 15:16
 * @描述: 这是一个 GankioAndroidAdapter 类.
 */
public class GankioIOSAdapter extends BaseQuickAdapter<GankioiOSResult, BaseViewHolder> {

    public GankioIOSAdapter(@Nullable List<GankioiOSResult> data) {
        super(R.layout.item_gankio_android, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GankioiOSResult item) {
        helper.setText(R.id.item_title, item.getDesc())
                .setText(R.id.item_content, item.getDesc());

        if (null != item.getImages()) {
            Glide.with(mContext)
                    .load(item.getImages().get(0))
                    .into((ImageView) helper.getView(R.id.item_iv));
        }

    }
}
