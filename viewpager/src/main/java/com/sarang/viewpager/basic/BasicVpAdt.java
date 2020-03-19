package com.sarang.viewpager.basic;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

class BasicVpAdt extends FragmentStatePagerAdapter {
    public BasicVpAdt(FragmentManager childFragmentManager) {
        super(childFragmentManager);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return new BasicFragment();
    }

    @Override
    public int getCount() {
        return 10;
    }
}
