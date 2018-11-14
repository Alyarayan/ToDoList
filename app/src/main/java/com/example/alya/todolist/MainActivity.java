package com.example.alya.todolist;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    FloatingActionButton floatingActionButton;
    ArrayList<Note> notesList;
    MyAdapter myAdapter;
    SQLHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        floatingActionButton = findViewById(R.id.fab_btn);
        listView = findViewById(R.id.list);
        helper = new SQLHelper(this);
        notesList = new ArrayList<>();

        notesList.addAll(helper.getAllNotes());

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNoteDialog();


            }
        });
        myAdapter = new MyAdapter(MainActivity.this, notesList);

        listView.setAdapter(myAdapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showActionsDialog(position);
                return true;
            }
        });




    }


    private void showActionsDialog(final int position) {
        String items[]={"Edit","Delete"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose option");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {



                } else {

                }
            }
        });
        builder.show();
    }


    private void createNote(String note) {
        long id = helper.addNote(note);
        Note n = helper.getNote(id);

        notesList.add(0, n);
        myAdapter.notifyDataSetChanged();


    }
    private void showNoteDialog() {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getApplicationContext());
        View view = layoutInflaterAndroid.inflate(R.layout.custom_alert_dialog, null);

        AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
        builder.setView(view);

        final EditText inputNote = view.findViewById(R.id.note_edit_txt);
        TextView dialogTitle = view.findViewById(R.id.note_txt);
        dialogTitle.setText("New Note");
        builder
                .setCancelable(false)
                .setPositiveButton("save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        createNote(inputNote.getText().toString());
                    }
                })
                .setNegativeButton("cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.dismiss();
                            }
                        });

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }


}