package com.example.sabita_sant.alarm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    float touch,release;
    RecentAlarmAdapter recent;
    DbAdapter dbAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        getFragmentManager().beginTransaction().replace(R.id.clkContainer,new Analog()).commit();
        ViewGroup viewGroup= (ViewGroup) findViewById(R.id.parent);
        viewGroup.setOnTouchListener(this);

        recent= new RecentAlarmAdapter(this,R.layout.recent_row);
        ListView listView= (ListView) findViewById(R.id.listview_alarm);
        listView.setAdapter(recent);
        dbAdapter=new DbAdapter(this);
        dbAdapter.getAll();
    }



    public void addAlarm(View v)
    {
        Intent intent=new Intent(getApplicationContext(),AddAlarm.class);
        startActivity(intent);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN)
            touch=event.getY();
        if(event.getAction()==MotionEvent.ACTION_UP)
            release=event.getY();
        {

            if(release>touch)
                getFragmentManager().beginTransaction().replace(R.id.clkContainer,new Analog()).commit();
            else
                getFragmentManager().beginTransaction().replace(R.id.clkContainer,new Digital()).commit();
        }

        return true;
    }


}
