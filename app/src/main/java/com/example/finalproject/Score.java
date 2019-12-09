package com.example.finalproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;

import java.io.FileNotFoundException;
import java.io.OutputStream;

public class Score extends Dialog {
    private ListView listView;
    private ArrayAdapter adapter;
    private Context context;
    private Button back;
    private Button clear;

    public Score(@NonNull Context context) {
        super(context, R.style.Theme_AppCompat_Dialog);
        this.context = context;
        setContentView(R.layout.activity_score);
        adapter = new ArrayAdapter<String>(getContext(), R.layout.adapter);
        listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        back = findViewById(R.id.btn_DialogScore_back);
        clear = findViewById(R.id.btn_DialogScore_clear);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext()).setTitle("ALERT!").setMessage("Sure to Remove ALl？")
                        .setPositiveButton("Yes", new OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                adapter.clear();
                                adapter.notifyDataSetChanged();
                                ScoreData.scoreData.clear();
                            }
                        }).setNegativeButton("Cancel", null).show();
            }
        });
    }

    /** add string data.*/
    public void addListData(String data) {
        adapter.add(data);
    }
}
