package com.example.mycanada;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataAccess extends SQLiteOpenHelper {
    SQLiteDatabase sqLiteDatabase;
    public DataAccess(Context context) {
        super(context, "mycanada.db", null, 1);
        sqLiteDatabase = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user (Mobile text, Birth text, Gender integer, Qualification integer)");
        db.execSQL("create table qualification (Id integer, Description text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(String table, ContentValues values) {
        sqLiteDatabase.insert(table, null, values);
    }
}
