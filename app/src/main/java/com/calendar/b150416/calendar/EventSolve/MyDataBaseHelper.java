package com.calendar.b150416.calendar.EventSolve;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class MyDataBaseHelper extends SQLiteOpenHelper {

    public  MyDataBaseHelper(Context context){
        super(context,"user.db" ,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
