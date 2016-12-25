package com.example.xiong.myapplication;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.start;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ProgressBar progressBar;
    private Button add;
    private Button reduce;
    private Button reset;
    private Button show;
    private ProgressDialog prodialog;
    private TextView textview;
    private WebView webview;
    private Button web;
    private String url = "http://2016.qq.com/";
    private  ProgressDialog prodialog2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        //Uri uri = Uri.parse(url);//url为要链接的地址
        //Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        //startActivity(intent);
    }

    private void init() {
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        add = (Button) findViewById(R.id.add);
        reduce = (Button) findViewById(R.id.reduce);
        reset = (Button) findViewById(R.id.reset);
        show = (Button) findViewById(R.id.show);
        textview = (TextView) findViewById(R.id.textview);
        webview = (WebView) findViewById(R.id.webview);
        web = (Button) findViewById(R.id.web);
        //webview.loadUrl("file:///android_asset/example.html");//WebView加载本地资源
        //获取第一进度条读数
        int first = progressBar.getProgress();
        //获取第二进度条读数
        int second = progressBar.getSecondaryProgress();
        //获取最大读数
        int max = progressBar.getMax();
        textview.setText("第一进度："+(int)(first/(float)max*100)+"% 第二进度："+(int)(second/(float)max*100)+"%");
        add.setOnClickListener(this);
        reduce.setOnClickListener(this);
        reset.setOnClickListener(this);
        show.setOnClickListener(this);
        web.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add:{
                //增加第一和第二进度条读数
                progressBar.incrementProgressBy(10);
                progressBar.incrementSecondaryProgressBy(10);
                break;
            }
            case R.id.reduce:{
                progressBar.incrementProgressBy(-10);
                progressBar.incrementSecondaryProgressBy(-10);
                break;
            }
            case R.id.reset:{
                progressBar.setProgress(50);
                progressBar.setSecondaryProgress(70);
                break;
            }
            case R.id.web:{
                webview.loadUrl("http://www.baidu.com/");//加载web资源
                WebSettings webSettings = webview.getSettings();
                webSettings.setJavaScriptEnabled(true);//启用支持javascript
                webview.getSettings().setCacheMode(webSettings.LOAD_CACHE_ELSE_NETWORK);
                webview.setWebViewClient(new WebViewClient(){
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        webview.loadUrl(url);
                        return true;
                    }

                });
                webview.setWebChromeClient(new WebChromeClient(){
                    @Override
                    public void onProgressChanged(WebView view, int newProgress) {
                        super.onProgressChanged(view, newProgress);
                        if (newProgress==100){
                            //网页加载完毕
                            closeDialog();
                        }
                        else{
                            //网页正在加载
                            openDialog(newProgress);
                        }
                    }

                    private void openDialog(int newProgress) {
                        if (prodialog2==null){
                            prodialog2 = new ProgressDialog(MainActivity.this);
                            prodialog2.setTitle("正在加载中，给我Hold On!");
                            prodialog2.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            prodialog2.setProgress(newProgress);
                            prodialog2.show();
                        }
                        else{
                            prodialog2.setProgress(newProgress);
                        }
                    }

                    private void closeDialog() {
                        if (prodialog2!=null&& prodialog2.isShowing()){
                            prodialog2.dismiss();
                            prodialog2=null;
                        }
                    }
                });

                break;
            }
            case R.id.show:{
                /**
                 * 页面显示风格
                 */
                //新建ProgressDialog对象
                prodialog = new ProgressDialog(MainActivity.this);
                //设置风格
                prodialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                //设置标题
                prodialog.setTitle("对话框进度条");
                //设置内容
                prodialog.setMessage("此处为内容，下面是进度条");
                //设置图标
                prodialog.setIcon(R.mipmap.ic_launcher);
                /**
                 * ProgressDialog属性设置
                 */
                prodialog.setMax(100);
                prodialog.incrementProgressBy(40);
                prodialog.setIndeterminate(false);//是否不明确显示进度
                /**
                 * 设置确定按钮
                 */
                prodialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"这是按确定后弹出的内容",Toast.LENGTH_SHORT).show();//要用show方法将其显示
                    }
                });
                //是否可以通过返回按钮退出对话框
                prodialog.setCancelable(true);
                //显示ProgressDialog
                prodialog.show();
                break;
            }
        }
        textview.setText("第一进度："+(int)(progressBar.getProgress()/(float)progressBar.getMax()*100)+"% 第二进度："+(int)(progressBar.getSecondaryProgress()/(float)progressBar.getMax()*100)+"%");

    }

    

    //改写物理按键--返回的逻辑
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            Toast.makeText(this,webview.getUrl(),Toast.LENGTH_SHORT).show();
            if (webview.canGoBack()){
                webview.goBack();//返回上一页
                return true;
            }
            else {
                System.exit(0);//退出程序
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
