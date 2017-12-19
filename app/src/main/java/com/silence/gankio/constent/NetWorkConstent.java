package com.silence.gankio.constent;

/**
 * Created by Silence-Dell
 *
 * @time 2017/12/19 0:50
 * @des ${TODO}
 */

public interface NetWorkConstent {

    public static final String Gankio_URL = "http://gank.io/api/";
    public static final String Gankio_Data = Gankio_URL + "data/";

    /**
     * Android数据
     */
    public static final String Gankio_Android = Gankio_Data + "Android/";
    /**
     * 福利数据
     */
    public static final String Gankio_Welfare = Gankio_Data + "福利/";
    /**
     * IOS数据
     */
    public static final String Gankio_IOS = Gankio_Data + "iOS/";
    /**
     * 全部数据
     */
    public static final String Gankio_All = Gankio_Data + "all/";

}
