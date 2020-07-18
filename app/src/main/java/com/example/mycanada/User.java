package com.example.mycanada;

import android.content.ContentValues;
import android.content.Context;

public class User extends DataAccess {

    private String mobile;
    private String birth;
    private int gender;
    private int qualification;

    public User(Context context, String mobile, String birth, int gender, int qualification) {
        super(context);
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
}
