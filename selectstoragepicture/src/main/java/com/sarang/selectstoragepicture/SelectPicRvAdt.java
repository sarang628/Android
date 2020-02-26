/*
 * Copyright (c) 2020. sryang
 */

package com.sarang.selectstoragepicture;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.ArrayList;

public abstract class SelectPicRvAdt extends RecyclerView.Adapter {
    abstract void clickPicture(MyImage myImage);

    private ArrayList<MyImage> myImageList;
    private ArrayList<MyImage> selectedImgList = new ArrayList<>();
    boolean isCheckIn = false;

    public ArrayList<MyImage> getSelectedImgList() {
        return selectedImgList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SelectPicHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_picture_select, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SelectPicHolder selectPicHolder = (SelectPicHolder) holder;
        //ImageLoadBindingAdapter.loadImage(selectPicHolder.img, myImageList.get(position).getData());
        Glide.with(holder.itemView.getContext())
                .load(myImageList.get(position).getData())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(selectPicHolder.img);

        if (selectedImgList.contains(myImageList.get(position))) {
            selectPicHolder.dim.setVisibility(View.VISIBLE);
            selectPicHolder.tag.setVisibility(View.VISIBLE);
            selectPicHolder.tag.setText("" + selectedImgList.indexOf(myImageList.get(position)));
        }
        else {
            selectPicHolder.dim.setVisibility(View.GONE);
            selectPicHolder.tag.setVisibility(View.GONE);
            selectPicHolder.tag.setText("");
        }

        ((SelectPicHolder) holder).img.setOnClickListener((View view) -> {
            clickPicture(myImageList.get(position));
            boolean isContained = selectedImgList.contains(myImageList.get(position));
            // 이미지를 선택했을때 선택 여부에 따라 딤과 카운트 보여지는 여부
            selectPicHolder.dim.setVisibility(isContained ? View.GONE : View.VISIBLE);
            selectPicHolder.tag.setVisibility(isContained ? View.GONE : View.VISIBLE);

            if (isContained) {
                selectedImgList.remove(myImageList.get(position));
                notifyDataSetChanged();
            }
            else {
                if (isCheckIn) {
                    selectedImgList.removeAll(selectedImgList);
                    notifyDataSetChanged();
                }
                selectedImgList.add(myImageList.get(position));
                selectPicHolder.tag.setText("" + (selectedImgList.size() - 1));
            }
        });
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (myImageList != null)
            count = myImageList.size();
        return count;
    }

    public void setMyImageList(ArrayList<MyImage> myImageList) {
        this.myImageList = myImageList;
        notifyDataSetChanged();
    }

    public void setSelectedImageList(ArrayList<MyImage> selectedImgList) {
        this.selectedImgList = selectedImgList;
    }

    public void setIsCheckIn(boolean isCheckIn) {
        this.isCheckIn = isCheckIn;
    }
}
