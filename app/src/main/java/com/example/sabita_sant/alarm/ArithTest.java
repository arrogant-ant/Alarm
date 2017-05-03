package com.example.sabita_sant.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class ArithTest extends AppCompatActivity {

    TextView op1,op2, operand,alert;
    int val1,val2;
    EditText res;
    String result;
    int i;
    String time,dismiss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arith_test);
        op1= (TextView) findViewById(R.id.operand1);
        op2= (TextView) findViewById(R.id.operand2);
        operand = (TextView) findViewById(R.id.operator);
        res= (EditText) findViewById(R.id.res);
        alert= (TextView) findViewById(R.id.alert_text);
        set();
        i=0;
        time=getIntent().getExtras().get("time").toString();
    }

    private void set() {
        i++;
        char op[]={'+','-','*','/'};
        int r= (int) (Math.random()*10%4);
        operand.setText(String.valueOf(op[r]));
        val1= (int) ((Math.random()*100)%23);
        op1.setText(String.valueOf(val1));
        val2=(int) ((Math.random()*100)%16+1);
        op2.setText(String.valueOf(val2));
        AlarmReceiver.ins.mediaPlayer.start();
        switch (r)
        {
            case 0:
                result= String.valueOf((val1+val2));
                break;
            case 1:
                result= String.valueOf((val1-val2));
                break;
            case 2:
                result= String.valueOf((val1*val2));
                break;
            case 3:
                result= String.valueOf((val1/val2));
                break;

        }
        Toast.makeText(this,"res "+result,Toast.LENGTH_SHORT).show();

    }

    public void check(View view) {
        if(res.getText().toString().equals(result)){
            AlarmReceiver.ins.mediaPlayer.stop();
            dismiss= String.valueOf(Calendar.MILLISECOND);

            //      Inserting data in recent list
        DbAdapter adapter = new DbAdapter(ArithTest.this);
        long l= adapter.insert(time,dismiss);
        if(l>0)
            Toast.makeText(ArithTest.this,"inserted "+time+"  "+dismiss,Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(ArithTest.this,"NOT inserted "+time,Toast.LENGTH_SHORT).show();
        }
        else
        {
            if(i<4)
            {
                set();
                alert.setText("Sorry!! "+(4-i)+" more try left");
            }
            else
            {
                Toast.makeText(ArithTest.this,"ALARM SNOOZED FOR 5 MINS",Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(ArithTest.this,AlarmReceiver.class);
                intent.putExtra("time",time);
                PendingIntent pendingIntent= PendingIntent.getBroadcast(ArithTest.this,0,intent,0);
                AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, Calendar.MILLISECOND + 1*60*1000,pendingIntent);
            }

        }

    }
}
