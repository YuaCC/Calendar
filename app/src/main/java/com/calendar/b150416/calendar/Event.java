package com.calendar.b150416.calendar;

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
}
