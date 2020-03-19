package com.sarang.viewpager.zoom;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sarang.viewpager.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ZoomPagerFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_pager, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewPager viewPager = view.findViewById(R.id.viewPager);

        viewPager.setAdapter(new ZoomVpAdt(getChildFragmentManager()));
        viewPager.setClipToPadding(false);
        viewPager.setPadding(100, 120, 100, 120);
        viewPager.setPageMargin(-60);
        new PagerScalerImpl().scale(viewPager, 0.8f);

    }

    public static void go(FragmentManager fragmentManager, int layoutId) {
        fragmentManager.beginTransaction()
                .add(layoutId, new ZoomPagerFragment())
                .addToBackStack(null)
                .commit();
    }
}
