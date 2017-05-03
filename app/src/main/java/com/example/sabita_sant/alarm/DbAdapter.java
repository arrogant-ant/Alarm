package com.example.sabita_sant.alarm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sabita_Sant on 16/04/17.
 */

public class DbAdapter {
    DbHelper helper;
    Context context;

    public DbAdapter(Context context) {
        helper=new DbHelper(context);
        this.context=context;

    }

    long insert(String time, String dismiss)
    {
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(DbHelper.TIME,time);
        contentValues.put(DbHelper.DISMISS,dismiss);
        return db.insert(DbHelper.TABLE_NAME,null,contentValues);

    }
    public Cursor getAll(RecentAlarmAdapter adapter){

        SQLiteDatabase db= helper.getWritableDatabase();
        String[] columns={DbHelper.TIME,DbHelper.DISMISS};
        Cursor cursor=db.query(DbHelper.TABLE_NAME,columns,null,null,null,null,null);
//        RecentAlarmRes recent;
//        while (cursor.moveToNext())
//        {
//
//            String time=cursor.getString(0);
//            String status=cursor.getString(1);
//            recent=new RecentAlarmRes(time,status);
//            adapter.add(recent);
//        }
        return cursor;
    }



    static class DbHelper extends SQLiteOpenHelper {
        private static final String DB_NAME = "Alarm";
        private static final String TABLE_NAME = "RecentAlarmRes";
        private static final int DB_VERSION = 1;
        private static final String TIME = "Time";
        private static final String DISMISS = "Dismiss";


        public DbHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String create = "CREATE TABLE " + TABLE_NAME + "(" + TIME + " VARCHAR(15)," + DISMISS + "  VARCHAR(10));";
            db.execSQL(create);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onCreate(db);

        }
    }
}
