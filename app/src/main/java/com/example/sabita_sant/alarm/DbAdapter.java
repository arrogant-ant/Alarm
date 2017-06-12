package com.example.sabita_sant.alarm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbAdapter
{
  Context context;
  DbHelper helper;
  long offset = 330L;
  
  public DbAdapter(Context paramContext)
  {
    this.helper = new DbHelper(paramContext);
    this.context = paramContext;
  }
  
  public Cursor getAll()
  {
    return this.helper.getWritableDatabase().query("RecentAlarmRes", new String[] { "Time", "Dismiss" }, null, null, null, null, null);
  }
  
  long insert(long paramLong1, long paramLong2)
  {
    long l1 = paramLong1 + this.offset;
    long l2 = paramLong2 + this.offset;
    SQLiteDatabase localSQLiteDatabase = this.helper.getWritableDatabase();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("Time", Long.valueOf(l1));
    localContentValues.put("Dismiss", Long.valueOf(l2));
    return localSQLiteDatabase.insert("RecentAlarmRes", null, localContentValues);
  }
  
  static class DbHelper
    extends SQLiteOpenHelper
  {
    private static final String DB_NAME = "Alarm";
    private static final int DB_VERSION = 1;
    private static final String DISMISS = "Dismiss";
    private static final String TABLE_NAME = "RecentAlarmRes";
    private static final String TIME = "Time";

    public DbHelper(Context context) {
      super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
      paramSQLiteDatabase.execSQL("CREATE TABLE"+TABLE_NAME+"("+TIME+"VARCHAR(15),"+DISMISS+"VARCHAR(10));");
    }
    
    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
      paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS"+TABLE_NAME+";");
      onCreate(paramSQLiteDatabase);
    }
  }
}



/* Location:           C:\Users\Sabita_Sant\Desktop\Alarm\dex2jar-0.0.9.15\classes_dex2jar.jar

 * Qualified Name:     com.example.sabita_sant.alarm.DbAdapter

 * JD-Core Version:    0.7.0.1

 */