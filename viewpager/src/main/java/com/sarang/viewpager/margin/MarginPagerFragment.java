package com.sarang.viewpager.margin;

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
public class MarginPagerFragment extends Fragment {

    public static void go(FragmentManager fragmentManager, int layoutId) {
        fragmentManager.beginTransaction()
                .add(layoutId, new MarginPagerFragment())
                .addToBackStack(null)
                .commit();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_margin_pager, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager marginPager = view.findViewById(R.id.marginPager);
        marginPager.setClipToPadding(false);
        marginPager.setPadding(130, 50, 130, 50);
        marginPager.setPageMargin(50);
        marginPager.setAdapter(new MarginVpAdt(getChildFragmentManager()));
    }
}
