package com.example.sarang.agreementsample;

public interface ApiManager {
    interface OnTermsCallbackListener {
        void onReceive(String term, String term1, String term2);
    }

    void getAgreementTerms(OnTermsCallbackListener onTermsCallbackListener);
}
