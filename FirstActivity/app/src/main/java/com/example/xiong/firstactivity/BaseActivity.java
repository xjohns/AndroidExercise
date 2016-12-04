package com.example.xiong.firstactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by xiong on 2016/12/4.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("BaseActivity",getClass().getSimpleName());//获取当前实例的类名，并打印
        ActivityCollector.addActivity(this);//将正在创建的活动添加进活动管理器ActivityCollector
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);//将要销毁的活动从活动管理器中移除
    }
}
