package com.example.xiong.firstactivity;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiong on 2016/12/4.
 */

public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<Activity>();

    public static void addActivity (Activity activity){
        activities.add(activity);
    }
    public static void removeActivity (Activity activity){
        activities.remove(activity);
    }
    //构造finishAll方法，结束应用的所有活动
    public static void finishAll (){
        for (Activity activity : activities){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
