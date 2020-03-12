package com.sarang.netflixintro;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class IntroViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<Integer> currentPosition = new MutableLiveData<>();
    private MutableLiveData<String> snackBar = new MutableLiveData<>();

    public MutableLiveData<Integer> getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Integer currentPosition) {
        this.currentPosition.setValue(currentPosition);
    }

    public void showSnackBar(String str) {
        snackBar.setValue(str);
    }

    public MutableLiveData<String> getSnackBar() {
        return snackBar;
    }
}
