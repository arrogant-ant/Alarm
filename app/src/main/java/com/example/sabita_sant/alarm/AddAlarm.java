package com.example.sabita_sant.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
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
    String alarm_time;
    Calendar calendar = Calendar.getInstance();
    private String format;
    private int hour;
    private int min;
    Switch repeat_sw;
    EditText snooze_et;
    private TextView time;
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
        alarmTime(hour, min);
        this.repeat_sw = ((Switch) findViewById(R.id.repeat));
        this.snooze_et = ((EditText) findViewById(R.id.snooze));
    }


    //converts 24h to 12h
    public void alarmTime(int hour, int min) {
        Toast.makeText(this, "inside alarm Time", Toast.LENGTH_LONG).show();
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
        this.hour = this.timePicker.getCurrentHour().intValue();
        this.min = this.timePicker.getCurrentMinute().intValue();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, min);
        this.alarm_time = (this.hour + ":" + this.min);
        ALARM_TIME = this.calendar.getTimeInMillis();
        snooze = Integer.valueOf(this.snooze_et.getText().toString());
        if (this.repeat_sw.isChecked()) {
            repeat = true;
        }
        alarmTime(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
        Intent intent = new Intent(AddAlarm.this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(AddAlarm.this, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }


    void stopAlarm() {
        startActivity(new Intent(this, ArithTest.class));
    }

    public void info(View view) {
        startActivity(new Intent(AddAlarm.this, Info.class));
    }

    public void analysis(View view) {
        startActivity(new Intent(AddAlarm.this, MainActivity.class));
    }
}

