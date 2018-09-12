package com.calendar.b150416.calendar.EventSolve;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDataBaseHelper extends SQLiteOpenHelper {
    //建表语句
    public static final String CREATE_TABLE="CREATE TABLE if not exists `EVENT` (\n" +
            "`YEAR`  integer  NOT NULL ,\n" +
            "`MONTH`  integer  NOT NULL ,\n" +
            "`DAY`  integer  NOT NULL ,\n" +
            "`HOUR`  integer  NOT NULL ,\n" +
            "`MINUTE`  integer  NOT NULL ,\n" +
            "`DESCRIPTION`  text NOT NULL ,\n" +
            "`ID`  integer primary key autoincrement \n" +
            ")\n" ;

    private Context mcontext;
    public  MyDataBaseHelper(Context context){
        super(context,"user.db" ,null,1);
        mcontext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        Toast.makeText(mcontext,"create database succeeded",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists `EVENT`");
        onCreate(db);
    }
}
