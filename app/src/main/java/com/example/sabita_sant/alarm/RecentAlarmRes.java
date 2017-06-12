package com.example.sabita_sant.alarm;

public class RecentAlarmRes
{
  String status;
  String time;
  
  public RecentAlarmRes(String paramString1, String paramString2)
  {
    this.status = paramString2;
    this.time = paramString1;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public String getTime()
  {
    return this.time;
  }
}


/* Location:           C:\Users\Sabita_Sant\Desktop\Alarm\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.example.sabita_sant.alarm.RecentAlarmRes
 * JD-Core Version:    0.7.0.1
 */