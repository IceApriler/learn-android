package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.Objects;

public class DialogActivity extends BaseActivity {
    private static final String TAG = "LifeCycle: DialogActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

//        Objects.requireNonNull(getSupportActionBar()).setTitle("DialogActivity");
    }
    public void onBackPressed() {
        super.onBackPressed();
        Log.i(TAG, "onBackPressed: DialogActivity返回MainActivity");
    }
}