package com.sarang.netflixintro;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sarang.netflixintro.databinding.IntroFragmentBinding;

public class IntroFragment extends Fragment {

    private IntroFragmentBinding mBinding;
    private IntroViewModel mViewModel;

    public static IntroFragment newInstance() {
        return new IntroFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.intro_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding = IntroFragmentBinding.bind(view);
        mBinding.setLifecycleOwner(this);
        mBinding.viewPager.setAdapter(new IntroVpAdt(getChildFragmentManager()));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(IntroViewModel.class);
        mBinding.setViewModel(mViewModel);
        mBinding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mViewModel.setCurrentPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewModel.getSnackBar().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
        mBinding.toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                mViewModel.showSnackBar(item.getTitle().toString());
                return false;
            }
        });
    }

    public static void go(FragmentManager fragmentManager, int layoutId) {
        fragmentManager.beginTransaction()
                .add(layoutId, new IntroFragment(), IntroFragment.class.getSimpleName())
                .commit();
    }
}
