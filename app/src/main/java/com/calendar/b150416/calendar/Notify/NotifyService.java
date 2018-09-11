package com.calendar.b150416.calendar.Notify;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.calendar.b150416.calendar.Notify.NotifyBinder;

public class NotifyService extends Service {
    NotifyBinder notifyBinder;
    public NotifyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return notifyBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        notifyBinder=new NotifyBinder();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
