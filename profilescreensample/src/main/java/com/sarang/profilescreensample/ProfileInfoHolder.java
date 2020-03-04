/*
 * Copyright (c) 2020. sryang
 */

package com.sarang.profilescreensample;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.sarang.profilescreensample.databinding.ItemProfileInfoBinding;

public class ProfileInfoHolder extends RecyclerView.ViewHolder {
    ItemProfileInfoBinding itemProfileInfoBinding;

    public static ProfileInfoHolder create(ViewGroup parent) {
        LayoutInflater inflator = LayoutInflater.from(parent.getContext());
        ItemProfileInfoBinding binding = ItemProfileInfoBinding.inflate(inflator, parent, false);
        return new ProfileInfoHolder(binding);
    }

    public ProfileInfoHolder(@NonNull ItemProfileInfoBinding itemProfileInfoBinding) {
        super(itemProfileInfoBinding.getRoot());
    }
}
