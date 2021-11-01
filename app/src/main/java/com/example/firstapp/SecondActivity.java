package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * 1. 接收activity跳转参数
 * 2. TextView设置text
 * 3. setOnClickListener注册监听onClick事件
 * 4. 销毁当前Activity，实现返回
 */
public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_message);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView);
        textView.setText(message);

        /**
         * 1. 注册监听textView的click事件
         * 2. 销毁当前Activity，实现返回
         */
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destoryActivity();
            }
        });
    }
    public void destoryActivity() {
        finish();
    }
}