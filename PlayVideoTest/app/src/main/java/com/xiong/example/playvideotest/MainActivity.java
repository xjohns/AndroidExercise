package com.xiong.example.playvideotest;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_play;
    private Button btn_pause;
    private Button btn_replay;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_play = (Button) findViewById(R.id.play);
        btn_pause = (Button) findViewById(R.id.pause);
        btn_replay = (Button) findViewById(R.id.replay);
        videoView = (VideoView) findViewById(R.id.vView_video);
        btn_play.setOnClickListener(this);
        btn_pause.setOnClickListener(this);
        btn_replay.setOnClickListener(this);
        initVideoPath();
    }

    private void initVideoPath() {
        File file = new File(Environment.getExternalStorageDirectory(), "video.mp4");
        videoView.setVideoPath(file.getPath());// 指定视频文件的路径
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.play:{
                if (!videoView.isPlaying()){
                    videoView.start();//开始播放
                }
                break;
            }
            case R.id.pause:{
                if (videoView.isPlaying()){
                    videoView.pause();//暂停播放
                }
                break;
            }
            case R.id.replay:{
                if (videoView.isPlaying()){
                    videoView.resume();//重新播放
                }else if (!videoView.isPlaying()){
                    videoView.resume();
                    videoView.start();
                }
                break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoView !=null){
            videoView.suspend();//释放占用的资源
        }
    }
}
