package com.example.kiit.testing;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Reminders.db";
    public static final String TABLE_NAME = "Reminder_Table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Reminder_Task";
    public static final String COL_3 = "something";    //for boolean
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table " + TABLE_NAME + "( ID INTEGER PRIMARY KEY AUTOINCREMENT , Reminder_Task Text) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(" drop table if exists " + TABLE_NAME);
        onCreate(db);
    }


    public boolean insertData(String Task) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, Task);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else
            return true;
    }

    public Integer deleteData(String task) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COL_2 + " =? ", new String[]{task});
    }

    public ArrayList<Customers> listfromdb() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Customers> results = new ArrayList<>();
        results.clear();
        Cursor crs = db.rawQuery("select * from " + TABLE_NAME, null);
        while (crs.moveToNext()) {
            Customers customer = new Customers(
                    (crs.getString(crs.getColumnIndex(COL_2))),null);
            results.add(customer);
        }
        crs.close();
        db.close();
        return results;
    }
}

