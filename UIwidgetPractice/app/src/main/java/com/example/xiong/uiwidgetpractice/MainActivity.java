package com.example.xiong.uiwidgetpractice;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{//实现点击监听器接口
    private TextView tView;
    private Button btn_change;
    private Button btn_send;
    private Button btn_aDialog;
    private Button btn_pDialog;
    private EditText eText;
    private ImageView iView;
    Integer srcPictureCode = 1;//用一个整数来标记ImageView的当前图片
    private ProgressBar pBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    //构造init()方法，专门初始化数据或对象
    private void init() {
        tView = (TextView) findViewById(R.id.tView);
        btn_change = (Button) findViewById(R.id.btn_change);
        btn_send = (Button) findViewById(R.id.btn_send);
        btn_aDialog = (Button) findViewById(R.id.btn_aDialog);
        btn_pDialog = (Button) findViewById(R.id.btn_pDialog);
        eText = (EditText) findViewById(R.id.eText);
        iView = (ImageView) findViewById(R.id.iView);
        pBar = (ProgressBar) findViewById(R.id.pBar);
        btn_change.setOnClickListener(this);
        btn_send.setOnClickListener(this);
        btn_aDialog.setOnClickListener(this);
        btn_pDialog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_change :{
                //通过整数标记判断ImageView当前图片，实现点击按钮切换图片
                if(srcPictureCode == 1){
                    iView.setImageResource(R.mipmap.ic_launcher_black);
                    srcPictureCode = 2;
                }
                else if (srcPictureCode == 2){
                    iView.setImageResource(R.mipmap.ic_launcher);
                    srcPictureCode = 1;
                }
                //判断进度条是否可见，实现进度条显示和结束的切换
                if (pBar.getVisibility() == View.GONE){
                    pBar.setVisibility(View.VISIBLE);
                }else {
                    pBar.setVisibility(View.GONE);
                }
                break;
            }
            //点击按钮，让TextView显示输入的内容
            case R.id.btn_send :{
                String inputText = eText.getText().toString();
                tView.setText(inputText);
                break;
            }
            //提示对话框的用法练习
            case R.id.btn_aDialog :{
                AlertDialog.Builder aDialog = new AlertDialog.Builder(MainActivity.this);
                aDialog.setTitle("警告");
                aDialog.setMessage("确定要点击按钮？");
                aDialog.setCancelable(false);
                aDialog.setPositiveButton("毋庸置疑", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tView.setText("什么都没有发生。。");
                    }
                });
                aDialog.setNegativeButton("稍安勿躁", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        iView.setImageResource(R.mipmap.ic_launcher_black);
                    }
                });
                aDialog.show();
                break;
            }
            //进度条对话框的练习
            case R.id.btn_pDialog :{
                ProgressDialog pDialog = new ProgressDialog(MainActivity.this);
                pDialog.setTitle("进度条对话框");
                pDialog.setMessage("正在加载中，请稍后");
                pDialog.setCancelable(true);
                pDialog.show();
                break;
            }
            default:
                break;
        }
    }
}
