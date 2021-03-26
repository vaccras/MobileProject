package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.project.db.DatabaseClient;

public class HistActivity extends AppCompatActivity {

    private DatabaseClient mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hist);



        mDb = DatabaseClient.getInstance(getApplicationContext());

        TextView intitu = findViewById(R.id.HIST_intitule);
        //intitu.setText(mDb.getAppDatabase().histoireDao());












    }
}