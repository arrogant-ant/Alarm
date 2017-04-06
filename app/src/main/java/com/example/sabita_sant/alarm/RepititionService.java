package com.example.sabita_sant.alarm;

import android.app.AlarmManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Sabita_Sant on 30/11/16.
 */

public class RepititionService extends Service {
    Calendar calendar=Calendar.getInstance();
    Timer t;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    AddAlarm ins;
/*    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ins=AddAlarm.instance();
        t=new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                int day= calendar.get(Calendar.DAY_OF_WEEK);
                if(day==Calendar.MONDAY && ins.sharedPreferences.getBoolean("Mon",false))
                    ins.alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),7*24*60*60*1000,ins.pendingIntent);
                else if(day==Calendar.TUESDAY && ins.sharedPreferences.getBoolean("Tue",false))
                    ins.alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),7*24*60*60*1000,ins.pendingIntent);
                else if(day==Calendar.WEDNESDAY && ins.sharedPreferences.getBoolean("Wed",false))
                    ins.alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),7*24*60*60*1000,ins.pendingIntent);
                else if(day==Calendar.THURSDAY && ins.sharedPreferences.getBoolean("Thu",false))
                    ins.alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),7*24*60*60*1000,ins.pendingIntent);
                else if(day==Calendar.FRIDAY && ins.sharedPreferences.getBoolean("Fri",false))
                    ins.alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),7*24*60*60*1000,ins.pendingIntent);
                else if(day==Calendar.SATURDAY && ins.sharedPreferences.getBoolean("Sat",false))
                    ins.alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),7*24*60*60*1000,ins.pendingIntent);
                else
                    ins.alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),7*24*60*60*1000,ins.pendingIntent);
            }
        },0,24*60*60*1000);


        return START_NOT_STICKY;
    }*/
}
