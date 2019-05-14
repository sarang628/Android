package com.sarang.androiddeveloper;

import android.app.Activity;
import android.app.Application;

import com.sarang.androiddeveloper.activity.BaseActivity;

import java.util.ArrayList;

public class MyApplication extends Application {

    public ArrayList<BaseActivity> activities = new ArrayList<>();

    public ArrayList<String> getActivityNameList() {
        ArrayList<String> stringArrayList = new ArrayList<>();

        for (Activity activity : activities) {
            stringArrayList.add(activity.getComponentName().getClassName());
        }

        return stringArrayList;
    }

}
