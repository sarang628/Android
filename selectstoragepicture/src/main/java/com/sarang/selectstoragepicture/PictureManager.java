/*
 * Copyright (c) 2020. sryang
 */

package com.sarang.selectstoragepicture;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class PictureManager {

    private static PictureManager pictureManager;

    public static PictureManager getInstance() {
        if (pictureManager == null)
            pictureManager = new PictureManager();
        return pictureManager;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean checkPermission(AppCompatActivity activity) {
        //권한 체크 하기
        int isPermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (isPermission == PackageManager.PERMISSION_DENIED) {
            AlertDialog.Builder b = new AlertDialog.Builder(activity);
            b.setMessage("이미지를 등록하기위해선 저장소 읽기 권한이 필요합니다. 허용하시겠습니까?");
            b.setPositiveButton("yes", (dialogInterface, i) -> activity.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0x01));
            b.setNegativeButton("no", (dialogInterface, i) -> activity.finish());
            b.show();
            return false;
        }
        else {
            return true;
        }
    }

    public ArrayList<String> requestPicFolderList(Activity act) {
        AppCompatActivity appCompatActivity = (AppCompatActivity) act;
        Context context = act;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !checkPermission(appCompatActivity)) {
            return null;
        }

        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME
                , MediaStore.Images.Media.DESCRIPTION
                , MediaStore.Images.Media.PICASA_ID
                , MediaStore.Images.Media.IS_PRIVATE
                , MediaStore.Images.Media.LATITUDE
                , MediaStore.Images.Media.LONGITUDE
                , MediaStore.Images.Media.DATE_TAKEN
                , MediaStore.Images.Media.ORIENTATION
                , MediaStore.Images.Media.MINI_THUMB_MAGIC
                , MediaStore.Images.Media.BUCKET_ID
                , MediaStore.MediaColumns.DATA
                , MediaStore.MediaColumns.DISPLAY_NAME
                , MediaStore.MediaColumns.DATE_ADDED
        };

        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        Map<String, MyImage> folderMap = new TreeMap<>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
                String filePath = cursor.getString(10);
                String[] splited = cursor.getString(10).split("/");
                String folder = filePath.replace(splited[splited.length - 1], "");
                MyImage myImage = new MyImage();
                myImage.setData(folder);

                if (folderMap.get(folder) == null)
                    folderMap.put(folder, myImage);
            }
            cursor.close();
        }

        ArrayList<MyImage> myImages = new ArrayList<>(folderMap.values());
        ArrayList<String> folderList = new ArrayList<>();
        folderList.add(0, "전체");
        for (MyImage myImage : myImages) {
            folderList.add(myImage.getData());
        }
        return folderList;
    }

    public ArrayList<MyImage> getPicList(Context context, String forderName) {
        if (forderName != null && forderName.equals("전체"))
            forderName = null;

        Cursor cursor = null;
        Uri uri = null;
        String[] projection = new String[1];
        String selection = null;
        String[] selectionArgs = new String[1];
        String sortOrder = MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC";
        ArrayList<MyImage> myImageArrayList = new ArrayList<>();
        try {
            uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            if (forderName != null) {
                projection[0] = MediaStore.MediaColumns.DATA;
                selection = MediaStore.Images.ImageColumns.DATA + " LIKE ?";
                selectionArgs[0] = "%" + forderName + "%";
                cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
            }
            else {
                cursor = context.getContentResolver().query(uri, null, null, null, sortOrder);
            }

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    int columnIndex = cursor.getColumnIndex(MediaStore.MediaColumns.DATA);
                    MyImage myImage = new MyImage();
                    myImage.setData(cursor.getString(columnIndex));
                    myImageArrayList.add(myImage);
                }
                cursor.close();
            }
        } catch (Exception e) {
            Log.e("sr", e.toString());
        }
        if (cursor != null)
            cursor.close();

        return myImageArrayList;
    }
}
