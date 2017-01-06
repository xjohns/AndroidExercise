package com.xiong.example.androidthreadtest;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final int UPDATE_TEXT = 1;//整型常量UPDATE_TEXT，用于表示更新TextView这个动作
    private TextView textView;
    private Button btn_change_text;
    private Button btn_asyncTask;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case UPDATE_TEXT:{
                    //  在这里可以进行UI操作
                    textView.setText("Just Do It!");
                    break;
                }
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.tView_text);
        btn_change_text = (Button) findViewById(R.id.change_text);
        btn_change_text.setOnClickListener(this);
        btn_asyncTask = (Button) findViewById(R.id.asyncTask);
        btn_asyncTask.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.change_text:{
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = UPDATE_TEXT;
                        handler.sendMessage(message);//将Message对象发送出去
                    }
                }).start();
                break;
            }
            case R.id.asyncTask:{
                DownloadTask downloadTask = new DownloadTask();
                downloadTask.execute();
                break;
            }
            default:
                break;
        }
    }
    class DownloadTask extends AsyncTask<Void, Integer, Void> {
        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);

        @Override
    /*
    这个方法会在后台任务开始执行之前调用，用于进行一些界面上的初始化操作，比如显示一个进度条对话框等。
     */
        protected void onPreExecute() {
            progressDialog.setTitle("这是标题");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.show();//显示进度对话框
        }

        @Override
    /*
    这个方法中的所有代码都会在子线程中运行，我们应该在这里去处理所有的耗时任务。任务一旦完成就可以通
    过 return 语句来将任务的执行结果返回，如果 AsyncTask 的第三个泛型参数指定的是 Void，就可以不返回
    任务执行结果。注意，在这个方法中是不可以进行 UI 操作的，如果需要更新 UI元素，比如说反馈当前任务的
    执行进度，可以调用publishProgress(Progress...)方法来完成。
     */
        protected Void doInBackground(Void... params) {
                for (int i=0;i<=100;i++){
                    publishProgress(i);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            return null;
        }

        @Override
        /*
        当在后台任务中调用了 publishProgress(Progress...)方法后，这个方法就会很快被调用，方法中携带的参数
        就是在后台任务中传递过来的。在这个方法中可以对 UI 进行操作，利用参数中的数值就可以对界面元素进行
        相应地更新。
        */
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            //更新下载进度
            progressDialog.setProgress(values[0]);
        }
    }
}
