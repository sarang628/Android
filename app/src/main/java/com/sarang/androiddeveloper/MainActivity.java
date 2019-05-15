package com.sarang.androiddeveloper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.sarang.androiddeveloper.activity.StackActivity;
import com.sarang.androiddeveloper.fragments.MyFragmentActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void activity(View view) {
        StackActivity.go(this);
    }

    public void fragment(View view) {
        MyFragmentActivity.go(this);
    }
}
