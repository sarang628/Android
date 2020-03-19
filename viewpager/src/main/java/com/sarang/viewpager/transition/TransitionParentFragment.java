package com.sarang.viewpager.transition;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sarang.viewpager.R;

public class TransitionParentFragment extends Fragment {

    public TransitionParentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transition_parent, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TransitionPagerFragment.go(getChildFragmentManager(), R.id.fl_parent);
    }

    public static void go(FragmentManager fragmentManager, int layoutId) {
        fragmentManager.beginTransaction()
                .add(layoutId, new TransitionParentFragment())
                .addToBackStack(null)
                .commit();
    }

    public void goTransitionB(View view) {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.addSharedElement(getView().findViewById(R.id.tv_title),
                getView().findViewById(R.id.tv_title).getTransitionName());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.fl_parent, new TransitionbFragment());
        fragmentTransaction.commitAllowingStateLoss();
    }
}
