package com.example.xiong.filepersistencetest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class MainActivity extends AppCompatActivity {
    private EditText eText_inputData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eText_inputData = (EditText) findViewById(R.id.eText_putdata);
        String inputText = data_load();
        //TextUtils.isEmpty判断inputText中是否有内容，当传入的字符串等于null或者等于空字符串的时候，这个方法都会返回true
        if (!TextUtils.isEmpty(inputText)){
            eText_inputData.setText(inputText);
            eText_inputData.setSelection(inputText.length());//将输入光标移动到文本的末尾位置以便于继续输入
            Toast.makeText(this, "恢复数据成功", Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在应用完全结束之前保存输入框中的内容
        String inputText = eText_inputData.getText().toString();
        data_save(inputText);
    }
    //构造无返回值，有inputText参数的的方法，通过openFileOutput返回FileOutputStream对象，再通过java流的方式将数据写入文件
    public void data_save(String inputText) {
        FileOutputStream fileOutPutStream = null;
        BufferedWriter bufferedWriter = null;
        //文件读写要try-catch处理可能存在的异常
        try {
            fileOutPutStream = openFileOutput("data", Context.MODE_PRIVATE);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutPutStream));
            bufferedWriter.write(inputText);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                //若已存入数据则关闭java流
                if (bufferedWriter != null){
                    bufferedWriter.close();}
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    //构造无参数有String返回值的方法，通过openFileInput返回FileInputStream对象，再通过java流的方式将数据取出
    public String data_load() {
        FileInputStream fileInputStream = null;
        BufferedReader bufferedReader = null;
        StringBuilder content = new StringBuilder();
        try {
            fileInputStream = openFileInput("data");
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line = "";
            while ((line = bufferedReader.readLine()) != null){
                content.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }
}
