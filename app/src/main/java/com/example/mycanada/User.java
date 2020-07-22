package com.example.mycanada;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

public class User extends DataAccess {

    private Context context;
    private String mobile;
    private String birth;
    private int gender;
    private int qualification;

    public User(Context context, String mobile, String birth, int gender, int qualification) {
        super(context);
        this.context = context;
        this.mobile = mobile;
        this.birth = birth;
        this.gender = gender;
        this.qualification = qualification;
    }

    public void InsertUser() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Mobile", this.mobile);
        contentValues.put("Birth", this.birth);
        contentValues.put("Gender", this.gender);
        contentValues.put("Qualification", this.qualification);
        insert("user", contentValues);
    }

    public void UpdateUser() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Birth", this.birth);
        contentValues.put("Gender", this.gender);
        contentValues.put("Qualification", this.qualification);
        update("user", contentValues, "Mobile = ?", new String[]{this.mobile});
    }

    public void DeleteUser() {
        delete("user", "Mobile = ?", new String[]{this.mobile});
    }

    public User GetUser() {
        String result = "";
        Cursor c = get("user", null, "Mobile=?", new String[]{this.mobile}, null, null, null);
        c.moveToFirst();
        if (c.getCount() < 1) {
            return null;
        }
        User user = new User(context,
                c.getString(c.getColumnIndex("Mobile")),
                c.getString(c.getColumnIndex("Birth")),
                c.getInt(c.getColumnIndex("Gender")),
                c.getInt(c.getColumnIndex("Qualification")));
        return user;
    }

    public ArrayList<User> GetUsers() {
        ArrayList users = new ArrayList<User>();
        Cursor c = get("user", null, "Mobile=?", new String[]{this.mobile}, null, null, null);
        c.moveToFirst();
        if (c.getCount() > 0) {
            do {
                User user = new User(context,
                        c.getString(c.getColumnIndex("Mobile")),
                        c.getString(c.getColumnIndex("Birth")),
                        c.getInt(c.getColumnIndex("Gender")),
                        c.getInt(c.getColumnIndex("Qualification")));
                users.add(user);
            } while (c.moveToNext());
        }
        return users;
    }
}