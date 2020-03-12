package com.sarang.netflixintro;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

class IntroVpAdt extends FragmentStatePagerAdapter {
    public IntroVpAdt(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Intro1Fragment();
            case 1:
                return new Intro2Fragment();
            case 2:
                return new Intro3Fragment();
            case 3:
                return new Intro4Fragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
