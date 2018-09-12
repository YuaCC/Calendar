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


    public  Event getEventByPosition(int position){
            return datas.get(position);
    }

    //把datas中的数据保存到数据库中
    public void save(){
        //获取数据库
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        //删除原来的表EVENT
        db.delete("EVENT",null,null);
        //读取数据并插入到EVENT中
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
        //关闭数据库
        db.close();
    }

    //把数据库中的数据保存到datas中
    public void load(){

        if(datas==null)
            datas=new LinkedList<Event>();
        //清楚datas中的旧数据
        while(datas.size()>0)
            removeEvent(datas.get(0));

        //获取数据库
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        //读取数据
        Cursor cs = db.query("EVENT", null, null, null, null, null, null);
        while (cs.moveToNext()) {
            int year=cs.getInt(0);
            int month=cs.getInt(1);
            int day=cs.getInt(2);
            int hour=cs.getInt(3);
            int minute=cs.getInt(4);
            String description=cs.getString(5);
            addEvent(new Event(year,month,day,hour,minute,description));
        }
        //关闭
        cs.close();
        db.close();
    }

}
