package com.example.sabita_sant.alarm;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class Info extends AppCompatActivity implements View.OnTouchListener  {

    float release;
    float touch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        getFragmentManager().beginTransaction().replace(R.id.clkContainer, new Analog()).commit();
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.parent);
        viewGroup.setOnTouchListener(this);

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
