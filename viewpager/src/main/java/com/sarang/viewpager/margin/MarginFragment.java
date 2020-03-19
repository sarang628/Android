package com.sarang.viewpager.margin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sarang.viewpager.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MarginFragment extends Fragment {

    public MarginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_margin, container, false);
    }
}
