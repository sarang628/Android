package com.example.sarang.agreementsample;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.example.sarang.agreementsample.api.FakeApiManager;
import com.example.sarang.agreementsample.databinding.ActivityMainBinding;
import com.example.sarang.agreementsample.util.UtilNetwork;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;
    private MainViewModel mViewModel;
    private ApiManager apiManager = new FakeApiManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mBinding.setLifecycleOwner(this);
        mBinding.setViewModel(mViewModel);
        setTitle("회원가입");
        GetAgreementTerms();
    }

    private void GetAgreementTerms() {

        //네트워크 연결 확인
        if (UtilNetwork.networkCheck(getApplicationContext()) == 0) {
            return;
        }

        if (apiManager != null)
            apiManager.getAgreementTerms(new ApiManager.OnTermsCallbackListener() {
                @Override
                public void onReceive(String term, String term1, String term2) {
                    mViewModel.setAgreeMand(term);
                    mViewModel.setPersonalInfoMand(term1);
                    mViewModel.setLbsOption(term2);
                }
            });
    }
}
