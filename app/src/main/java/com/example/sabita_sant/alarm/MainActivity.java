package com.example.sabita_sant.alarm;

import android.content.DialogInterface;
import android.content.Intent;
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


public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    float touch,release;
    RecentAlarmAdapter recent;
    DbAdapter dbAdapter;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        getFragmentManager().beginTransaction().replace(R.id.clkContainer,new Analog()).commit();
        ViewGroup viewGroup= (ViewGroup) findViewById(R.id.parent);
        viewGroup.setOnTouchListener(this);

        recent= new RecentAlarmAdapter(this,R.layout.recent_row);
        dbAdapter=new DbAdapter(this);
        listView= (ListView) findViewById(R.id.listview_alarm);
        listView.setAdapter(recent);

        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("item","selected");
                Toast.makeText(MainActivity.this,"selected",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,"item clicked",Toast.LENGTH_SHORT).show();
                Log.e("item","click");
                AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Remove")
                        .setMessage("Are you sure to remove alarm ")
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setNegativeButton("Cancel",null);
                builder.show();
                return true;

            }
 

        });



    }


    @Override
    protected void onResume() {
        super.onResume();
        recent.clear();
        dbAdapter.getAll(recent);


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
