package com.silence.gankio.api;

import com.silence.gankio.constent.NetWorkConstent;
import com.silence.gankio.model.GankioAndroidResult;
import com.silence.gankio.model.GankioiOSResult;
import com.silence.gankio.model.GankioResult;
import com.silence.gankio.model.GankioWelfareResult;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Silence-Dell
 *
 * @time 2017/12/18 1:24
 * @des ${TODO}
 */

public interface GankioApi {

    @GET(NetWorkConstent.Gankio_Android + "{number}/{page}")
    Observable<GankioResult<List<GankioAndroidResult>>> getAndroidData(@Path("number") int number, @Path("page") int page);

    @GET(NetWorkConstent.Gankio_Welfare + "{number}/{page}")
    Observable<GankioResult<List<GankioWelfareResult>>> getWelfareData(@Path("number") int number, @Path("page") int page);

    @GET(NetWorkConstent.Gankio_iOS + "{number}/{page}")
    Observable<GankioResult<List<GankioiOSResult>>> getiOSData(@Path("number") int number, @Path("page") int page);

}
