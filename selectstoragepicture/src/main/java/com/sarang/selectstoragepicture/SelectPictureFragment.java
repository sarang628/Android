/*
 * Copyright (c) 2020. sryang
 */

package com.sarang.selectstoragepicture;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;


import com.bumptech.glide.Glide;
import com.sarang.selectstoragepicture.databinding.SelectPictureFragmentBinding;

import java.util.ArrayList;

public class SelectPictureFragment extends Fragment {

    private SelectPictureViewModel mViewModel;
    SelectPictureFragmentBinding mBinding;
    //SelectPictureActions selectPictureActions = DependencyInjection.selectPictureActions;
    //OnSelectPicturesListener onSelectPicturesListener = DependencyInjection.onSelectPicturesListener;

    public static SelectPictureFragment newInstance() {
        return new SelectPictureFragment();
    }

    public static void go(FragmentManager supportFragmentManager, int layoutId) {
        supportFragmentManager.beginTransaction()
                .add(layoutId, SelectPictureFragment.newInstance(), SelectPictureFragment.class.getName())
                .addToBackStack(SelectPictureFragment.class.getName())
                .commit();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.select_picture_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SelectPictureViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding = SelectPictureFragmentBinding.bind(view);
        //mBinding.setActions(DependencyInjection.selectPictureActions);
        PictureManager pictureManager = PictureManager.getInstance();

        // Creating adapter for spinner
        ArrayList<String> folderList = pictureManager.requestPicFolderList(getActivity());
        if (folderList == null) {
            return;
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, folderList);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        mBinding.spinner.setAdapter(dataAdapter);

        mBinding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((SelectPicRvAdt) mBinding.selectPicRv.getAdapter()).setMyImageList(pictureManager.getPicList(getContext(),
                        folderList.get(i)
                ));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mBinding.selectPicRv.setAdapter(new SelectPicRvAdt() {
            @Override
            void clickPicture(MyImage myImage) {
                Glide.with(getContext())
                        .load(myImage.getData())
                        .into(mBinding.imageView2);
            }
        });

        mBinding.textView.setOnClickListener(view1 -> {
            /*selectPictureActions.clickConfirm();
            if (onSelectPicturesListener != null)
                onSelectPicturesListener.onSelectPictures(getSelectedImage());*/
        });
    }

    ArrayList<MyImage> getSelectedImage() {
        return ((SelectPicRvAdt) mBinding.selectPicRv.getAdapter()).getSelectedImgList();
    }
}
