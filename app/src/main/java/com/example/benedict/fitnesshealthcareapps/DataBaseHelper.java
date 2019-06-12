package com.example.benedict.fitnesshealthcareapps;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Benedict on 17/03/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "fitness.db";
    private static  final String Create_Table_User = "CREATE TABLE user_table (USERNAME STRING PRIMARY KEY, PASSWORD STRING, NAME STRING);";
        private static  final String Create_Table_activities = "CREATE TABLE activity_table (USERNAME STRING, ACTIVITY STRING, TIME STRING, DATE STRING);";
    SQLiteDatabase db = this.getWritableDatabase();

    public DataBaseHelper(Context context) {
        //create database
        super(context, DATABASE_NAME, null, 1);
        Log.e("DATABASE OPERATIONS", "Database created / opened. . .");
    }

    @Override
    public void onCreate(SQLiteDatabase Db) {
        Db.execSQL(Create_Table_User);
        Db.execSQL(Create_Table_activities);
        Log.e("DATABASE OPERATIONS", "Tables created. . .");
    }

    @Override
    public void onUpgrade(SQLiteDatabase Db, int oldVersion, int newVersion) {
        Db.execSQL("DROP TABLE IF EXIST user_table");
        Db.execSQL("DROP TABLE IF EXIST user_table");
        onCreate(Db);
    }

    public void insertUser(String USERNAME,String PASSWORD, String NAME){
        db.execSQL("INSERT INTO user_table (USERNAME,PASSWORD,NAME) VALUES(\""+USERNAME+"\",\""+PASSWORD+"\",\""+NAME+"\");");
    }

    public String RetriveUserInfo(String Username,String ColumnName){
        String Data= null;
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT " + ColumnName + " FROM user_table WHERE USERNAME=?", new String[]{Username + ""});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                Data = cursor.getString(cursor.getColumnIndex(ColumnName));
            }
        }
        catch(Exception ex){
            Log.e("Error", ex.getMessage() + " Catch Checking. . .");
        }
        finally{
            cursor.close();
        }
        Log.e("DATABASE OPERATIONS", Data + " One row Selected. . .");
        return Data;
    }

    public void insertActivity(String USERNAME, String ACTIVITY){
        Calendar calendar;
        SimpleDateFormat DateFormat = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat TimeFormat = new SimpleDateFormat("HH:mm");
        calendar = Calendar.getInstance();
        String Date = DateFormat.format(calendar.getTime());
        String Time = TimeFormat.format(calendar.getTime());
        db.execSQL("INSERT INTO activity_table (USERNAME,ACTIVITY,TIME, DATE) VALUES(\""+USERNAME+"\",\""+ACTIVITY+"\",\""+Time+"\",\""+Date+"\");");
        Log.e("DATABASE OPERATIONS", "Data Inserted at activity_table. . .");
    }

    public ArrayList<String> RetriveActivities(String USERNAME){
        String ACTIVITY,TIME,DATE;
        ArrayList<String> data = new ArrayList<String>();
        Cursor c = db.rawQuery("SELECT ACTIVITY,TIME,DATE FROM activity_table WHERE USERNAME=\""+USERNAME+"\"", null);
        data.add(0,"ACTIVITY");
        data.add(1,"TIME");
        data.add(2,"DATE");
        if (c.moveToFirst())
            do {
                ACTIVITY = c.getString(c.getColumnIndex("ACTIVITY"));
                TIME = c.getString(c.getColumnIndex("TIME"));
                DATE = c.getString(c.getColumnIndex("DATE"));
                data.add(ACTIVITY);
                data.add(TIME);
                data.add(DATE);
            } while (c.moveToNext());
        return data;
    }
}
