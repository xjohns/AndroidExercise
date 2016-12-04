package com.example.xiong.firstactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by xiong on 2016/12/3.
 */

public class ThirdActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ThirdActivity", "Task id is " + getTaskId());
        setContentView(R.layout.activity_third);
        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCollector.finishAll();//调用活动管理器的finishAll()方法退出应用
            }
        });
    }
}
