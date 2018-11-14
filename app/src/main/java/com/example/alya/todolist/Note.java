package com.example.alya.todolist;


public class Note {
    private String date;
    private String note;
    private int id;


    public Note() {
    }

    public Note(String date, String note) {
        this.date = date;
        this.note = note;
    }

    public Note(String date, String note, int id) {
        this.date = date;
        this.note = note;
        this.id = id;
    }

    public Note(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public String getNote() {
        return note;
    }

    public int getId() {
        return id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setId(int id) {
        this.id = id;
    }
}