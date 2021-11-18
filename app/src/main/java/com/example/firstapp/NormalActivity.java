package com.example.firstapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Objects;

/**
 * 如果不喜欢使用匿名类的方式注册监听器。可以使用实现接口的方式进行注册。
 * 1. public class NormalActivity implements View.OnClickListener 实现接口
 * 2. button.setOnClickListener(this) 注册
 * 3. public void onClick(View v) 实现接口的点击事件回调
 */
public class NormalActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "LifeCycle: NormalActivity";
    private EditText editText;
    private ImageView imageView;
    private ProgressBar progressBar;
    private Integer[] buttonIdList = {R.id.button01, R.id.button02, R.id.button03, R.id.button04, R.id.button05, R.id.button06};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);

        Objects.requireNonNull(getSupportActionBar()).setTitle("NormalActivity");

        /**
         * 为所有button绑定事件
         */
        for (int i = 1; i < buttonIdList.length; i++) {
            Button button = (Button) findViewById(buttonIdList[i]);
            button.setOnClickListener(this);
        }

        editText = (EditText) findViewById(R.id.edit_text01);
        imageView = (ImageView) findViewById(R.id.image_view);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }
    public void onBackPressed() {
        super.onBackPressed();
        Log.i(TAG, "onBackPressed: NormalActivity返回MainActivity");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button01:
                Log.d(TAG, "onClick: button01");
                /**
                 * 1. getText()获取输入框的内容。toString()转换成字符串。
                 */
                String inputText = editText.getText().toString();
                Toast.makeText(NormalActivity.this, inputText, Toast.LENGTH_SHORT).show();
                break;
            case R.id.button02:
                /**
                 * 2. setImageResource()给ImageView组件设置图片资源。
                 */
                imageView.setImageResource(R.drawable.img_2);
                break;
            case R.id.button03:
                /**
                 * 3. getVisibility()获取控件的可见性。setVisibility()设置可见性。
                 */
                if (progressBar.getVisibility() == View.GONE) {
                    progressBar.setVisibility(View.VISIBLE);
                } else {
                    /**
                     * 可以传 View.VISIBlE、View.INVISIBLE、View.GONE 三种值。
                     */
                    progressBar.setVisibility(View.GONE);
                }
                break;
            case R.id.button04:
                /**
                 * 4. getProgress()获取进度值。setProgress()设置进度值。
                 */
                int progress = progressBar.getProgress();
                progress = progress + 10;
                progressBar.setProgress(progress);
                break;
            case R.id.button05:
                /**
                 * 5. 弹出一个对话框。
                 */
                AlertDialog.Builder dialog = new AlertDialog.Builder(NormalActivity.this);
                dialog.setTitle("这是Dialog的Title");
                dialog.setMessage("这里是Dialog的message。");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                // 使用lamda表达式语法
                dialog.setNegativeButton("取消", (dialog1, which) -> {

                });
                dialog.show();
                break;
            case R.id.button06:
                /**
                 * 5. 弹出一个加载进度对话框。
                 */
                ProgressDialog progressDialog = new ProgressDialog(NormalActivity.this);
                progressDialog.setTitle("这个是ProgressDialog");
                progressDialog.setMessage("加载中...");
                progressDialog.setCancelable(true);
                progressDialog.show();
                break;
            default:
                break;
        }
    }
}