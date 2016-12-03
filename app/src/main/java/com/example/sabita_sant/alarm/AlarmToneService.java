package com.example.sabita_sant.alarm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

/**
 * Created by Sabita_Sant on 14/10/16.
 */
public class AlarmToneService extends Service{
    private NotificationManager notificationManager;

   static AlarmToneService ServiceInst;
   MediaPlayer mediaPlayer;
    AlarmToneService()
    {
        ServiceInst=this;
         //mediaPlayer = MediaPlayer.create(this, R.raw.tone1);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //Log.e("state: "," "+intent.getExtras().getBoolean("state"));


        Log.e("service", "Invoked");
        notificationManager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
        showNotification("Alarm", "IT'S " + AddAlarm.AlarmText, "tap to snooze");

        mediaPlayer = MediaPlayer.create(this, R.raw.tone1);
        mediaPlayer.start();


        return START_NOT_STICKY;


    }
    private void showNotification(String alert,String title, String msg)
    {
        PendingIntent pending_back= PendingIntent.getActivity(this,0,new Intent(this,StopAlarm.class),PendingIntent.FLAG_UPDATE_CURRENT);
        //configure Notifi
        NotificationCompat.Builder notiBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setTicker(alert)
                .setContentTitle(title)
                .setContentText(msg)
                .setSmallIcon(R.drawable.check_box)
                .addAction(R.drawable.snooze,"Snooze",pending_back);//change pending_back to snooze alarm
        notiBuilder.setContentIntent(pending_back);
        notiBuilder.setAutoCancel(true);
        notiBuilder.setDefaults(NotificationCompat.DEFAULT_SOUND);

        notificationManager.notify(1,notiBuilder.build());
    }
}
