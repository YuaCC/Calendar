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
    LinkedList<Event> datas;
    ArrayAdapter<String> arrayAdapter;
    MyDataBaseHelper dataBaseHelper;
   public  NotifyBinder notifyBinder=null;
    public int size(){
        return datas.size();
    }
    public EventManagerAdapter(ArrayAdapter<String> arrayAdapter,Context context){
        datas=new LinkedList<Event> ();
        this.arrayAdapter=arrayAdapter;
        dataBaseHelper=new MyDataBaseHelper(context);
        load();
    }

    public void addEvent(Event e){
        if(!datas.contains(e)){
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

            if(notifyBinder!=null)
                notifyBinder.addEvent(e);
        }


    }

    public boolean removeEvent(Event e){
        if(datas.contains(e))
        {
            if(notifyBinder!=null)
                notifyBinder.removeEvent(e);
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
        if(datas==null)
            datas=new LinkedList<Event>();
        while(datas.size()>0)
            removeEvent(datas.get(0));

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
