package com.example.sabita_sant.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class ArithTest
        extends AppCompatActivity {
    TextView alert;
    Calendar cal = Calendar.getInstance();
    long dismiss;
    int i;
    TextView op1;
    TextView op2;
    TextView operand;
    EditText res;
    String result;
    int snooze_time;
    long time;
    int val1;
    int val2;
    MediaPlayer mediaPlayer;
    MediaPlayer tone[] = new MediaPlayer[3];
    boolean stopped = false;
    private long timeCountInMilliSeconds = 15*1000;
    private CountDownTimer countDownTimer;
    private TimerStatus timerStatus = TimerStatus.STOPPED;
    private ProgressBar progressBarCircle;
    private enum TimerStatus {
        STARTED,
        STOPPED
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arith_test);
        op1 = (TextView) findViewById(R.id.operand1);
        op2 = (TextView) findViewById(R.id.operand2);
        operand = (TextView) findViewById(R.id.operator);
        res = (EditText) findViewById(R.id.res);
        alert = (TextView) findViewById(R.id.alert_text);
        progressBarCircle = (ProgressBar) findViewById(R.id.progressBarCircle);

        tone[0] = MediaPlayer.create(ArithTest.this, R.raw.tone1);
        tone[1] = MediaPlayer.create(ArithTest.this, R.raw.tone2);
        tone[2] = MediaPlayer.create(ArithTest.this, R.raw.tone3);
        mediaPlayer = tone[(int) (Math.random() * 10 % 3)];
        this.time = AddAlarm.ALARM_TIME;
        this.snooze_time = AddAlarm.snooze;
        //tone_handler=new Handler();

        i = 0;
        set();
    }

    private void set() {
        timerStatus = TimerStatus.STARTED;
        setProgressBarValues();
        startCountDownTimer();
        i++;
        char op[] = {'+', '-', '*', '/'};
        int r = (int) (Math.random() * 10 % 4);
        operand.setText(String.valueOf(op[r]));
        val1 = (int) ((Math.random() * 100) % 23);
        op1.setText(String.valueOf(val1));
        val2 = (int) ((Math.random() * 100) % 16 + 1);
        op2.setText(String.valueOf(val2));
        mediaPlayer.start();
        switch (r) {
            case 0:
                result = String.valueOf((val1 + val2));
                break;
            case 1:
                result = String.valueOf((val1 - val2));
                break;
            case 2:
                result = String.valueOf((val1 * val2));
                break;
            case 3:
                result = String.valueOf((val1 / val2));
                break;

        }
        Toast.makeText(this, "res " + result, Toast.LENGTH_SHORT).show();
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        if(mediaPlayer.isPlaying()&& !stopped)
//        {
//            mediaPlayer.stop();
//            PendingIntent pendingIntent = PendingIntent.getBroadcast(ArithTest.this, 0, new Intent(ArithTest.this, AlarmReceiver.class), 0);
//            ((AlarmManager) getSystemService(ALARM_SERVICE)).set(AlarmManager.RTC_WAKEUP, Calendar.MILLISECOND + 60000 * this.snooze_time, pendingIntent);
//            finish();
//        }

    }


    public void check(View paramView) {
        if (this.res.getText().toString().equals(this.result)) {
            mediaPlayer.stop();
            stopped = true;
            this.dismiss = Calendar.getInstance().getTimeInMillis();
            this.time = (this.time % 86400000L / 60000L);
            this.dismiss = (this.dismiss % 86400000L / 60000L);
            if (new DbAdapter(this).insert(this.time, this.dismiss) > 0L) {
                Toast.makeText(this, "inserted " + this.time + "  " + this.dismiss, Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(ArithTest.this, "NOT inserted " + time, Toast.LENGTH_SHORT).show();
            finish();

        } else {
            if (this.i < 4) {
                set();
                this.alert.setText("Sorry!! " + (4 - this.i) + " more try left");
                return;
            } else {

                stopped = true;
                mediaPlayer.stop();
                Toast.makeText(this, "ALARM SNOOZED FOR " + this.snooze_time + " MINS", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ArithTest.this, AlarmReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(ArithTest.this, 0, intent, 0);
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, Calendar.getInstance().getTimeInMillis() + 60000 * snooze_time, pendingIntent);
                finish();
            }
        }
    }




    /**
     * method to start count down timer
     */
    private void startCountDownTimer() {

        countDownTimer = new CountDownTimer(timeCountInMilliSeconds,100) {
            @Override
            public void onTick(long millisUntilFinished) {

                progressBarCircle.setProgress((int) (millisUntilFinished/1000));

            }

            @Override
            public void onFinish() {

                Toast.makeText(ArithTest.this,"Time up",Toast.LENGTH_SHORT).show();
                timerStatus = TimerStatus.STOPPED;
            }

        }.start();
        countDownTimer.start();
    }

    /**
     * method to stop count down timer
     */
    private void stopCountDownTimer() {
        countDownTimer.cancel();
    }

    /**
     * method to set circular progress bar values
     */
    private void setProgressBarValues() {

        progressBarCircle.setMax((int) timeCountInMilliSeconds / 1000);
        progressBarCircle.setProgress((int) timeCountInMilliSeconds / 1000);

    }
}



/* Location:           C:\Users\Sabita_Sant\Desktop\Alarm\dex2jar-0.0.9.15\classes_dex2jar.jar

 * Qualified Name:     com.example.sabita_sant.alarm.ArithTest

 * JD-Core Version:    0.7.0.1

 */