/*
 * Copyright (c) 2020. sryang
 */

package com.sarang.profilescreensample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.sarang.profile.databinding.FragmentProfileBinding;
import com.sarang.torangdatamodel.model.Feed;
import com.sarang.torangdi.DependencyInjection;
import com.sarang.torangdi.ProfileActions;
import com.sarang.torangdi.viewmodels.ProfileViewModel;

public class ProfileFragment extends Fragment {

    private ProfileViewModel mViewModel;
    private FragmentProfileBinding mBinding;
    private ProfileActions profileActions = DependencyInjection.profileActions;

    public static ProfileFragment get(FragmentManager supportFragmentManager) {
        return (ProfileFragment) supportFragmentManager.findFragmentByTag(ProfileFragment.class.getSimpleName());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        mBinding = FragmentProfileBinding.bind(view);
        mBinding.setLifecycleOwner(this);
        mBinding.setActions(profileActions);

        mBinding.rvReview.setAdapter(new ProfileRvAdt(getChildFragmentManager()));
    }

    private ProfileRvAdt getAdapter() {
        return (ProfileRvAdt) mBinding.rvReview.getAdapter();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ProfileViewModel.obtain(getActivity());

        /*mBinding.button.setOnClickListener(
                view -> profileActions.clickFollow(user,
                        user -> mBinding.setUser(user)));*/

        mViewModel.getUser().observe(getActivity(), user -> {
            mBinding.setUser(user);
            mViewModel.loadFeeds();
        });

        mViewModel.getFeeds().observe(getActivity(), feeds -> {
            for (Feed feed : feeds) {
                feed.user = mViewModel.getUser().getValue();
            }
            getAdapter().setFeeds(feeds);
        });

    }

    public static void go(FragmentManager fragmentManager, int layoutId) {
        ProfileFragment profileFragment = new ProfileFragment();
        fragmentManager.beginTransaction()
                .add(layoutId, profileFragment, ProfileFragment.class.getSimpleName())
                .addToBackStack(ProfileFragment.class.getSimpleName())
                .commit();
    }

    @Override
    public void onDetach() {
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        super.onDetach();
    }
}
