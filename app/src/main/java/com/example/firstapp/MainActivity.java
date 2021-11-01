package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * 1. 继承AppCompatActivity
 * 2. 重写onCreate
 * 3. setContentView设置页面
 * 4. 跳转Activity
 */
public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Android:", "The onCreate() event");
        registerClick();
    }
    public String getEditText() {
        EditText editText = (EditText) findViewById(R.id.editTextTextPersonName);
        return editText.getText().toString();
    }
    public void registerClick() {
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 显示启动：Intent内部直接声明要启动的activity所对应的class。
                 */
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra(EXTRA_MESSAGE, getEditText());
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 隐式启动：
                 * 1. 隐式 Intent 要比显示 Intent 含蓄的多，他并不明确指定要启动哪个 Activity，而是通过指定 action 和 category 的信息，让系统去分析这个 Intent，并找出合适的 Activity 去启动。
                 * 2. 虽然没有生命category，但是startActivity时会自动将android.intent.category.DEFAULT添加到Intent中。
                 * 3. 每个Intent只能指定一个action，却能指定多个category。
                 */
                Intent intent2 = new Intent("com.example.firstapp.ACTION_START");
                intent2.addCategory("com.example.firstapp.MY_CATEGORY");
                intent2.putExtra(EXTRA_MESSAGE, getEditText());
                startActivity(intent2);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 隐式启动：
                 *
                 */
                Intent intent3 = new Intent("android.intent.action.VIEW");
                intent3.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent3);
            }
        });
    }
}