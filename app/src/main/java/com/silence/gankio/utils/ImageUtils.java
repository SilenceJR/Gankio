package com.silence.gankio.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by Silence-Dell
 *
 * @time 2018/2/8 1:00
 * @des ${TODO}
 */

public class ImageUtils {

    public static Observable<BitmapFactory.Options> getImageWidthHeight(String path) {

        return Observable.create(new ObservableOnSubscribe<BitmapFactory.Options>() {
            @Override
            public void subscribe(ObservableEmitter<BitmapFactory.Options> e) throws Exception {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;

                URL url = new URL(path);
                URLConnection urlConnection = url.openConnection();
                urlConnection.connect();
                InputStream is = urlConnection.getInputStream();

                Bitmap bitmap = BitmapFactory.decodeStream(is, null, options);

//                Bitmap bitmap = BitmapFactory.decodeFile(path, options);

                e.onNext(options);
                e.onComplete();
            }
        });

    }

    public static int getWindowWidth(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = activity.getWindowManager();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        return width;
    }

}
