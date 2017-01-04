package com.example.xiong.databasetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by xiong on 2016/12/21.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
    //Book表的SQL建表语句
    public static final String CREATE_BOOK = "create table Book (" +
            "id integer primary key autoincrement," +
            "author text," +
            "price real," +
            "pages integer," +
            "name text," +
            "category_id interger)";
    public static final String CREATE_CATEGORY = "create table Category(" +
            "id integer primary key autoincrement," +
            "category_name text," +
            "category_code integer)";
    private Context mContext;
    //第三个参数允许我们在查询数据的时候返回一个自定义的Cursor，一般都是传入 null，第四个参数表示当前数据库的版本号
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);//调用SQLiteDatabase的execSQL()方法去执行SQL语句
        db.execSQL(CREATE_CATEGORY);
        Toast.makeText(mContext, "创建成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion){
            case 1:{
                db.execSQL(CREATE_CATEGORY);
            }
            case 2:{
                db.execSQL("alter table Book add column category_id interger");
            }
            default:
        }
//        db.execSQL("drop table if exists Book");
//        db.execSQL("drop table if exists Category");
//        onCreate(db);
    }
}
