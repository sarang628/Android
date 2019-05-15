package com.sarang.androiddeveloper.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sarang.androiddeveloper.R;

public class MyFragmentActivity extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener {


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
        trans.add(R.id.container, BlankFragment.newInstance("a", "b")).addToBackStack("backStack");
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
        trans.replace(R.id.container, BlankFragment.newInstance("a", "b"))/*.addToBackStack("backStack")*/;
        trans.commit();
        renewFraglemtList();
    }

    private void renewFraglemtList() {

        ((LinearLayout) findViewById(R.id.fragmentButtonLayout)).removeAllViewsInLayout();

        ((TextView) findViewById(R.id.backStackCount)).setText("" + getSupportFragmentManager().getBackStackEntryCount());

        for (int i = 0; i < getSupportFragmentManager().getBackStackEntryCount(); i++) {
            Button btn = new Button(this);
            if (getSupportFragmentManager().getFragments().get(i) instanceof BlankFragment) {
                btn.setText("" + ((BlankFragment) getSupportFragmentManager().getFragments().get(i)).count);
                final int finalI = i;
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getSupportFragmentManager().beginTransaction().show(getSupportFragmentManager().getFragments().get(finalI)).commit();
                    }
                });
                ((LinearLayout) findViewById(R.id.fragmentButtonLayout)).addView(btn);
            }
        }
    }
}
