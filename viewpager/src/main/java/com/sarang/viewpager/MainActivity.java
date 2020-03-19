package com.sarang.viewpager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.sarang.viewpager.basic.BasicPagerFragment;
import com.sarang.viewpager.margin.MarginFragment;
import com.sarang.viewpager.margin.MarginPagerFragment;
import com.sarang.viewpager.transition.TransitionFragment;
import com.sarang.viewpager.transition.TransitionPagerFragment;
import com.sarang.viewpager.transition.TransitionParentFragment;
import com.sarang.viewpager.zoom.ZoomPagerFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    public void goBasic(View view) {
        BasicPagerFragment.go(getSupportFragmentManager(), R.id.root);
    }

    public void goMargin(View view) {
        MarginPagerFragment.go(getSupportFragmentManager(), R.id.root);
    }

    public void goZoom(View view) {
        ZoomPagerFragment.go(getSupportFragmentManager(), R.id.root);
    }

    public void goTransition(View view) {
        TransitionParentFragment.go(getSupportFragmentManager(), R.id.root);
    }
}
