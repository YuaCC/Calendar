package com.calendar.b150416.calendar.EventSolve;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.widget.ArrayAdapter;

import com.calendar.b150416.calendar.Notify.NotifyBinder;

import java.util.LinkedList;

public class EventManagerAdapter implements EventManager{
    public int size(){
        return 0;
    }
    public EventManagerAdapter(ArrayAdapter<String> arrayAdapter,Context context){
    }

    public void addEvent(Event e){
        

    }

    public boolean removeEvent(Event e){
      
    }


    public boolean changeEvent(Event oldEvent,Event newEnvent){
        return false;
    }

    public LinkedList<Event> getEventOfDay(int year, int month, int day){
        return ret;
    }
    public  Event getEventByPosition(int position){
       return null;
    }


    public void save(){

    }

    public void load(){

    }

}
