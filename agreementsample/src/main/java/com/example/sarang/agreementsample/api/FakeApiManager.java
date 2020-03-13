package com.example.sarang.agreementsample.api;

import com.example.sarang.agreementsample.ApiManager;

public class FakeApiManager implements ApiManager {
    @Override
    public void getAgreementTerms(OnTermsCallbackListener onTermsCallbackListener) {
        onTermsCallbackListener.onReceive("aaaaaaaaaaaaaaaa","bbbbbbbbbbbbbb","ccccccccccccccc");
    }
}
