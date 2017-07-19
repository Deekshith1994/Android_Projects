package com.example.kdeek.hw6;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kdeek on 10/19/2016.
 */
public class DatabaseOpenhelper extends SQLiteOpenHelper{
    static final String DB_NAME = "mynotes.db";
    static final int DB_VERSION = 1;

    public DatabaseOpenhelper(Context context) {

        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Cities.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Cities.onUpgrade(db, oldVersion, newVersion);
    }
}
