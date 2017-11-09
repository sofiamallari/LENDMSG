package com.example.android.lend;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SOFIA on 11/9/2017.
 */

public class DBhelper extends SQLiteOpenHelper {
    public static final int DB_VERSION=1;
    public static SQLiteDatabase db;

    public DBhelper(Context con){
        super(con, "messages_db", null , DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //need once create method
        if(db == null) {
            String studentSql = "create table msg_db(message)";
            db.execSQL(studentSql);
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion) {
        //everytime change
        onCreate(db);
    }

}
