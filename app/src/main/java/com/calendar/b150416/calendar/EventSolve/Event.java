package com.calendar.b150416.calendar.EventSolve;

public class Event {
    public int year;
    public int month;
    public int day;
    public int hour;
    public int minute;
    public String description;
    public  Event(int year,int month,int day,int hour,int minute,String description){
        this.year=year;
        this.month=month;
        this.day=day;
        this.hour=hour;
        this.minute=minute;
        this.description=description;
    }
    public long getTime(){
        return ( ((long)year*365+day)*24+hour)*60+minute;
    }
    @Override
    public boolean equals(Object o){
        Event other=(Event)o;
        return other.year==year&&other.month==month&&other.day==day&&other.hour==hour&&other.minute==minute;
    }
}
