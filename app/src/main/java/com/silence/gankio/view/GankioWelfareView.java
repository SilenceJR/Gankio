package com.silence.gankio.view;

import com.silence.gankio.base.inter.IView;
import com.silence.gankio.model.GankioWelfareResult;

import java.util.List;

/**
 * @作者: PJ
 * @创建时间: 2017/12/20 / 15:55
 * @描述: 这是一个 GankioWelfareView 类.
 */
public interface GankioWelfareView extends IView{
    void onWelfareSuccess(List<GankioWelfareResult> gankioWelfareResults);

}
