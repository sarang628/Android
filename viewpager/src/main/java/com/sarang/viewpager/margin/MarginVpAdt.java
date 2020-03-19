package com.sarang.viewpager.margin;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

class MarginVpAdt extends FragmentStatePagerAdapter {
    public MarginVpAdt(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return new MarginFragment();
    }

    @Override
    public int getCount() {
        return 10;
    }
}
