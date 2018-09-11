package com.calendar.b150416.calendar.Notify;

import com.calendar.b150416.calendar.EventSolve.Event;

public class NotifyManagerAdapter implements NotifyManager {
    NotifyManagerAdapter(){
    }
    public void notifyEvent(Event e){
        System.out.println(e.description);
    }
}
