package com.sarang.rangable_edittext;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

import com.sarang.customedittext.R;
import com.sarang.rangable_edittext.InputFilterMinMax;

import java.text.DecimalFormat;

/**
 * Created by Sarang on 2016-02-09.
 */
public class limitableEditText extends android.support.v7.widget.AppCompatEditText {

    public limitableEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        addTextChangedListener(mWatcher);

        float min = context.obtainStyledAttributes(attrs, R.styleable.limitableEdittext)
                .getFloat(R.styleable.limitableEdittext_min, 0);
        float max = context.obtainStyledAttributes(attrs, R.styleable.limitableEdittext)
                .getFloat(R.styleable.limitableEdittext_max, 0);
        setRange(min, max);
        int digit = context.obtainStyledAttributes(attrs, R.styleable.limitableEdittext)
                .getInteger(R.styleable.limitableEdittext_digit, 0);
        setDigit(digit);


    }

    InputFilterMinMax filter;

    public void setRange(double min, double max) {
        filter = new InputFilterMinMax(min, max) {
        };
        setFilters(new InputFilter[]{filter});
    }

    public void setDigit(int digit) {
        filter.setdigit(digit);
        setFilters(new InputFilter[]{filter});
    }

    private final TextWatcher mWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
