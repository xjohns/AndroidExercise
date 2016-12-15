package com.example.xiong.fragmentbestpractice;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by xiong on 2016/12/14.
 */

public class NewsContentFragment extends Fragment {
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.news_content_frag, container, false);
        return view;
    }
    //构造refresh方法刷新标题和内容
    public void refresh(String newTitle, String newContent){
        View visibilityLayout = view.findViewById(R.id.visibility_layout);
        visibilityLayout.setVisibility(View.VISIBLE);
        TextView newTitleText = (TextView) view.findViewById(R.id.news_title);
        TextView newContentText = (TextView) view.findViewById(R.id.news_content);
        newTitleText.setText(newTitle);//刷新标题
        newContentText.setText(newContent);//刷新内容
    }
}
