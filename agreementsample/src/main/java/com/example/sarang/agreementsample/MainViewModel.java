package com.example.sarang.agreementsample;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class MainViewModel extends AndroidViewModel {

    private MutableLiveData<Boolean> isAgreeMand = new MutableLiveData<>();
    private MutableLiveData<Boolean> isPersonalInfoMand = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLbsOption = new MutableLiveData<>();
    private MutableLiveData<Boolean> isAllChecked = new MutableLiveData<>();

    private MutableLiveData<String> agreeMand = new MutableLiveData<>();
    private MutableLiveData<String> personalInfoMand = new MutableLiveData<>();
    private MutableLiveData<String> lbsOption = new MutableLiveData<>();

    public void setAgreeMand(String agreeMand) {
        this.agreeMand.setValue(agreeMand);
    }

    public void setPersonalInfoMand(String personalInfoMand) {
        this.personalInfoMand.setValue(personalInfoMand);
    }

    public void setLbsOption(String lbsOption) {
        this.lbsOption.setValue(lbsOption);
    }

    public MutableLiveData<String> getAgreeMand() {
        return agreeMand;
    }

    public MainViewModel(@NonNull Application application) {
        super(application);
        isAgreeMand.setValue(false);
        isPersonalInfoMand.setValue(false);
        isLbsOption.setValue(false);
        isAllChecked.setValue(false);
    }

    public MutableLiveData<String> getPersonalInfoMand() {
        return personalInfoMand;
    }

    public MutableLiveData<String> getLbsOption() {
        return lbsOption;
    }

    public MutableLiveData<Boolean> getIsAgreeMand() {
        return isAgreeMand;
    }

    public MutableLiveData<Boolean> getIsPersonalInfoMand() {
        return isPersonalInfoMand;
    }

    public MutableLiveData<Boolean> getIsLbsOption() {
        return isLbsOption;
    }

    public void clickAllcheck() {
        //체크가 하나라도 안되어있다면 모두 체크
        //체크가 모두 되어있다면 모두 체크 해제
        boolean allCheck = (isAgreeMand.getValue() && isPersonalInfoMand.getValue() && isLbsOption.getValue());

        isAgreeMand.setValue(!allCheck);
        isPersonalInfoMand.setValue(!allCheck);
        isLbsOption.setValue(!allCheck);
    }
}
