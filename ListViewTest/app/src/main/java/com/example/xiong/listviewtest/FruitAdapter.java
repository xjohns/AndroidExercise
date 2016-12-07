package com.example.xiong.listviewtest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by xiong on 2016/12/6.
 * 自定义适配器
 */

public class FruitAdapter extends ArrayAdapter <Fruit> {
    private int resourceId;

    public FruitAdapter(Context context, int textViewResourceId, List<Fruit> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }
    //重写getView()方法，该方法在每个子项被滚动到屏幕内的时候会被调用
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Fruit fruit = getItem(position);//获取当前项的fruit实例
        View view;
        //优先使用已缓存的布局
        if (convertView == null){
        view = LayoutInflater.from(getContext()).inflate(resourceId, null);//用LayoutInflater为子项加载传入的布局
             }
        else {
            view = convertView;
        }
        ImageView fruit_image = (ImageView) view.findViewById(R.id.iView_fruit_image);
        TextView fruit_name = (TextView) view.findViewById(R.id.tView_fruit_name);
        fruit_image.setImageResource(fruit.getImageId());
        fruit_name.setText(fruit.getName());
        return view;
    }
}
