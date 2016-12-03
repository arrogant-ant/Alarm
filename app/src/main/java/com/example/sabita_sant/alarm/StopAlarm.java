package com.example.sabita_sant.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Sabita_Sant on 26/10/16.
 */

public class StopAlarm extends BroadcastReceiver {
    AddAlarm ins;
    @Override
    public void onReceive(Context context, Intent intent) {
        ins=AddAlarm.instance();
       ins.stopAlarm();
    }
}
