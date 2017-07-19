package com.example.kdeek.hw6;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by kdeek on 10/19/2016.
 */
public class Cities {
    static final String TABLE_NAME = "Cities";
    static final String COL_ID = "cid";
    static final String COL_CITY = "city";
    static final String COL_COUNTRY = "country";
    static final String COL_TEMP = "temperature";
    static final String COL_fav = "favorite";

    static public void onCreate(SQLiteDatabase db){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + TABLE_NAME + " (");
        sb.append(COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , ");
        sb.append(COL_CITY + " TEXT NOT NULL, ");
        sb.append(COL_COUNTRY + " TEXT NOT NULL, ");
        sb.append(COL_TEMP + " REAL NOT NULL, ");
        sb.append(COL_fav + " INTEGER NOT NULL);");
        Log.d("Query",sb.toString());
        try {
            db.execSQL(sb.toString());
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    static public void onUpgrade(SQLiteDatabase db, int OldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        Cities.onCreate(db);
    }
}
