package com.calendar.b150416.calendar.Notify;

import android.os.Binder;

import com.calendar.b150416.calendar.EventSolve.Event;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class NotifyBinder extends Binder {
    HashMap<Event,Timer> timers;
    NotifyManager nm;
    public NotifyBinder(){
        timers=new HashMap<>();
        nm=new NotifyManagerAdapter();
    }
    public void addEvent(Event e){
        if(!timers.containsKey(e)){
            Calendar cal=Calendar.getInstance();
            cal.set(Calendar.YEAR,e.year);
            cal.set(Calendar.MONTH,e.month);
            cal.set(Calendar.DAY_OF_MONTH,e.day);
            cal.set(Calendar.HOUR_OF_DAY,e.hour);
            cal.set(Calendar.MINUTE,e.minute);
            cal.set(Calendar.SECOND,0);
            Date date=cal.getTime();
            Date now=new Date();
            if(now.compareTo(date)<0){
                final Event finale=e;
                Timer timer=new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        nm.notifyEvent(finale);
                    }
                }, date);
                timers.put(e,timer);
            }
        }
    }
    public void removeEvent(Event e){
        if(timers.containsKey(e)){
            Timer t=timers.get(e);
            t.cancel();
            timers.remove(e);
        }
    }
}
