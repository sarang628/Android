package com.sarang.androiddeveloper.activity;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sarang.androiddeveloper.R;

public class ContextSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_sample);

        Context context = this;

        /**
         System Service 사용
         */
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        /**
         리소스 불러오기
         */
        context.getResources().getString(R.string.app_name);

        /**
         클래스에 context를 할당하여 사용하지 말자
         */
        new sample().setContext(context);

    }

    public class sample {
        Context context;

        @Deprecated
        public void setContext(Context context) {

        }
    }
}
