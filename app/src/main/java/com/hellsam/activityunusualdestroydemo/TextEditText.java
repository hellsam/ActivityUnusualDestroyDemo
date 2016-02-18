package com.hellsam.activityunusualdestroydemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by binshenchen on 16/2/17.
 */
public class TextEditText extends EditText {
    public TextEditText(Context context) {
        super(context);
    }

    public TextEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public Parcelable onSaveInstanceState() {
        return super.onSaveInstanceState();
    }
}
