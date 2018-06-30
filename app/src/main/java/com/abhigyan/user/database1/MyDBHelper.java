package com.abhigyan.user.database1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by USER on 30-06-2018.
 */

public class MyDBHelper extends SQLiteOpenHelper {

   public static  final String DATABASE_NAME = "student.db";
   public static  final int DATABASE_VERSION = 1;
   public static  final String TABLE_NAME = "this_table";
   public static  final String COL_1 = "ID";
   public static  final String COL_2 = "NAME";
   public static  final String COL_3 = "REGISTRATION ";

    public MyDBHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL( "create table " + TABLE_NAME +" ("+COL_1+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+COL_2+" TEXT"+COL_3+" ,TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String regdno)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_2, name);
        cv.put(COL_3, regdno);
        long result = db.insert(TABLE_NAME,null, cv);

        if(result == -1)
        {
            return  false;
        }
        else
        {
            Log.i("Done", "done");
            return  true;

        }
    }

    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor resources = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return resources;
    }

    public Boolean updateData(String id, String name, String regdno)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_1,id);
        cv.put(COL_2, name);
        cv.put(COL_3, regdno);
        db.update(TABLE_NAME,cv, "ID = ?", new String[]{ id } );
        return true;
    }

    public Boolean deleteData(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,"ID = ?", new String[]{ id });
        return true;
    }


}
