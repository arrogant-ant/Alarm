package com.example.sabita_sant.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Sabita_Sant on 12/12/16.
 */

public class AlarmService extends Service {

    AlarmManager alarmManager;
    AddAlarm addAlarm;
    PendingIntent pendingIntent;
    Intent tone_intent;
    private static AlarmService ins;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



    public static AlarmService instance()
    {
        return ins;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        ins=this;
        addAlarm = AddAlarm.instance();
        alarmManager= (AlarmManager)getSystemService(ALARM_SERVICE);
        tone_intent = new Intent(this,AlarmToneService.class);
        pendingIntent= PendingIntent.getService(this,0, tone_intent,0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, addAlarm.calendar.getTimeInMillis(), pendingIntent);
        return START_NOT_STICKY;
    }
}
