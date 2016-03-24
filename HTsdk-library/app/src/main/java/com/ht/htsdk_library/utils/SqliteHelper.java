package com.ht.htsdk_library.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ht.htsdk_library.bean.UserInfo;

//负责数据库的创建以及版本更新的帮助类
public class SqliteHelper extends SQLiteOpenHelper {

    public static final String TB_NAME = "userInfo";

    public SqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //创建表
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                        TB_NAME + "(" +
                        UserInfo.ID + " integer primary key autoincrement," +
                        UserInfo.USERID + " varchar(50)," +
                        UserInfo.NAME + " varchar(50)," +
                        UserInfo.TOKEN + " varchar(100)," +
                        UserInfo.DIVICE + " boolean," +
                        UserInfo.FACEBOOKINFO + " boolean," +
                        UserInfo.EMAIL + " boolean" +
                        ")"
        );
        Log.e("Database", "onCreate");
    }

    //更新表
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_NAME);
        onCreate(db);
        Log.e("Database", "onUpgrade");
    }

    //更新列
    public void updateColumn(SQLiteDatabase db, String oldColumn, String newColumn, String typeColumn) {
        try {
            db.execSQL("ALTER TABLE " +
                            TB_NAME + " CHANGE " +
                            oldColumn + " " + newColumn +
                            " " + typeColumn
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
