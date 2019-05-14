package com.sarang.androiddeveloper.activity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Spinner;

import com.sarang.androiddeveloper.MyApplication;
import com.sarang.androiddeveloper.R;

public class Stack1Activity extends BaseActivity {

    RecyclerView rv;
    ActivityManager activityManager;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stack1);

        ((MyApplication) getApplication()).activities.add(this);

        rv = findViewById(R.id.rv);

        rv.setAdapter(new ActivityStackRvAdt());

        spinner = findViewById(R.id.spinner);

        activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ((ActivityStackRvAdt) rv.getAdapter()).clear();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ((ActivityStackRvAdt) rv.getAdapter()).setActivityArrayList(((MyApplication) getApplication()).activities);
            }
        }, 1000);


        System.out.println("break");

    }

    public void stack(View view) {
        Intent intent = new Intent(this, StackActivity.class);
        setFalg(intent);
        startActivity(intent);
    }

    public void clear(View view) { StackActivity.go(this); }

    public static void go(Context context) {
        Intent intent = new Intent(context, Stack1Activity.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(intent);
    }


    public void stack1(View view) {
        Intent intent = new Intent(this, Stack1Activity.class);
        setFalg(intent);
        startActivity(intent);

    }

    protected void setFalg(Intent intent) {
        String selectedItem = (String) spinner.getSelectedItem();


        if (selectedItem.equals("FLAG_ACTIVITY_BROUGHT_TO_FRONT")) {
            intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        }
        else if (selectedItem.equals("FLAG_ACTIVITY_CLEAR_TOP")) {
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        else if (selectedItem.equals("FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS")) {
            intent.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        }
        else if (selectedItem.equals("FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY")) {
            intent.setFlags(Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY);
        }
        else if (selectedItem.equals("FLAG_ACTIVITY_MULTIPLE_TASK")) {
            intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        }
        else if (selectedItem.equals("FLAG_ACTIVITY_NO_ANIMATION")) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        }
        else if (selectedItem.equals("FLAG_ACTIVITY_NO_HISTORY")) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        }
        else if (selectedItem.equals("FLAG_ACTIVITY_NO_USER_ACTION")) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
        }
        else if (selectedItem.equals("FLAG_ACTIVITY_PREVIOUS_IS_TOP")) {
            intent.setFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
        }
        else if (selectedItem.equals("FLAG_ACTIVITY_REORDER_TO_FRONT")) {
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        }
        else if (selectedItem.equals("FLAG_ACTIVITY_RESET_TASK_IF_NEEDED")) {
            intent.setFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        }
        else if (selectedItem.equals("FLAG_ACTIVITY_SINGLE_TOP")) {
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        }
        else if (selectedItem.equals("FLAG_ACTIVITY_CLEAR_TASK")) {
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }
        else if (selectedItem.equals("FLAG_ACTIVITY_TASK_ON_HOME")) {
            intent.setFlags(Intent.FLAG_ACTIVITY_TASK_ON_HOME);
        }
        else if (selectedItem.equals("FLAG_ACTIVITY_MATCH_EXTERNAL")) {
            intent.setFlags(Intent.FLAG_ACTIVITY_MATCH_EXTERNAL);
        }
    }
}
