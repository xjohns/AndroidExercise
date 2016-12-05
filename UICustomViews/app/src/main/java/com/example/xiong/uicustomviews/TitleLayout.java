package com.example.xiong.uicustomviews;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by xiong on 2016/12/5.
 */
//自定义控件
public class TitleLayout extends LinearLayout {
    /*
    重写LinearLayout中带有两个参数的构造函数,通过LayoutInflater对标题栏布局进行动态加载（具体：from方法构建出一个LayoutInflater
对象，然后调用inflate方法就可以动态加载一个布局文件，该方法需加载两个参数：第一个是要加载的布局文件的id，第二是给加载好的布局再
添加一个父布局，这里指定为TitleLayout，故直接传入this.
     */
    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title, this);
        Button btn_back = (Button) findViewById(R.id.btn_title_back);
        Button btn_setting = (Button) findViewById(R.id.btn_title_setting);
        btn_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)getContext()).finish();//结束当前活动
            }
        });
        btn_setting.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "这是设置按钮", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
