package com.calendar.b150416.calendar.Notify;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.calendar.b150416.calendar.Notify.NotifyBinder;

public class NotifyService extends Service {
    NotifyBinder notifyBinder;
    public NotifyService() {
    }
//实现Service中的函数onBind
    @Override
    public IBinder onBind(Intent intent) {
        return notifyBinder;
    }


    //初始化
    @Override
    public void onCreate() {
        super.onCreate();
        notifyBinder=new NotifyBinder();
    }



}
