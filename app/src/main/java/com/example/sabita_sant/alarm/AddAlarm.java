package com.example.sabita_sant.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AddAlarm extends AppCompatActivity{

    private int hour,min;
    private TimePicker timePicker;
    private TextView time;
    private String format;
    static StringBuilder AlarmText;

    Calendar calendar = Calendar.getInstance();
    private static AddAlarm inst;
    private static Intent alarm_intent;
    SharedPreferences sharedPreferences;
    private static int no_rep=0;       //counts the no of repetion days
    AlarmService alarmService;



    @Override
    public void onStart() {
        super.onStart();
        inst = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences= getSharedPreferences("Repetition", Context.MODE_PRIVATE);
        setContentView(R.layout.activity_add_alarm);
        timePicker= (TimePicker) findViewById(R.id.timePicker);
        time = (TextView)findViewById(R.id.showtime);
        alarmTime(hour,min);
        alarmService = AlarmService.instance();
        alarm_intent = new Intent(AddAlarm.this, AlarmService.class);
    }
    public static AddAlarm instance() {
        return inst;
    }
    //converts 24h to 12h
    public void alarmTime(int hour, int min) {
        Toast.makeText(this,"inside alarm Time",Toast.LENGTH_LONG).show();
        if(hour==0)
            format="A.M.";
        else if(hour==12)
            format="P.M.";
        else if(hour>12)
        {
            format="P.M.";
            hour-=12;
        }
        else
            format="A.M.";
        if(min<10)
            AlarmText=new StringBuilder("").append(hour).append(":0").append(min).append(" ").append(format);
        else
            AlarmText=new StringBuilder("").append(hour).append(":").append(min).append(" ").append(format);
        time.setText("Alarm Set at "+AlarmText);


    }



    //sets alarm
    public void setAlarm(View view) {
        //gets alarm time
        hour=timePicker.getCurrentHour();
        min=timePicker.getCurrentMinute();
        calendar.set(Calendar.HOUR_OF_DAY,hour);
        calendar.set(Calendar.MINUTE, min);

        alarmTime(calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE));
        Intent intent= new Intent(AddAlarm.this,AlarmReceiver.class);
        PendingIntent pendingIntent= PendingIntent.getBroadcast(AddAlarm.this,0,intent,0);

        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);


    }



    //stops alarm
    void stopAlarm()
    {
       startActivity(new Intent(AddAlarm.this,ArithTest.class));
    }
    //prints alarm time
    public void setAlarmText(String s)
    {
        time.setText(s);
    }

    //onclick off button
    public void alarmoff(View view) {
        stopAlarm();
    }

    //marks the repetition days
    public void selectRepetition(View view) {
        boolean checked= ((CheckBox)view).isChecked();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (checked)
            no_rep++;
        else
            no_rep--;

        //update repition day
        switch (view.getId())
        {
            case R.id.monBox:
                editor.putBoolean("Mon",checked);
                break;
            case R.id.tueBox:
                editor.putBoolean("Tue",checked);
                break;
            case R.id.wedBox:
                editor.putBoolean("Wed",checked);
                break;
            case R.id.thuBox:
                editor.putBoolean("Thu",checked);
                break;
            case R.id.friBox:
                editor.putBoolean("Fri",checked);
                break;
            case R.id.satBox:
                editor.putBoolean("Sat",checked);
                break;
            case R.id.sunBox:
                editor.putBoolean("Sun",checked);
                break;
        }
        editor.apply();

    }


}
