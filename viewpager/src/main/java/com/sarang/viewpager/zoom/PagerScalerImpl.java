package com.sarang.viewpager.zoom;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.sarang.viewpager.zoom.PagerScaler;

public class PagerScalerImpl implements PagerScaler {


    /**
     * PageTransmer 에서 position 변수가 -1 0 1 과 같이 나온다고 합니다.
     *
     * @param viewPager
     * @param scale
     */
    @Override
    public void scale(final ViewPager viewPager, final float scale) {
        viewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float fixScale = 1 - scale;
                fixScale = fixScale * Math.abs(position);
                page.setScaleX(1 - fixScale);
                page.setScaleY(1 - fixScale);
            }
        });
    }
}
