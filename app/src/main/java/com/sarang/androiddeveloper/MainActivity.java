package com.sarang.androiddeveloper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sarang.androiddeveloper.activity.StackActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StackActivity.go(this);
    }
}
