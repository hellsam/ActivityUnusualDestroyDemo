package com.hellsam.activityunusualdestroydemo;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.security.Key;

/**
 * Activity异常销毁状态保存测试
 *
 *
 * Test1:
 * Conditions：未在xml中指定android:configChanges="orientation|screenSize"
 * Action：旋转屏幕
 * Result：先调用系统会调用onSaveInstanceState后调用onRestoreInstanceState，未调用onConfigurationChanged
 * Conclusion：当Activity异常销毁后，系统会调用onSaveInstanceState，Activity重新创建后会调用onRestoreInstanceState
 * 可以重写onSaveInstanceState方法保存数据/状态，然后在onCreate或者onRestoreInstanceState中恢复数据（在onCreate中需要判断bundle是否为空）
 *
 *
 * Test2：
 * Conditions：在xml指定android:configChanges="orientation|screenSize"
 * Action：旋转屏幕
 * Result：系统调用了onConfigurationChanged，未调用onSaveInstanceState和onRestoreInstanceState
 * Conclusion：这种情况下activity不会销毁重建，可以重写onConfigurationChanged做相关操作
 *
 *
 *
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String KEY_TEMP_STRING = "KEY_TEMP_STRING";
    private EditText mContentET;
    private String mTemp = "none";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContentET = (EditText) findViewById(R.id.et_content);
        findViewById(R.id.btn_input).setOnClickListener(this);
        findViewById(R.id.btn_output).setOnClickListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
//        mContentET.getEditableText().
        Log.e("TAG", mContentET.getSelectionStart() + "," + mContentET.getSelectionEnd());
        outState.putString(KEY_TEMP_STRING, mTemp);
        super.onSaveInstanceState(outState);
        Log.e("TAG", "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e("TAG", "onRestoreInstanceState");
        mTemp = savedInstanceState.getString(KEY_TEMP_STRING, "");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e("TAG", "onConfigurationChanged");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_input:
                mTemp = "temp123";
                break;
            case R.id.btn_output:
                Log.e("TAG", mTemp);
                break;
        }
    }
}
