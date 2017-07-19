package com.example.kdeek.hw6;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by kdeek on 10/19/2016.
 */
public class DatabaseManager {
    private Context context;
    private DatabaseOpenhelper databaseOpenHelper;
    private SQLiteDatabase db;
    private NoteDAO noteDAO;

    public DatabaseManager(Context context) {
        this.context = context;
        databaseOpenHelper =  new DatabaseOpenhelper(this.context);
        db = databaseOpenHelper.getWritableDatabase();
        noteDAO = new NoteDAO(db);
    }

    public void close(){
        if (db != null){
            db.close();
        }
    }

    public NoteDAO getNoteDAO() {
        return this.noteDAO;
    }

    public long saveNote(Note note){
        return this.noteDAO.save(note);
    }

    public boolean updateNote(Note note){
        return this.noteDAO.update(note);
    }

    public boolean deleteNote(Note note){
        return this.noteDAO.delete(note);
    }

    public Note getNote(long _id){
        return this.noteDAO.get(_id);
    }

    public List<Note> getAllNote(){
        return this.noteDAO.getAll();
    }

}
