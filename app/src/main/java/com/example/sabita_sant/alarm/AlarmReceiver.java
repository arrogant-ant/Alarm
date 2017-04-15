package com.example.sabita_sant.alarm;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.NotificationCompat;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by Sabita_Sant on 09/04/17.
 */

public class AlarmReceiver extends BroadcastReceiver {
    NotificationManager notificationManager;
    MediaPlayer mediaPlayer;
    Context c;
    static AlarmReceiver ins;
    AlarmReceiver()
    {
        ins= this;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        c=context;
        notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        showNotification(context,"Alarm", "IT'S " + AddAlarm.AlarmText, "tap to snooze");

        mediaPlayer = MediaPlayer.create(context, R.raw.tone1);
        mediaPlayer.start();
    }

    private void showNotification(Context context,String alert,String title, String msg)
    {
        PendingIntent pending_back= PendingIntent.getActivity(context,0,new Intent(context,StopAlarm.class),PendingIntent.FLAG_UPDATE_CURRENT);
        //configure Notifi
        NotificationCompat.Builder notiBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
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
