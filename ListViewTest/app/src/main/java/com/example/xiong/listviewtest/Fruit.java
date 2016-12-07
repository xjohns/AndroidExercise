package com.example.xiong.listviewtest;

/**
 * Created by xiong on 2016/12/6.
 * 新建一个实体类，作为ListView适配器的适配类型;
 * 构造Fruit方法，传入name和imageId两个参数，并赋值给此类中已声明的name和imageId;
 * 构造getName方法，返回name，getImageId方法，返回imageId.
 */
public class Fruit  {
    private String name;
    private int imageId;

    public Fruit(String name, int imageId){
        this.name = name;
        this.imageId = imageId;
    }

    public String getName(){
        return name;
    }

    public int getImageId(){
        return imageId;
    }
}
