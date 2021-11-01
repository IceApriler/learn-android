package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
        setContentView(R.layout.activity_second);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        /**
         * 取值：intent.getStringExtra、intent.getIntExtra、intent.getBooleanExtra、...
         */
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView);
        textView.setText(message);

        registerClick();
    }

    private void registerClick() {
        /**
         * 1. 监听事件。
         * 2. 使用finish销毁当前Activity，实现返回。
         */
        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destoryActivity();
            }
        });
    }

    public void destoryActivity() {
        /**
         * 1. 构建的intent只用于传递数据，没有指定任何"意图"。
         * 2. setResult()方法是专门用于向上一个activity返回数据用的。第一个参数一般只使用RESULT_OK和RESULT_CANCELED。第二个参数则把带有数据的intent传递回去。
         */
        Intent intent = new Intent();
        intent.putExtra("data_return", "hello main firstActivity");
        setResult(RESULT_OK, intent);

        finish();
    }

    @Override
    public void onBackPressed() {
        /**
         * 1. 重写onBackPressed，这样在用户通过按下back键返回到上一个activity时，也能将intent数据返回。
         */
        super.onBackPressed();

        this.destoryActivity();
    }
}