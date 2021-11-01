package com.example.firstapp;

import androidx.annotation.Nullable;
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
    private static final String TAG = "MainActivity";

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        /**
         * 1. 在SecondActivity销毁后，会回调本方法onActivityResult。因此需要重写，来接收返回的数据。
         * 2. 通过requestCode来判断数据来源。
         * 3. 需要注意的是，为了兼容用户通过「back返回键」来销毁activity返回时也能获取到数据，需要在SecondActivity中重写onBackPressed。
         */
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            // 来自SecondActivity
            case 1:
                if (resultCode == RESULT_OK) {
                    assert data != null;
                    String returnedData = data.getStringExtra("data_return");
                    Log.d(TAG, "onActivityResult: " + returnedData);
                }
                break;
            default:
        }
    }

    public void registerClick() {
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 显示启动：
                 * 1. Intent内部直接声明要启动的activity所对应的class。
                 * 2. 使用intent.putExtra向下一个activity传递数据。第一个参数是key，第二个参数是value。
                 * 3. 使用startActivityForResult启动下一个activity，期望在下一个活动销毁时能够返回一个结果。（即下传上）
                 */
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra(EXTRA_MESSAGE, getEditText());
                startActivityForResult(intent, 1);
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
                 * 1. 打开网页 android.intent.action.VIEW
                 * 2. 协议为http
                 */
                Intent intent3 = new Intent("android.intent.action.VIEW");
                intent3.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent3);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 隐式启动：
                 * 1. 拨打电话 android.intent.ACTION_DIAL （android系统的内置动作）
                 * 2. 协议为tel
                 */
                Intent intent4 = new Intent(Intent.ACTION_DIAL);
                intent4.setData(Uri.parse("tel:10086"));
                startActivity(intent4);
            }
        });
    }
}