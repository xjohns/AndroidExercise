package com.example.xiong.fragmenttest;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_left = (Button) findViewById(R.id.left_btn);
        btn_left.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.left_btn:{
                /* 动态添加碎片
                1. 创建待添加的碎片实例。
                2. 获取到 FragmentManager，在活动中可以直接调用 getFragmentManager()方法得到。
                3. 开启一个事务，通过调用 beginTransaction()方法开启。
                4. 向容器内加入碎片，一般使用 replace()方法实现，需要传入容器的 id 和待添加的碎
                片实例。
                5. 提交事务，调用 commit()方法来完成。
                 */
                AnotherRightFragment fragment = new AnotherRightFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.right_layout, fragment);
                transaction.addToBackStack(null);//添加碎片到返回栈,接收一个名字用于描述返回栈的状态，一般传入 null 即可
                transaction.commit();
                break;
            }
            default:
                break;
        }
    }
}
