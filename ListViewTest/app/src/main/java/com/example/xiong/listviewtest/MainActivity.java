package com.example.xiong.listviewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private List<Fruit> fruit_list = new ArrayList<Fruit>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruits();//初始化水果数据
        FruitAdapter adapter = new FruitAdapter(MainActivity.this, R.layout.fruit_item, fruit_list);
        ListView list_View = (ListView) findViewById(R.id.listView);
        list_View.setAdapter(adapter);
    }

    private void initFruits() {
        Fruit apple = new Fruit("apple", R.drawable.apple);
        fruit_list.add(apple);
        Fruit grape = new Fruit("grape", R.drawable.grape);
        fruit_list.add(grape);
        Fruit mango = new Fruit("mango", R.drawable.mango);
        fruit_list.add(mango);
        Fruit pear = new Fruit("pear", R.drawable.pear);
        fruit_list.add(pear);
        Fruit watermelon = new Fruit("watermelon", R.drawable.watermelon);
        fruit_list.add(watermelon);
    }
}
