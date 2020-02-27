/*
 * Copyright (c) 2020. sryang
 */

package com.sarang.countableprogressdialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.io.File;
import java.util.ArrayList;

public class UploadProgressDialog extends Dialog {
    private ProgressBar progressBar;
    private ProgressBar timerProgress;
    private TextView percentage;
    private TextView msg;
    private ImageView img;
    private Handler handler = new Handler();
    private String currentPath = "";

    public UploadProgressDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_upload_progress);
        progressBar = findViewById(R.id.progress_bar);
        timerProgress = findViewById(R.id.timer_progress);
        percentage = findViewById(R.id.percentage);
        msg = findViewById(R.id.msg);
        img = findViewById(R.id.img);
        setCancelable(false);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(lp);
    }

    public void showImage(String path) {
        if (currentPath.equals(path))
            return;

        img.setVisibility(View.VISIBLE);
        Glide.with(getContext())
                .load(path)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(img);
    }

    public void showImage(int resource) {
        img.setVisibility(View.VISIBLE);
        Glide.with(getContext())
                .load(resource)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(img);
    }

    public void hideImage() {
        img.setVisibility(View.GONE);
    }

    public void setProcessProgress(int processProgress) {
        progressBar.setProgress(processProgress % 100);
        timerProgress.setProgress(processProgress % 100);
        percentage.setText("" + (processProgress % 100) + "%");
    }

    public void setProgressMessage(String progressMessage) {
        msg.setText(progressMessage);
    }

    public void done() {
        handler.postDelayed(() -> {
            setProgressMessage("upload complete!");
            handler.postDelayed(() -> dismiss(), 1000);
        }, 1000);
    }

    public void failed(String msg) {
        handler.postDelayed(() -> {
            setProgressMessage(msg);
            handler.postDelayed(() -> dismiss(), 1000);
        }, 1000);
    }

    private void start() {
        show();
        handler.postDelayed(() -> {
            setProgressMessage("ready..");
        }, 1000);
    }

    private void compress(ArrayList<String> images) {
        handler.postDelayed(() -> {
            setProgressMessage("compress image..");
        }, 1000);
    }

    private void upload(ArrayList<File> pathes) {
        handler.postDelayed(() -> {
            setProgressMessage("compress done\n" + TextUtils.join(",", pathes));
            handler.postDelayed(() -> done(), 1000);
        }, 1000);
        setProgressMessage("start image upload..");
    }
}
