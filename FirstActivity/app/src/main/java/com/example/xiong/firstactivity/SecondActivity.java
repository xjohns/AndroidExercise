package com.example.xiong.firstactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by xiong on 2016/12/3.
 */

public class SecondActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //返回数据给上一个活动
                Intent intent = new Intent();
                intent.putExtra("data_return","返回数据给第一页");
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
    //重写返回键功能
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("data_return","通过返回键返回数据给第一页");
        setResult(RESULT_OK,intent);
        finish();
    }
}
