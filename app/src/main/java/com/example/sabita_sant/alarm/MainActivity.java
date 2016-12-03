package com.example.sabita_sant.alarm;

import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_clock);


        if(savedInstanceState== null)
        {
            getFragmentManager().beginTransaction().add(R.id.menuContainer ,new OptionMenu()).commit();
            getFragmentManager().beginTransaction().add(R.id.alarmContainer ,new AlarmList()).commit();
        }
    }

    public void openAlarm(View v) {

        Toast.makeText(getApplicationContext(), "In open Alarm",Toast.LENGTH_SHORT).show();
        getFragmentManager().beginTransaction().replace(R.id.menuContainer ,new OptionMenu()).commit();
        getFragmentManager().beginTransaction().replace(R.id.alarmContainer, new AlarmList()).commit();
    }

    public void addAlarm(View v)
    {
        Intent intent=new Intent(getApplicationContext(),AddAlarm.class);
        startActivity(intent);
    }

}
