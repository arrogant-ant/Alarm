package com.example.sabita_sant.alarm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

public class AlarmReceiver
        extends BroadcastReceiver {
    static AlarmReceiver ins;
    Context c;

    NotificationManager notificationManager;

    public AlarmReceiver() {
        Log.e("test", "constructor receiver");
        ins = this;
    }

    private void showNotification(Context context, String alert, String title, String msg) {
        Intent arith = new Intent(context, ArithTest.class);
        PendingIntent pending_back = PendingIntent.getActivity(context, 0, arith, PendingIntent.FLAG_UPDATE_CURRENT);
        android.support.v7.app.NotificationCompat.Builder notiBuilder = (android.support.v7.app.NotificationCompat.Builder) new NotificationCompat.Builder(context)
                .setTicker(alert)
                .setContentTitle(title)
                .setContentText(msg)
                .setSmallIcon(R.drawable.check_box)
                .addAction(R.drawable.snooze,"Snooze",pending_back);//change pending_back to snooze alarm
        notiBuilder.setContentIntent(pending_back);
        notiBuilder.setAutoCancel(true);
        notiBuilder.setDefaults(NotificationCompat.DEFAULT_SOUND);
        notiBuilder.setContentIntent(pending_back
        );
        notiBuilder.setAutoCancel(true);
        notiBuilder.setDefaults(1);
        this.notificationManager.notify(1, notiBuilder.build());
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        c=context;
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        showNotification(context,"Alarm", "IT'S " + AddAlarm.AlarmText, "tap to stop");
         Intent i=new Intent();
        i.setClassName("com.example.sabita_sant.alarm","com.example.sabita_sant.alarm.ArithTest");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);



    }
}



/* Location:           C:\Users\Sabita_Sant\Desktop\Alarm\dex2jar-0.0.9.15\classes_dex2jar.jar

 * Qualified Name:     com.example.sabita_sant.alarm.AlarmReceiver

 * JD-Core Version:    0.7.0.1

 */