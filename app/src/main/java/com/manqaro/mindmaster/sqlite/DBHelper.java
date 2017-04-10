package com.manqaro.mindmaster.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Game", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table colors (highscore integer not null);");
        db.execSQL("create table math_easy (highscore integer not null);");
        db.execSQL("create table math_hard (highscore integer not null);");
        db.execSQL("create table memorize (highscore integer not null);");
        db.execSQL("create table pairs (highscore integer not null);");
        db.execSQL("create table numbers (highscore integer not null);");
        db.execSQL("create table shapes (highscore integer not null);");
        db.execSQL("create table combo (highscore integer not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}