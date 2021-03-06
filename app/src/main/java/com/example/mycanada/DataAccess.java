package com.example.mycanada;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataAccess extends SQLiteOpenHelper {
    SQLiteDatabase sqLiteDatabase;

    public DataAccess(Context context) {
        //TODO get database name from configuration file
        super(context, "mycanada.db", null, 2);
        sqLiteDatabase = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user (Username text, Password text, Mobile text, Birth text, Gender integer, Qualification integer, Photo text)");
        db.execSQL("create table qualification (Id integer, Description text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.rawQuery("ALTER TABLE user ADD COLUMN photo text", null);
    }

    public void insert(String table, ContentValues values) {
        sqLiteDatabase.insert(table, null, values);
    }

    public void update(String table, ContentValues values, String whereClause, String[] whereArgs) {
        sqLiteDatabase.update(table, values, whereClause, whereArgs);
    }

    public void delete(String table, String whereClause, String[] whereArgs) {
        sqLiteDatabase.delete(table, whereClause, whereArgs);
    }

    public Cursor get(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        Cursor c = sqLiteDatabase.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
        //c.moveToFirst();
        return c;
    }
}