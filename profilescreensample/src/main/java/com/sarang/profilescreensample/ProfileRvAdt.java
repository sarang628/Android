/*
 * Copyright (c) 2020. sryang
 */

package com.sarang.profilescreensample;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sarang.base_feed.TimeLineVH;
import com.sarang.torangdatamodel.model.Review;

import java.util.ArrayList;

class ProfileRvAdt extends RecyclerView.Adapter {
    ArrayList<Review> feeds = new ArrayList<>();
    private FragmentManager fragmentManager;

    public ProfileRvAdt(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void setFeeds(ArrayList<Review> feeds) {
        this.feeds = feeds;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? 0 : 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return ProfileInfoHolder.create(parent);
        }
        else {
            return TimeLineVH.create(parent);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TimeLineVH) {
            if (fragmentManager != null)
                ((TimeLineVH) holder).setFragmentManager(fragmentManager);

            ((TimeLineVH) holder).setFeed(feeds.get(position - 1));

        }
    }

    @Override
    public int getItemCount() {
        return feeds.size() + 1;
    }
}
