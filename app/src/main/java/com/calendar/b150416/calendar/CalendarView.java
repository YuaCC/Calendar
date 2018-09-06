package com.calendar.b150416.calendar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Calendar;
import java.util.Date;
import java.util.jar.Attributes;

public class CalendarView extends View {
    public Date nowDate;
    public Date displayDate;
    private EventManager ema=null;
    final private double headerHightPercent=0.1;

    public CalendarView(Context context, AttributeSet attrs){
        super(context,attrs);
        displayDate=nowDate=new Date();
    }
    public void setEventManager(EventManager ema){
        this.ema=ema;
    }
    @Override
    public void onDraw(Canvas canvas){
        Calendar cal=Calendar.getInstance();
        cal.setTime(displayDate);
        cal.set(Calendar.DAY_OF_MONTH,1);


        int firstWeekDay=cal.get(Calendar.DAY_OF_WEEK);
        int dayCnt=getDaysOfMonth(cal);
        int rowCnt=(dayCnt+firstWeekDay+5)/7+1;
        int colCnt=7;
        for (int i=0;i<6;++i)
            for(int j=0;j<7;++j){
            drawDay(canvas,i,j,6,7,cal,null);
            cal.set(Calendar.DAY_OF_MONTH,cal.get(Calendar.DAY_OF_MONTH)+1);
            }
    }
    private void drawDay(Canvas canvas,int row,int col,int rowCnt,int colCnt,Calendar cal,Paint paint){
        float width=canvas.getWidth();
        float height=canvas.getHeight();
        float l=(float)(col*(width/colCnt));
        float r=(float)(l+width/colCnt);
        float u=(float)(headerHightPercent*height+((1-headerHightPercent)*height)/rowCnt*row);
        float d=(float)(u+((1-headerHightPercent)*height)/rowCnt);
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setTextSize(50);
        String day=cal.get(Calendar.DAY_OF_MONTH)+"";
        canvas.drawText(day,l,u,paint);

    }
    private int getDaysOfMonth(Calendar cal){
        int month=cal.get(Calendar.MONTH);
        int year=cal.get(Calendar.YEAR);
        switch (month){
            case Calendar.JANUARY:
            case Calendar.MARCH:
            case Calendar.MAY:
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.OCTOBER:
            case Calendar.DECEMBER:
                return 31;
            case Calendar.APRIL:
            case Calendar.JUNE:
            case Calendar.SEPTEMBER:
            case Calendar.NOVEMBER:
                return 30;
            case Calendar.FEBRUARY:
                if(year%400==0)
                    return 29;
                else if(year%100==0)
                    return 28;
                else if(year%4==0)
                    return 29;
                else
                    return 28;
            default:
                return -1;
        }
    }
}
