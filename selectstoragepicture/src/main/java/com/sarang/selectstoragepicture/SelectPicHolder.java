/*
 * Copyright (c) 2020. sryang
 */

package com.sarang.selectstoragepicture;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class SelectPicHolder extends RecyclerView.ViewHolder {

    public ImageView img;
    public ImageView dim;
    public TextView tag;

    public SelectPicHolder(View itemView) {
        super(itemView);
        img = itemView.findViewById(R.id.img);
        dim = itemView.findViewById(R.id.dim);
        tag = itemView.findViewById(R.id.imgtag);
    }
}
