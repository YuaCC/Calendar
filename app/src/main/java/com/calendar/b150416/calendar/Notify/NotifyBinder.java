package com.calendar.b150416.calendar.Notify;

import android.os.Binder;

import com.calendar.b150416.calendar.EventSolve.Event;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class NotifyBinder extends Binder {
    //存储事项的定时器
    HashMap<Event,Timer> timers;
    //当定时器时间到的时候调用nm.notifyEvent来通知用户
    NotifyManager nm;

    public NotifyBinder(){
        //初始化
        timers=new HashMap<>();
        nm=new NotifyManagerAdapter();
    }
    //添加事项
    public void addEvent(Event e){
        //如果timers已经包含了该事项的定时器，则无视
        if(!timers.containsKey(e)){
            //获取事项的时间
            Calendar cal=Calendar.getInstance();
            cal.set(Calendar.YEAR,e.year);
            cal.set(Calendar.MONTH,e.month);
            cal.set(Calendar.DAY_OF_MONTH,e.day);
            cal.set(Calendar.HOUR_OF_DAY,e.hour);
            cal.set(Calendar.MINUTE,e.minute);
            cal.set(Calendar.SECOND,0);
            Date date=cal.getTime();
            Date now=new Date();
            //如果该事项的时间已经过去了，则无视
            if(now.compareTo(date)<0){
                //设置定时器
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
    //移除定时器
    public void removeEvent(Event e){
        //如果timers不包含该定时器
        if(timers.containsKey(e)){
            timers.remove(e);
        }
    }
}
