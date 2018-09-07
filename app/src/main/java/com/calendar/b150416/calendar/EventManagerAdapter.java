package com.calendar.b150416.calendar;

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
    HashMap<Integer,LinkedList<Event>> datas;
    HashMap<Event,Timer> timers;
    public EventManagerAdapter(){
        nm=new NotifyManagerAdapter();
        datas=new HashMap<Integer,LinkedList<Event> >();
        timers=new HashMap<Event,Timer>();
    }

    public void addEvent(Event e){
        Integer key=Integer.valueOf(e.year*10000+e.month*100+e.day);
        if(!datas.containsKey(key))
            datas.put(key,new LinkedList<Event>());
        LinkedList<Event> list=datas.get(key);
        list.add(e);

        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,e.hour);
        calendar.set(Calendar.MINUTE,e.minute);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.YEAR,e.year);
        Date date=calendar.getTime();
        Timer timer=new Timer();
        final Event finale=e;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                nm.notifyEvent(0,finale);
            }
        }, date);
        timers.put(e,timer);
    }

    public boolean removeEvent(Event e){
        Integer key=Integer.valueOf(e.year*10000+e.month*100+e.day);

        if(datas.containsKey(key))
        {
            LinkedList<Event> set=datas.get(key);
            if(set.contains(e)){
                set.remove(e);
                Timer timer=timers.get(e);
                timer.cancel();
                timers.remove(e);
                return true;
            }
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

        Integer key=Integer.valueOf(year*10000+month*100+day);
        if(datas.containsKey(key))
            return datas.get(key);
        return null;
    }
}
