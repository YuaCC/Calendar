package com.calendar.b150416.calendar;

import java.util.ArrayList;

public class EventManagerAdapter implements EventManager {
    NotifyManager nm=null;
    public EventManagerAdapter(){
        nm=new NotifyManagerAdapter();
    }

    public int addEvent(Event e){
        return 0;
    }

    public boolean removeEvend(int id){
        return true;
    }


    public boolean changeEvent(int id,Event newEnvent){
        return true;
    }

    public Event getEvent(int id){
        return new Event(2018,9,3,12,0,"EventManagerAdapter");
    }
    public ArrayList<Event> getEventOfDay(int year, int month, int day){
        return new ArrayList<Event>();
    }
}
