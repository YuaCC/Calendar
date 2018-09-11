package com.calendar.b150416.calendar;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class EventManagerAdapter implements EventManager {
    NotifyManager nm=null;
    LinkedList<Event> datas;
    HashMap<Event,Timer> timers;
    ArrayAdapter<String> arrayAdapter;
    MyDataBaseHelper dataBaseHelper;
    public EventManagerAdapter(ArrayAdapter<String> arrayAdapter,Context context){
        nm=new NotifyManagerAdapter(context);
        datas=new LinkedList<Event> ();
        timers=new HashMap<Event,Timer>();
        this.arrayAdapter=arrayAdapter;
        dataBaseHelper=new MyDataBaseHelper(context);
        load();

    }

    public Event inputNewEvent(){
        return null;
    }
    public void addEvent(Event e){
        int i=0;
        for(i=0;i<datas.size();++i)
            if(datas.get(i).getTime()>e.getTime())
            {
                datas.add(i,e);
                break;
            }
        if(i>=datas.size())
            datas.add(e);

        arrayAdapter.insert(String.format("%d/%d/%d %d:%d %s",e.year,e.month+1,e.day,e.hour,e.minute,e.description),i);
        arrayAdapter.notifyDataSetChanged();

        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.YEAR,e.year);
        calendar.set(Calendar.MONTH,e.month);
        calendar.set(Calendar.HOUR_OF_DAY,e.hour);
        calendar.set(Calendar.MINUTE,e.minute);
        calendar.set(Calendar.SECOND,0);
        Date date=calendar.getTime();
        Timer timer=new Timer();
        final Event finale=e;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Looper.prepare();
                nm.notifyEvent(0,finale);
            }
        }, date);
        timers.put(e,timer);

    }

    public boolean removeEvent(Event e){
        if(datas.contains(e))
        {
            Timer timer=timers.get(e);
            timer.cancel();
            timers.remove(e);
            arrayAdapter.remove(arrayAdapter.getItem(datas.indexOf(e)));
            arrayAdapter.notifyDataSetChanged();
            datas.remove(e);
            return true;
        }
        return false;
    }


    public boolean changeEvent(Event oldEvent,Event newEnvent){
       if(removeEvent(oldEvent)){
           addEvent(newEnvent);
           return true;
       }
        return false;
    }

    public LinkedList<Event> getEventOfDay(int year, int month, int day){
        LinkedList<Event> ret=new LinkedList<Event>();
        for(Event e:datas){
            if(e.year==year&&e.month==month&&e.day==day)
                ret.add(e);
        }
        return ret;
    }
    public  Event getEventByPosition(int position){
            return datas.get(position);
    }
    public void save(){
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        db.delete("EVENT",null,null);
        ContentValues cv = new ContentValues();
        for(Event e:datas){
            cv.put("YEAR",e.year);
            cv.put("MONTH",e.month);
            cv.put("DAY",e.day);
            cv.put("HOUR",e.hour);
            cv.put("MINUTE",e.minute);
            cv.put("DESCRIPTION",e.description);
            db.insert("EVENT", null,cv);
        }
        db.close();
    }
    public void load(){
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        Cursor cs = db.query("EVENT", null, null, null, null, null, null);
        Event e = null;
        while (cs.moveToNext()) {
            int year=cs.getInt(0);
            int month=cs.getInt(1);
            int day=cs.getInt(2);
            int hour=cs.getInt(3);
            int minute=cs.getInt(4);
            String description=cs.getString(5);
            addEvent(new Event(year,month,day,hour,minute,description));
        }
        cs.close();
        db.close();
    }
}
