/*
 * Copyright (c) 2020. sryang
 */

package com.sarang.selectstoragepicture;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SelectStoragePictureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_storage_picture);

        SelectPictureFragment.go(getSupportFragmentManager(), R.id.root);

    }
}
