package com.silence.gankio.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.observers.DefaultObserver;
import okhttp3.ResponseBody;

/**
 * Created by Silence
 *
 * @time 2018/2/7 20:57
 * @des ${TODO}
 */

public abstract class FileDownLoadObserver<T> extends DefaultObserver<T> {

    public abstract void onProgress(int progress, long total);

    public File savaFile(ResponseBody responseBody, String destFileDir, String destFileName) throws IOException {
        InputStream is = null;
        byte[] buf = new byte[8192];
        int len = 0;
        FileOutputStream fos = null;

        try {
            is = responseBody.byteStream();
            final long total = responseBody.contentLength();
            long sum = 0;

            File dir = new File(destFileDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dir, destFileName);
            fos = new FileOutputStream(file);
            while ((len = is.read(buf)) != -1) {
                sum += len;
                fos.write(buf, 0, len);
                final long finalSun = sum;
                onProgress((int) (finalSun * 100 /total), total);
            }
            fos.flush();
            return file;
        } finally {
            if (null != is) {
                is.close();
            }

            if (null != fos) {
                fos.close();
            }
        }

    }
}
