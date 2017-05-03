package com.example.sabita_sant.alarm;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    float touch,release;
    RecentAlarmAdapter recent;
    DbAdapter dbAdapter;
    GraphView graph;
    LineGraphSeries<DataPoint> alarm_time,dismiss_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        getFragmentManager().beginTransaction().replace(R.id.clkContainer,new Analog()).commit();
        ViewGroup viewGroup= (ViewGroup) findViewById(R.id.parent);
        viewGroup.setOnTouchListener(this);
        graph= (GraphView) findViewById(R.id.graph);
        dbAdapter=new DbAdapter(this);
        alarm_time= new LineGraphSeries<DataPoint>(getAlarmTime());
        dismiss_time=new LineGraphSeries<DataPoint>(getDismissTime());
        graph.addSeries(alarm_time);
        graph.addSeries(dismiss_time);


    }

    private DataPoint[] getDismissTime() {
        Cursor cursor=dbAdapter.getAll();
        DataPoint[] data =new DataPoint[cursor.getCount()];
        for(int i=0;i<cursor.getCount();i++)
        {
            cursor.moveToNext();
            data[i]=new DataPoint(i,cursor.getLong(0));
        }
        return data;

    }
    private DataPoint[] getAlarmTime() {
        Cursor cursor=dbAdapter.getAll();
        DataPoint[] data =new DataPoint[cursor.getCount()];
        for(int i=0;i<cursor.getCount();i++)
        {
            cursor.moveToNext();
            data[i]=new DataPoint(i,cursor.getLong(1));
        }
        return data;
    }
    @Override
    protected void onResume() {
        super.onResume();



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

            if(release-touch>5)
                getFragmentManager().beginTransaction().replace(R.id.clkContainer,new Analog()).commit();
            else if(touch-release>5)
                getFragmentManager().beginTransaction().replace(R.id.clkContainer,new Digital()).commit();
        }

        return true;
    }


}
