package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class NormalActivity extends AppCompatActivity {
    private static final String TAG = "LifeCycle: NormalActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
    }
    public void onBackPressed() {
        super.onBackPressed();
        Log.i(TAG, "onBackPressed: NormalActivity返回MainActivity");
    }
}