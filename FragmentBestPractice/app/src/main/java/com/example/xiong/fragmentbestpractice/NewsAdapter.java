package com.example.xiong.fragmentbestpractice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by xiong on 2016/12/14.
 */

public class NewsAdapter extends ArrayAdapter<News> {
    private int resourceId;

    public NewsAdapter(Context context, int textViewResourceId, List<News> objects){
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        News news = getItem(position);
        View view;
        if (convertView == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.news_item, null);
        }else {
            view = convertView;
        }
        TextView newsTextView = (TextView) view.findViewById(R.id.news_title);
        newsTextView.setText(news.getTitle());
        return view;
    }
}
