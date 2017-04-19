package com.example.sabita_sant.alarm;

/**
 * Created by Sabita_Sant on 16/04/17.
 */

public class RecentAlarmRes {
    String time, status;

    public RecentAlarmRes( String time,String status) {
        this.status = status;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }
}
