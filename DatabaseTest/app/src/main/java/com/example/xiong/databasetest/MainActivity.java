package com.example.xiong.databasetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 3);
        Button btn_createDb = (Button) findViewById(R.id.btn_createDatabase);
        btn_createDb.setOnClickListener(this);
        Button btn_addData = (Button) findViewById(R.id.btn_addData);
        btn_addData.setOnClickListener(this);
        Button btn_updateData = (Button) findViewById(R.id.btn_updateData);
        btn_updateData.setOnClickListener(this);
        Button btn_deleteData = (Button) findViewById(R.id.btn_deleteData);
        btn_deleteData.setOnClickListener(this);
        Button btn_queryData = (Button) findViewById(R.id.btn_queryData);
        btn_queryData.setOnClickListener(this);
        Button btn_replaceData = (Button) findViewById(R.id.btn_replaceData);
        btn_replaceData.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_createDatabase:{
                /*
                 getReadableDatabase()和getWritableDatabase()。这两个方法都可以创建或打开一个现有的数据库（如果数据库已存在则直接打开，否则创建一个新的数据库），
                 并返回一个可对数据库进行读写操作的对象，当数据库不可写入的时候（如磁盘空间已满）getReadableDatabase()方法返回的对象将以只读的方式去打开数据库，
                 而 getWritableDatabase()方法则将出现异常。
                 */
                dbHelper.getWritableDatabase();
                break;
            }
            case R.id.btn_addData:{
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                //开始组装第一条数据
                contentValues.put("name", "The Da Vinci Code");
                contentValues.put("author", "Dan Brown");
                contentValues.put("pages", 454);
                contentValues.put("price", 16.96);
                db.insert("Book", null, contentValues);//插入第一条数据
                contentValues.clear();
                contentValues.put("name", "The Lost Symbol");
                contentValues.put("author", "Dan Brown");
                contentValues.put("pages", 510);
                contentValues.put("price", 19.95);
                db.insert("Book", null, contentValues);//插入第二条数据
                contentValues.clear();
                contentValues.put("category_name", "Science");
                contentValues.put("category_code", "001");
                db.insert("Category", null, contentValues);//插入第三条数据
                break;
            }
            case R.id.btn_updateData:{
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("price", 10.96);
                /*
                更新数据，第三、第四个参数用于去约束更新某一行或某几行中的数据，不指定的话默认就是更新所有行，
                ?是一个占位符，可以通过第四个参数提供的一个字符串数组为第三个参数中的每个占位符指定相应的内容。
                 */
                db.update("Book", contentValues, "name = ?", new String[]{"The DaVinci Code"});
                break;
            }
            case R.id.btn_deleteData:{
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                //第二、第三个参数又是用于去约束删除某一行或某几行的数据，不指定的话默认就是删除所有行。
                db.delete("Book", "pages > ?", new String[]{"500"});
                break;
            }
            case R.id.btn_queryData:{
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                /*
                query()第二个参数用于指定去查询哪几列，如果不指定则默认查询所有列。第三、第四个参数用于去约束查询某一行或某几行的数据，
                不指定则默认是查询所有行的数据。第五个参数用于指定需要去 group by 的列，不指定则表示不对查询结果进行 group by 操作。
                第六个参数用于对 group by 之后的数据进行进一步的过滤，不指定则表示不进行过滤。第七个参数用于指定查询结果的排序方式，
                不指定则表示使用默认的排序方式。
                 */
                Cursor cursor = db.query("Book", null, null, null, null, null, null);
                if (cursor.moveToFirst()){
                    do {
                        /*
                        遍历Cursor对象，取出数据并打印，通过Cursor的getColumnIndex()方法获取到某一列在表中对应的位置索引，
                        然后将这个索引传入到相应的取值方法中，可得到从数据库中读取到的数据。
                         */
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d("MainActivity", "book name is " + name);
                        Log.d("MainActivity", "book author is " + author);
                        Log.d("MainActivity", "book pages is " + pages);
                        Log.d("MainActivity", "book price is " + price);
                    }while (cursor.moveToNext());
                }
                cursor.close();
                break;
            }
            case R.id.btn_replaceData:{
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.beginTransaction();//开启事务
                db.delete("Book", null, null);
                ContentValues values = new ContentValues();
                values.put("name", "Computer Code");
                values.put("author", "Somebody");
                values.put("pages", "101");
                values.put("price", "20.6");
                db.insert("Book", null, values);
                db.setTransactionSuccessful();//事务已执行成功
                Toast.makeText(this, "替换成功", Toast.LENGTH_SHORT).show();
                db.endTransaction();
                break;
            }
        }
    }
}
