package com.sarang.netflixintro;

import android.view.View;

public class BindingAdapter {
    @androidx.databinding.BindingAdapter("app:selected")
    public static void selected(View v, Boolean b) {
        v.setSelected(b);
    }
}
