package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import java.util.Collections;

import java.util.ArrayList;

public class TableMultipActivity extends AppCompatActivity {
    public static final String PRENOM_KEY = "PRENOM";
    public static final String NOM_KEY = "NOM";

    NumberPicker table_choisie;
    String table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_multip);


        table_choisie = findViewById(R.id.TM_picker);
        table_choisie.setMinValue(1);
        table_choisie.setMaxValue(12);

        //table_choisie.setOnValueChangedListener(this::onValueChange);
    }

    NumberPicker.OnValueChangeListener onValueListener = new NumberPicker.OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

        }
    };

    public void TM_lanceTables(View view) {
        Intent intent = new Intent(this, Tm_CalculsActivity.class);
        String type = "normal";
        if (view.getId()==R.id.TM_desordre){
            type = "shuffle";
            table = String.valueOf(table_choisie.getValue());
        } else if (view.getId()==R.id.TM_infini){
            type = "infini";
            table = "1";
        } else {
            table = String.valueOf(table_choisie.getValue());
        }

        intent.putExtra(Tm_CalculsActivity.TABLE_CHOISIE,table);
        intent.putExtra(Tm_CalculsActivity.TYPE, type);
        intent.putExtra(Tm_CalculsActivity.PRENOM_KEY, getIntent().getStringExtra(PRENOM_KEY));
        intent.putExtra(Tm_CalculsActivity.NOM_KEY, getIntent().getStringExtra(NOM_KEY));
        startActivity(intent);
    }


}