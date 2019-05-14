package com.sarang.androiddeveloper.activity;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sarang.androiddeveloper.R;

import java.util.ArrayList;

class ActivityStackRvAdt extends RecyclerView.Adapter<ActivityStackVH> {

    ArrayList<BaseActivity> activityArrayList = new ArrayList<>();

    public void setActivityArrayList(ArrayList<BaseActivity> activityArrayList) {
        this.activityArrayList = activityArrayList;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public ActivityStackVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ActivityStackVH(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_activity, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityStackVH activityStackVH, int i) {
        BaseActivity act = activityArrayList.get(i);
        TextView tv = activityStackVH.itemView.findViewById(R.id.act_info);
        tv.setText(act.getComponentName().getClassName());

        View root = activityStackVH.itemView.findViewById(R.id.root);
        root.setBackgroundColor(act.isDestroyed() ? Color.parseColor("#55FF0000")
                : act.isResumed ? Color.parseColor("#5500FF00")
                : Color.parseColor("#FFFFFF")
        );

        tv.append(":" + act.id);


    }

    @Override
    public int getItemCount() {
        return activityArrayList.size();
    }

    public void clear() {
        activityArrayList = new ArrayList<>();
        notifyDataSetChanged();
    }
}
