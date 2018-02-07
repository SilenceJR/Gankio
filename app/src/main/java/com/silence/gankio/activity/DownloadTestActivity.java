package com.silence.gankio.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.silence.gankio.R;
import com.silence.gankio.base.BaseActivity;
import com.silence.gankio.constent.Constent;
import com.silence.gankio.utils.FileDownLoadObserver;
import com.silence.gankio.utils.HttpUtil;
import com.silence.gankio.widget.ToolBarCommView;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

public class DownloadTestActivity extends BaseActivity {

    private static final int MY_CODE = 0x01;
    @BindView(R.id.btn_download)
    Button mBtnDownload;
    @BindView(R.id.tv_show)
    TextView mTvShow;
    @BindView(R.id.btn_create_file_dir)
    Button mBtnCreateFileDir;
    @BindView(R.id.iv_image)
    ImageView mIvImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, MY_CODE);
        } else {
            mTvShow.setText("SD卡权限成功取得!");
        }
    }

    @Override
    protected void initView() {
        initToolBar();
    }

    private void initToolBar() {
        ToolBarCommView toolBarCommView = new ToolBarCommView(this);
        toolBarCommView.setDefTitle().showBack(false);
        initToolBar(false, toolBarCommView).setDefaultRedBg();
    }

    @Override
    protected void initDate() {


    }

    @Override
    protected int getActivityContentView() {
        return R.layout.activity_download_test;
    }

    @Override
    public void onNetFinish() {

    }

    @OnClick({R.id.btn_create_file_dir, R.id.btn_download})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_create_file_dir:
                if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                    File file = new File(Constent.DOWNLOAD_DIR);
                    if (!file.exists()) {
                        boolean mkdirs = file.mkdirs();
                        Log.d("DownloadTestActivity", file.getAbsolutePath());
                        mTvShow.setText("下载文件夹创建" + (mkdirs ? "成功" : "失败") + "!!!");
                    } else {
                        mTvShow.setText("下载文件夹已存在");
                    }
                } else {
                    mTvShow.setText("未找到有效的SD卡!");
                }

                break;
            case R.id.btn_download:
                String fileName = Constent.DOWNLOAD_FILE_URL.substring(Constent.DOWNLOAD_FILE_URL.lastIndexOf("/") + 1);
                mTvShow.setText("");
                HttpUtil.downloadFile(Constent.DOWNLOAD_FILE_URL, Constent.DOWNLOAD_DIR, fileName, new FileDownLoadObserver<File>() {
                    String text = "";

                    @Override
                    public void onProgress(int progress, long total) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String progressText = "progress :" + progress + ", total :" + total + "\n";
                                text = text + progressText;
                                mTvShow.setText(text);
                            }
                        });

                    }

                    @Override
                    public void onNext(File file) {
                        if (View.GONE == mIvImage.getVisibility()) {
                            mIvImage.setVisibility(View.VISIBLE);
                        }
//                        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
//                        mIvImage.setImageBitmap(bitmap);

                        Glide.with(DownloadTestActivity.this)
                                .load(file.getAbsolutePath())
                                .into(mIvImage);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mTvShow.setText(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_CODE:
                mTvShow.setText("SD卡权限成功取得!");
                break;
            default:
                break;
        }
    }
}
