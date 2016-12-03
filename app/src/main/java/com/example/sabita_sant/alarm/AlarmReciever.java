package com.example.sabita_sant.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Sabita_Sant on 14/10/16.
 */
public class AlarmReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("Inside", "alaarm Reciever");
        AddAlarm inst = AddAlarm.instance();
        inst.setAlarmText("Alarm! Wake up! Wake up!");
        //intent to service
        Intent tone_service = new Intent(context,AlarmToneService.class);
        String msg= String.valueOf(intent.getExtras().getBoolean("state"));
        Toast.makeText(context,"state = "+msg,Toast.LENGTH_SHORT).show();
        //tone_service.putExtra("state",intent.getExtras().getBoolean("state"));
        context.startService(tone_service);
    }
}
