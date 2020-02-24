package com.sarang.androiddeveloper;

import android.Manifest;
import android.content.pm.PackageManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int permission = MainActivity.this.checkCallingOrSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);



        if (PackageManager.PERMISSION_GRANTED == permission) {
            Log.d(TAG, "PackageManager.PERMISSION_GRANTED");
        }
        else {
            Log.d(TAG, "PackageManager.PERMISSION_DENIED");
        }

    }
}
