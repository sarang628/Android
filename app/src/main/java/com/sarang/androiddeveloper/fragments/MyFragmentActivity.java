package com.sarang.androiddeveloper.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sarang.androiddeveloper.R;

import java.util.ArrayList;

public class MyFragmentActivity extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener {

    ArrayList<String> tags = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fragment);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Toast.makeText(this, uri.toString(), Toast.LENGTH_LONG).show();
    }

    public static void go(Context context) {
        Intent intent = new Intent(context, MyFragmentActivity.class);
        context.startActivity(intent);
    }

    public void addToBackStack(View v) {

        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.add(R.id.container, BlankFragment.newInstance("a", "b"), "" + BlankFragment.createCount).addToBackStack("backStack");
        tags.add("" + BlankFragment.createCount);
        trans.commit();
        renewFraglemtList();


    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
            renewFraglemtList();
        }
        else
            super.onBackPressed();
    }

    public void replace(View v) {
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.replace(R.id.container, BlankFragment.newInstance("a", "b"), "" + BlankFragment.createCount).addToBackStack("backStack");
        tags.add("" + BlankFragment.createCount);
        trans.commit();
        renewFraglemtList();
    }

    private void renewFraglemtList() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                ((TextView) findViewById(R.id.backStackCount)).setText("" + getSupportFragmentManager().getBackStackEntryCount());
                ((LinearLayout) findViewById(R.id.fragmentButtonLayout)).removeAllViewsInLayout();
                /*
                for (int i = 0; i < getSupportFragmentManager().getBackStackEntryCount(); i++) {
                    Button btn = new Button(MyFragmentActivity.this);
                    if (getSupportFragmentManager().getFragments().get(i) != null && getSupportFragmentManager().getFragments().get(i) instanceof BlankFragment) {
                        btn.setText("" + ((BlankFragment) getSupportFragmentManager().getFragments().get(i)).count);
                        final int finalI = i;
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                for (int i = 0; i < getSupportFragmentManager().getBackStackEntryCount(); i++) {
                                    getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager().getFragments().get(i)).commit();
                                }
                                getSupportFragmentManager().beginTransaction().show(getSupportFragmentManager().getFragments().get(finalI)).commit();

                            }
                        });
                        ((LinearLayout) findViewById(R.id.fragmentButtonLayout)).addView(btn);
                    }
                }*/
                for (int i = 0; i < tags.size(); i++) {
                    Button btn = new Button(MyFragmentActivity.this);
                    btn.setText("" + tags.get(i));
                    final int finalI = i;
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //                            for (int i = 0; i < getSupportFragmentManager().getBackStackEntryCount(); i++) {
                            //                                getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager().findFragmentByTag(tags.get(i))).commit();
                            //                            }
                            //                            getSupportFragmentManager().beginTransaction().show(getSupportFragmentManager().findFragmentByTag(tags.get(finalI))).commit();
                            FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
                            trans.replace(R.id.container, getSupportFragmentManager().findFragmentByTag("" + (finalI + 1)));
                            trans.commit();

                        }
                    });
                    ((LinearLayout) findViewById(R.id.fragmentButtonLayout)).addView(btn);

                }
            }
        }, 500);
    }
}
