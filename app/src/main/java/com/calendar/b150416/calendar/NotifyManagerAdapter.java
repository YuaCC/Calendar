package com.calendar.b150416.calendar;

import android.app.Notification;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class NotifyManagerAdapter implements NotifyManager {
    Context mcontext;
    public NotifyManagerAdapter(Context context){
        mcontext=context;
    }
    public void notifyEvent(int id,Event e){

        System.out.println("搞事情");
    }
}
