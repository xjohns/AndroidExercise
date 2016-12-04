package com.example.xiong.firstactivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by xiong on 2016/12/3.
 */

public class SecondActivity extends BaseActivity {
    /*
    构造actionStart方法，完成Intent的构建，所有SecondActivity中需要的数据都通过actionStart()方法的参数传递过来，然
后把它们存储到Intent
     */
    public static void actionStart(Context context, String data1, String data2){
        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra("param1", data1);
        intent.putExtra("param2", data2);
        context.startActivity(intent);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("SecondActivity", "Task id is " + getTaskId());
        setContentView(R.layout.activity_second);
        //接收并打印传入的数据
        Intent getData = getIntent();
        String d1 = getData.getStringExtra("param1");
        String d2 = getData.getStringExtra("param2");
        Log.d("SecondAcitivity", "数据已传入：" + d1 +","+d2);

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this,ThirdActivity.class);
                startActivity(intent);
                //返回数据给上一个活动
//                Intent intent = new Intent();
//                intent.putExtra("data_return","返回数据给第一页");
//                setResult(RESULT_OK,intent);
//                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("SecondActivity","onDestroy");
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
