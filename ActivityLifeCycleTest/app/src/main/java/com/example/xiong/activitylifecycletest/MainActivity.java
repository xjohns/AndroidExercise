package com.example.xiong.activitylifecycletest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate");//输出日志onCreate
        setContentView(R.layout.activity_main);
        //读取临时保存的数据
        if (savedInstanceState != null){
            String tempData = savedInstanceState.getString("data_key");
            Log.d(TAG,tempData);
        }
        Button startNormalActivity = (Button) findViewById(R.id.btn_startNormalActivity);
        Button startDialogActivity = (Button) findViewById(R.id.btn_startDialogActivity);
        startNormalActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,NormalActivity.class);
                startActivity(intent);
            }
        });
        startDialogActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DialogActivity.class);
                startActivity(intent);
            }
        });
    }
    //重写临时保存数据的方法
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String tempData = "something you are writing...";
        outState.putString("data_key",tempData);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart");//输出日志onStart
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");//输出日志onResume
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause");//输出日志onPause
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop");//输出日志onStop
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");//输出日志onDestroy
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"onRestart");//输出日志onRestart
    }
}
