package com.sarang.viewpager.basic;

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
public class BasicPagerFragment extends Fragment {

    public BasicPagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_basic_pager, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager basicPager = view.findViewById(R.id.basicPager);
        basicPager.setAdapter(new BasicVpAdt(getChildFragmentManager()));
    }

    public static void go(FragmentManager fragmentManager, int layoutId) {
        fragmentManager.beginTransaction()
                .add(layoutId, new BasicPagerFragment())
                .addToBackStack(null)
                .commit();
    }
}
