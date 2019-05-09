package com.allumez.offlinesurvey;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHelper extends SQLiteOpenHelper {

    //Constants for Database names, table names, and column names
    public static final String DB_NAME = "NamesDB";
    public static final String TABLE_NAME = "offline_survey";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_PHOTO = "photo";
    public static final String COLUMN_STATUS = "status";

    //database version
    private static final int DB_VERSION = 1;
    private Context context;

    //Constructor
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //creating the database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME
                + "("
                + COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME +" VARCHAR, "
                + COLUMN_PHONE + " TINYINT, "
                + COLUMN_ADDRESS + " VARCHAR, "
                + COLUMN_PHOTO + " BLOB, "
                + COLUMN_STATUS +" TINYINT);";

        db.execSQL(sql);
    }

    //upgrading the database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Persons";
        db.execSQL(sql);
        onCreate(db);
    }

    /*
     * This method is taking two arguments
     * first one is the names that is to be saved
     * second one is the status
     * 0 means the names is synced with the server
     * 1 means the names is not synced with the server
     * */


    public boolean addName(String name, String phone, String address, byte[] image, int status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_PHONE, phone);
        contentValues.put(COLUMN_ADDRESS,address);
        contentValues.put(COLUMN_PHOTO,image);
        contentValues.put(COLUMN_STATUS, status);

        db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }

    /*
     * This method taking two arguments
     * first one is the id of the names for which
     * we have to update the sync status
     * and the second one is the status that will be changed
     * */
    public boolean updateNameStatus(int id, int status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_STATUS, status);
        db.update(TABLE_NAME, contentValues, COLUMN_ID + "=" + id, null);
        db.close();
        Log.e("COLUMN ID", String.valueOf(id));
        return true;
    }

    /*
     * this method will give us all the names stored in sqlite
     * */
    public Cursor getNames() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + COLUMN_ID + " ASC;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    /*
     * this method is for getting all the unsynced names
     * so that we can sync it with database
     * */
    public Cursor getUnsyncedNames() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_STATUS + " = 0;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public Cursor deleteTabledata()
    {
        SQLiteDatabase db =this.getReadableDatabase();
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = id;";
        Cursor c = db.rawQuery(sql,null);
        return c;
    }
    public Cursor getId() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        String sql = "SELECT * FROM" names ORDER BY + " COLUMN_ID " DESC LIMIT 1;"


        String sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + COLUMN_ID + " ASC;";
        Cursor c = db.rawQuery(sql,null);

        return c;
    }


}