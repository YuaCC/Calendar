package com.calendar.b150416.calendar.Notify;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

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
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public void onCreate() {
        super.onCreate();
        notifyBinder=new NotifyBinder();
        Log.d("NotifyService","create");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("NotifyService","destroy");
    }


}
