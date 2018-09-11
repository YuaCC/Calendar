package com.calendar.b150416.calendar.Notify;

import android.util.Log;

import com.calendar.b150416.calendar.EventSolve.Event;

public class NotifyManagerAdapter implements NotifyManager {
    NotifyManagerAdapter(){
    }
    public void notifyEvent(Event e){
        Log.d("NotifyManagerAdapter",e.description);
    }
}
