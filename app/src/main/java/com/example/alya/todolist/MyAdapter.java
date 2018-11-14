package com.example.alya.todolist;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MyAdapter extends ArrayAdapter<Note> {
    public MyAdapter(@NonNull Context context, @NonNull List<Note> objects) {
        super(context, 0 , objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_row,parent,false);
        TextView date = convertView.findViewById(R.id.date_text);
        TextView noteName = convertView.findViewById(R.id.text2);


        Note note=getItem(position);

        noteName.setText(note.getNote());
        date.setText(formatDate(note.getDate()));


        return convertView;
    }
    private String formatDate(String dateStr) {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = fmt.parse(dateStr);

            SimpleDateFormat fmtOut = new SimpleDateFormat("MMM d");
            return fmtOut.format(date);
        } catch (ParseException e) {

        }

        return "";
    }

}
