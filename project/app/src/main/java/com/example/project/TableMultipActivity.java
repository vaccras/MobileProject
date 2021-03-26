package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import java.util.Collections;
import android.widget.NumberPicker;

import java.util.ArrayList;

public class TableMultipActivity extends AppCompatActivity {

    NumberPicker table_choisie;

    ArrayList<Integer> reponsesAttendu = new ArrayList<>();



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

        Intent intent = new Intent(this, Tm_Calculs.class);

        for (int i =1 ; i<13 ; i++){
            reponsesAttendu.add(i*(int)table_choisie.getValue());
        }
        if (view.getId()==R.id.TM_desordre){
            Collections.shuffle(reponsesAttendu);
        }

        intent.putExtra(Tm_Calculs.TABLE_CHOISIE,String.valueOf(table_choisie.getValue()));
        intent.putExtra(Tm_Calculs.REPONSE, reponsesAttendu);
        startActivity(intent);
    }


}