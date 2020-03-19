package com.sarang.viewpager.zoom;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.sarang.viewpager.zoom.BlankFragment;

public class ZoomVpAdt extends FragmentStatePagerAdapter {
    public ZoomVpAdt(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return new BlankFragment();
    }

    @Override
    public int getCount() {
        return 10;
    }
}
