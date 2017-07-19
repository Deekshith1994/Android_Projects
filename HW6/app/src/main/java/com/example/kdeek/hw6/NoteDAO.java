package com.example.kdeek.hw6;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kdeek on 10/19/2016.
 */
public class NoteDAO {

    private SQLiteDatabase db;

    public NoteDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public long save(Note note){
        ContentValues values = new ContentValues();
        values.put(Cities.COL_CITY, note.getCity());
        values.put(Cities.COL_COUNTRY, note.getCountry());
        values.put(Cities.COL_TEMP, note.getTemp());
        values.put(Cities.COL_fav,note.isFav());

        return db.insert(Cities.TABLE_NAME ,null, values);
    }

    public boolean update(Note note){
        ContentValues values = new ContentValues();
        values.put(Cities.COL_CITY, note.getCity());
        values.put(Cities.COL_COUNTRY, note.getCountry());
        values.put(Cities.COL_TEMP, note.getTemp());
        values.put(Cities.COL_fav,note.isFav());
        return db.update(Cities.TABLE_NAME,values,Cities.COL_ID + "=?", new String[]{note.getId() + ""}) > 0;
    }

    public boolean delete(Note note){
        return db.delete(Cities.TABLE_NAME,Cities.COL_ID + "=?", new String[]{note.getId() + ""}) > 0;
    }

    public Note get(long _id){
        Note note =  null;
        Cursor c = db.query(true,Cities.TABLE_NAME,new String[] {Cities.COL_ID, Cities.COL_CITY, Cities.COL_COUNTRY, Cities.COL_TEMP, Cities.COL_fav}, Cities.COL_ID + "=?", new String[] {_id + ""},
                null,null,null,null);

        if (c != null && c.moveToFirst()){
            note = buildNotefromCursor(c);
            if (!c.isClosed()){
                c.close();
            }
        }
        return note;
    }

    public List<Note> getAll(){
        List<Note> notes = new ArrayList<>();
        Cursor c = db.query(Cities.TABLE_NAME, new String[]{Cities.COL_ID, Cities.COL_CITY, Cities.COL_COUNTRY, Cities.COL_TEMP, Cities.COL_fav}, null,null,null,null,null);
        if (c !=null && c.moveToFirst()){
            do {
                Note note = buildNotefromCursor(c);
                if (note != null){
                    notes.add(note);
                }
            }while (c.moveToNext());
            if (!c.isClosed()){
                c.close();
            }
        }
        return notes;
    }

    private Note buildNotefromCursor(Cursor c){
        Note note = null;
        if (c != null){
            note = new Note();
            note.setId(c.getInt(0));
            note.setCity(c.getString(1));
            note.setCountry(c.getString(2));
            note.setTemp(c.getDouble(3));
            note.setFav(c.getInt(4));
        }
        return note;
    }

}
