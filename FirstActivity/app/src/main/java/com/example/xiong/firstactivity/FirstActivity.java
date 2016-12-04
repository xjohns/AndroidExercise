package com.example.xiong.firstactivity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("FirstActivity", "Task id is " + getTaskId());//打印当前返回栈的 id
        setContentView(R.layout.activity_first);
        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //直接调用SecondActivity中构建的方法actionStart
                SecondActivity.actionStart(FirstActivity.this, "重要数据1", "重要数据2");
                //显式Intent
//                Log.d("FirstActivity", this.toString());//打印当前实例名
//                Intent intent = new Intent(FirstActivity.this,SecondActivity.class);
//                startActivity(intent);
//                startActivityForResult(intent,1);
                //隐式Intent
//                Intent intent = new Intent("com.example.xiong.firstactivity.ACTION_START");
//                startActivity(intent);
                //隐式Intent调用系统浏览器
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("http://www.baidu.com"));
//                startActivity(intent);
                //隐式Intent调用系统拨号
//                Intent intent = new Intent(Intent.ACTION_DIAL);
//                intent.setData(Uri.parse("tel:10010"));
//                startActivity(intent);

            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("FirstActivity","onRestart");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:{
                Toast.makeText(this,"add",Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.remove:{
                Toast.makeText(this,"remove",Toast.LENGTH_SHORT).show();
                break;
            }
        }
        return true;
    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        switch (requestCode){
//            case 1:{
//                if (resultCode == RESULT_OK){
//                    String returnedData = data.getStringExtra("data_return");
//                    Log.d("第一个活动",returnedData);
//                }
//                break;
//            }
//            default:
//        }
//    }
}
