package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.IntegerRes;
import android.support.annotation.Nullable;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Contact2019.db";
    public static final String TABLE_NAME = "Contact2019_table";
    public static final String ID = "ID";
    public static final String COLUMN_NAME_CONTACT = "contact";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID  + "INTEGER PRIMARY KEY AUTONINCREMENT," +
                    COLUMN_NAME_CONTACT + " TEXT)";
    public static final String SQL_DELETE_ENTIRES=
            "DROB TALBE IF EXISTS"  + TABLE_NAME;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null,  1);
        SQLiteDatabase db = this.getWritableDatabase(); //FOR TEST ONLY, REMOVE LATER
        Log.d("MyContactApp","DatabaseHelper: contruscted the DatabaseHelper");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
