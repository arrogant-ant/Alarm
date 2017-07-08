package com.example.sabita_sant.alarm;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AddAlarm
        extends AppCompatActivity {
    static long ALARM_TIME;
    static StringBuilder AlarmText;
    private static AddAlarm inst;
    static boolean repeat = false;
    static int snooze;
    Calendar calendar = Calendar.getInstance();
    private String format;
    Switch repeat_sw;
    EditText snooze_et;
    private TextView time,title;
    private TimePicker timePicker;

    public static AddAlarm instance() {
        return inst;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_alarm);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        time = (TextView) findViewById(R.id.showtime);
        title= (TextView) findViewById(R.id.title);
        alarmTime(-330*60000);
        this.repeat_sw = ((Switch) findViewById(R.id.repeat));
        this.snooze_et = ((EditText) findViewById(R.id.snooze));
        Typeface heading=Typeface.createFromAsset(getAssets(),"fonts/Raleway-SemiBold.ttf");
        title.setTypeface(heading);
    }


    //converts 24h to 12h
    public void alarmTime(long timeInMilis) {
        timeInMilis/=60000;
        timeInMilis+=330;
        int min=(int)timeInMilis%60;
        int hour=(int)(timeInMilis/60)%24;
        if (hour == 0)
            format = "A.M.";
        else if (hour == 12)
            format = "P.M.";
        else if (hour > 12) {
            format = "P.M.";
            hour -= 12;
        } else
            format = "A.M.";
        if (min < 10)
            AlarmText = new StringBuilder("").append(hour).append(":0").append(min).append(" ").append(format);
        else
            AlarmText = new StringBuilder("").append(hour).append(":").append(min).append(" ").append(format);
        time.setText("Alarm Set at " + AlarmText);


    }


    public void onStart() {
        super.onStart();
        inst = this;
    }

    public void setAlarm(View paramView) {

        calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour().intValue());
        calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute().intValue());
        ALARM_TIME = this.calendar.getTimeInMillis();
        snooze = Integer.valueOf(this.snooze_et.getText().toString());
        if (this.repeat_sw.isChecked()) {
            repeat = true;
        }
        else
            repeat=false;
        alarmTime(calendar.getTimeInMillis());
        Intent intent = new Intent(AddAlarm.this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(AddAlarm.this, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        setNotification(AddAlarm.this,calendar.getTimeInMillis());
    }




    public void info(View view) {
        startActivity(new Intent(AddAlarm.this, Info.class));
    }

    public void analysis(View view) {
        startActivity(new Intent(AddAlarm.this, MainActivity.class));
    }

    public static void setNotification(Context ctx, long timeInMillis)
    {
        Notification.Builder builder =new Notification.Builder(ctx)
                .setAutoCancel(false)
                .setContentTitle("Alarm")
                .setSmallIcon(R.drawable.icon)
                .setContentText("Next Alarm Pending at "+AlarmText)
                .setPriority(Notification.PRIORITY_MAX)
                .setOngoing(true);

        NotificationManager manager=(NotificationManager)ctx.getSystemService(NOTIFICATION_SERVICE);
        manager.notify(2,builder.build());
    }
}

