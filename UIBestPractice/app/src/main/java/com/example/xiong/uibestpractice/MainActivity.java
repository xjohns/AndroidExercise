package com.example.xiong.uibestpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView msgListView;
    private EditText inputText;
    private Button btn_send;
    private MsgAdapter msgAdapter;
    private List<Msg> msgList = new ArrayList<Msg>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initsMsgs();
        msgAdapter = new MsgAdapter(MainActivity.this, R.layout.list_item, msgList);
        inputText = (EditText) findViewById(R.id.eText_send);
        btn_send = (Button) findViewById(R.id.btn_send);
        msgListView = (ListView) findViewById(R.id.list_message);
        msgListView.setAdapter(msgAdapter);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                if (!"".equals(content)){
                    Msg msg = new Msg(content, Msg.TYPE_SEND);
                    msgList.add(msg);
                    msgAdapter.notifyDataSetChanged();//当有新消息时，刷新ListView中的显示
                    msgListView.setSelection(msgList.size());//将ListView定位到最后一行
                    inputText.setText("");//清空输入框中的内容
                }
            }
        });

    }

    private void initsMsgs() {
        Msg msg1 = new Msg("Hello, it's nice day!", Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("Not really, in fact, it's raining.", Msg.TYPE_SEND);
        msgList.add(msg2);
        Msg msg3 = new Msg("Well, sometimes it means you can have fun at home.", Msg.TYPE_RECEIVED);
        msgList.add(msg3);
    }
}
