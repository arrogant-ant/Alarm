package com.example.sabita_sant.alarm;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Sabita_Sant on 09/10/16.
 */
public class AlarmList  extends Fragment implements OnTouchListener{
    float touch,release;
    public AlarmList()
    {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.alarm_list, container, false);
        rootview.setOnTouchListener(this);
        return rootview;

    }
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
