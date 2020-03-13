package com.example.sarang.agreementsample.api;

import com.example.sarang.agreementsample.ApiManager;

import java.util.ArrayList;

public class RealApiManager implements ApiManager {
    ArrayList<String> list_agree = new ArrayList<>();

    @Override
    public void getAgreementTerms(OnTermsCallbackListener onTermsCallbackListener) {

    }
}
