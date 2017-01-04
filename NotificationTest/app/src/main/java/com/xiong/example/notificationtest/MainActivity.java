package com.xiong.example.notificationtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_send_notice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_send_notice = (Button) findViewById(R.id.send_notice);
        btn_send_notice.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
    switch (v.getId()){
        case R.id.send_notice:{
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            Notification.Builder builder = new Notification.Builder(this).setTicker("这是显示于屏幕顶端状态栏的文本").setSmallIcon(R.mipmap.ic_launcher);
            builder.setContentTitle("内容标题");
            builder.setContentText("内容文本");
            Notification notification = builder.getNotification();
            manager.notify(1, notification);
            break;
        }
        default:
            break;
    }
    }
}
