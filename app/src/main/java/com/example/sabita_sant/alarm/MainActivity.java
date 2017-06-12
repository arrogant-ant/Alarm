package com.example.sabita_sant.alarm;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class MainActivity
        extends AppCompatActivity
        {
    LineGraphSeries<DataPoint> alarm_time;
    DbAdapter dbAdapter;
    LineGraphSeries<DataPoint> dismiss_time;
    GraphView graph;
    RecentAlarmAdapter recent;


    private DataPoint[] getAlarmTime() {
        Cursor localCursor = this.dbAdapter.getAll();
        DataPoint[] arrayOfDataPoint = new DataPoint[localCursor.getCount()];
        for (int i = 0; i < localCursor.getCount(); i++) {
            localCursor.moveToNext();
            arrayOfDataPoint[i] = new DataPoint(i, localCursor.getLong(1));
    }
        return arrayOfDataPoint;
    }

    private DataPoint[] getDismissTime() {
        Cursor localCursor = this.dbAdapter.getAll();
        DataPoint[] arrayOfDataPoint = new DataPoint[localCursor.getCount()];
        for (int i = 0; i < localCursor.getCount(); i++) {
            localCursor.moveToNext();
            arrayOfDataPoint[i] = new DataPoint(i, localCursor.getLong(0));
    }
        return arrayOfDataPoint;
    }

    public void addAlarm(View paramView) {
        startActivity(new Intent(getApplicationContext(), AddAlarm.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        graph = (GraphView) findViewById(R.id.graph);
        this.graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            public String formatLabel(double paramAnonymousDouble, boolean paramAnonymousBoolean) {
                if (paramAnonymousBoolean) {
                    return super.formatLabel(paramAnonymousDouble, paramAnonymousBoolean);
        }
                return super.formatLabel(paramAnonymousDouble / 60.0D, paramAnonymousBoolean) + ":" + super.formatLabel(paramAnonymousDouble % 60.0D, paramAnonymousBoolean);
            }
        });
        this.dbAdapter = new DbAdapter(this);
        this.alarm_time = new LineGraphSeries(getAlarmTime());
        this.alarm_time.setColor(-16776961);
        this.alarm_time.setTitle("Alarm Time");
        this.dismiss_time = new LineGraphSeries(getDismissTime());
        this.dismiss_time.setColor(-16711936);
        this.dismiss_time.setTitle("Dismiss Time");
        this.graph.addSeries(this.alarm_time);
        this.graph.addSeries(this.dismiss_time);
    }

    protected void onResume() {
        super.onResume();
    }

}



/* Location:           C:\Users\Sabita_Sant\Desktop\Alarm\dex2jar-0.0.9.15\classes_dex2jar.jar

 * Qualified Name:     com.example.sabita_sant.alarm.MainActivity

 * JD-Core Version:    0.7.0.1

 */