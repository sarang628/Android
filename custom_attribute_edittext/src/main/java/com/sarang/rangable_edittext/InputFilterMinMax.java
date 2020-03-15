package com.sarang.rangable_edittext;

import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;

/**
 * Created by Sarang on 2016-02-09.
 */
public class InputFilterMinMax implements InputFilter {
    private double min, max;

    public InputFilterMinMax(double min, double max) {
        this.min = min;
        this.max = max;
    }

    int mDigit = 0;

    public void setdigit(int digit) {
        mDigit = digit;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            String expected = new String();
            expected += dest.subSequence(0, dstart);
            expected += source.subSequence(start, end);
            expected += dest.subSequence(dend, dest.length());
            double input = Double.valueOf(expected);


            if (mDigit == 3) {
                if (source.equals("."))
                    return "";

                if (expected.length() > 3)
                    return "";

            }

            if (mDigit == 5) {
                if (expected.length() == 5 && Double.valueOf(expected) < 10)
                    return "";

                if (expected.length() > 5)
                    return "";
            }

            if (isInRange(min, max, input))
                return null;

        } catch (NumberFormatException nfe) {
        }
        return "";
    }

    private boolean isInRange(double a, double b, double c) {
        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }

}
