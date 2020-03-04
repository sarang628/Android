/*
 * Copyright (c) 2020. sryang
 */

package com.sarang.profilescreensample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ProfileScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);

        ProfileFragment.go(getSupportFragmentManager(), R.id.container);
    }
}
