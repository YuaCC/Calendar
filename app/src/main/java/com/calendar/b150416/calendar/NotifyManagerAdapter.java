package com.calendar.b150416.calendar;

import android.util.Log;

public class NotifyManagerAdapter implements NotifyManager {
    public void notifyEvent(int id,Event e){
        /**
         * 在这里写上代码使得手机推送一条消息提醒用户事件时间到了
         */
        Log.d("NofifyManagerAdapter",String.format("Notify Event ,id=%d,deccription=%s",id,e.description));
    }
}
