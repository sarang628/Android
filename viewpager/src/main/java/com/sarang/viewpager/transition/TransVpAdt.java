package com.sarang.viewpager.transition;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

class TransVpAdt extends FragmentStatePagerAdapter {
    public TransVpAdt(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return new TransitionFragment();
    }

    @Override
    public int getCount() {
        return 10;
    }
}
