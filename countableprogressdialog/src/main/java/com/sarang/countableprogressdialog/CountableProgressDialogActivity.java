/*
 * Copyright (c) 2020. sryang
 */

package com.sarang.countableprogressdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class CountableProgressDialogActivity extends AppCompatActivity {

    int percent = 0;
    Runnable runnable;
    Runnable runnable1;
    Handler handler = new Handler();
    int imglist[] = {
            R.drawable.sample1,
            R.drawable.sample2,
            R.drawable.sample4,
            R.drawable.sample5,
            R.drawable.sample6
    };
    int imagePosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countable_progress_dialog);

    }

    public void show(View view) {
        UploadProgressDialog uploadProgressDialog = new UploadProgressDialog(this);
        uploadProgressDialog.show();

        uploadProgressDialog.setProgressMessage("업로드 중입니다..");

        runnable1 = () -> {
            uploadProgressDialog.showImage(imglist[imagePosition++ % 5]);
            handler.postDelayed(runnable1, 1000);
        };

        handler.post(runnable1);


        runnable = () -> {
            uploadProgressDialog.setProcessProgress(percent++);
            handler.postDelayed(runnable, 10);
        };

        handler.post(runnable);

        handler.postDelayed(() -> {
            handler.removeCallbacks(runnable);
            handler.removeCallbacks(runnable1);
            uploadProgressDialog.done();
        }, 15000);
    }
}
