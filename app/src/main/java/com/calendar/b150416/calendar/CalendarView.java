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
    public Calendar nowDate;
    public Calendar displayDate;
    private EventManager ema=null;
    final private double headerHightPercent=0.1;
    final private int headMaign=50;

    public CalendarView(Context context, AttributeSet attrs){
        super(context,attrs);
        nowDate=Calendar.getInstance();
        nowDate.setTime(new Date());

        displayDate=Calendar.getInstance();
        displayDate.setTime(new Date());
    }
    public void setYear(int year){
        displayDate.set(Calendar.DAY_OF_MONTH,1);
        displayDate.set(Calendar.YEAR,year);
    }
    public void setMonth(int month){
        displayDate.set(Calendar.DAY_OF_MONTH,1);
        displayDate.set(Calendar.MONTH,month);
    }
    public void display(int year,int month){
        displayDate.set(Calendar.DAY_OF_MONTH,1);
        displayDate.set(Calendar.MONTH,month);
        displayDate.set(Calendar.YEAR,year);
    }
    public void setEventManager(EventManager ema){
        this.ema=ema;
    }
    @Override
    public void onDraw(Canvas canvas){
        displayDate.set(Calendar.DAY_OF_MONTH,1);


        int firstWeekDay=displayDate.get(Calendar.DAY_OF_WEEK);
        int dayCnt=getDaysOfMonth(displayDate);
        Paint paint=new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPaint(paint);

        drawHeader(canvas);

        int index=firstWeekDay-1;
        int rowCnt=(dayCnt+firstWeekDay+5)/7+1;
        int colCnt=7;

        for (int i=0;i<dayCnt;++i,++index)
            drawDay(canvas,index/7,index%7,rowCnt,colCnt,displayDate.get(Calendar.YEAR),displayDate.get(Calendar.MONTH),i+1);

    }
    private void drawHeader(Canvas canvas){
        double width=canvas.getWidth();
        Paint paint=new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(50);
        String []headers={"周日","周一","周二","周三","周四","周五","周六"};
        for(int i=0;i<7;++i){
            double beg=(width/7)*i;
            drawTextLine(headers[i],beg,beg+width/7,0,paint,canvas);
        }

    }
    private void drawDay(Canvas canvas,int row,int col,int rowCnt,int colCnt,int year,int month,int day){
        float width=canvas.getWidth();
        float height=canvas.getHeight();
        float l=(float)(col*(width/colCnt));
        float r=(float)(l+width/colCnt);
        float u=(float)(headerHightPercent*height+((1-headerHightPercent)*height)/rowCnt*row);
        float d=u+height/rowCnt;
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setTextSize(50);
        String daystr=String.valueOf(day);
        drawTextLine(daystr,l,r,u,paint,canvas);

        if(year==nowDate.get(Calendar.YEAR)&&month==nowDate.get(Calendar.MONTH)&&day==nowDate.get(Calendar.DAY_OF_MONTH)){
            paint.setColor(Color.GREEN);
            paint.setStrokeWidth(5);
            canvas.drawLine(l,u,l,d,paint);
            canvas.drawLine(l,d,r,d,paint);
            canvas.drawLine(r,d,r,u,paint);
            canvas.drawLine(r,u,l,u,paint);
        }


    }
    private void drawTextLine(String str,double l,double r,double u,Paint paint,Canvas canvas){
        str=adaptStringWidth(str,paint,(int)(r-l));
        double width=getStringWidth(str,paint);
        double offset=((r-l)-width)/2;
        canvas.drawText(str,(float)(l+offset),(float)(u+20+headMaign),paint);
    }
    private  int getStringWidth(String str,Paint paint) {
        if (str != null && str.length() > 0) {
            int len = str.length();
            float[] widths = new float[len];
            paint.getTextWidths(str, widths);
            double length = 0;
            for (float cw : widths) length += cw;
            return (int) length;
        } else return 0;
    }
    private String adaptStringWidth(String str,Paint paint,int maxWidth){
        if(str!=null &&str.length()>0){
            int width=getStringWidth(str,paint);
            if(width<=maxWidth)
                return str;
            else{
                StringBuffer sb=new StringBuffer();
                String tail="..";
                int tailWidth=getStringWidth(tail,paint);
                int len = str.length();
                float[] widths = new float[len];
                char[] arr = str.toCharArray();
                paint.getTextWidths(str, widths);
                double length = 0;
                for(int i=0;i<arr.length;++i){
                    if(length+widths[i]+tailWidth<=maxWidth)
                    {
                        length+=widths[i];
                        sb.append(arr[i]);
                    }
                    else
                        break;
                }
                sb.append(tail);
                return sb.toString();
            }
        }
        else
            return str;
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
