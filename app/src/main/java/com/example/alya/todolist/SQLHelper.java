package com.example.alya.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SQLHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "NotesData";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "Notes";
    private static final String KEY_ID = "id";
    private static final String KEY_NOTE = "Note";
    private static final String KEY_DATE = "Date";







    public SQLHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + "("
                        + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + KEY_NOTE + " TEXT,"
                        + KEY_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                        + ")";
        db.execSQL(CREATE_TABLE);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DELETE_TABLE ="DROP TABLE IF EXISTS "+ TABLE_NAME+"";
        db.execSQL(DELETE_TABLE);
        onCreate(db);

    }

    public long  addNote(String note){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NOTE,note);
        long id = db.insert(TABLE_NAME, null, values);
        db.close();
        return id;
    }


    public Note getNote(long id){
        String select_query="SELECT * FROM "+TABLE_NAME+" WHERE id="+id;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(select_query,null);
        Note note=null;
        if(cursor.moveToFirst()) {
            int  note_id =cursor.getInt(cursor.getColumnIndex(KEY_ID));
            String noteName=cursor.getString(cursor.getColumnIndex(KEY_NOTE));
            String date=cursor.getString(cursor.getColumnIndex(KEY_DATE));
            note = new Note(date, noteName,note_id);
        }
        return note;
    }


    public List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + "";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                note.setNote(cursor.getString(cursor.getColumnIndex(KEY_NOTE)));
                note.setDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));

                notes.add(note);
            } while (cursor.moveToNext());
        }

        return notes;
    }












    public void editNote(Note note){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NOTE, note.getNote());
        values.put(KEY_DATE, note.getDate());
        db.update(TABLE_NAME, values, "id=?", new String[]{String.valueOf(note.getId())});
    }


    public void deleteNote(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME,"id=?",new String[]{String.valueOf(id)});



    }
}