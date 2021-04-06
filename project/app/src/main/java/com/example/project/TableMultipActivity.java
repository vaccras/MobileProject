package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;

public class TableMultipActivity extends AppCompatActivity {
    // recuperation de l'utilisateur
    public static final String PRENOM_KEY = "PRENOM";
    public static final String NOM_KEY = "NOM";

    // recuperation des informations de la view
    private NumberPicker table_choisie;
    private String table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_multip);

        // mise en place du numberPicker pour le choix des tables de multiplications (entre 1 et 12)
        table_choisie = findViewById(R.id.TM_picker);
        table_choisie.setMinValue(1);
        table_choisie.setMaxValue(12);
    }

    NumberPicker.OnValueChangeListener onValueListener = new NumberPicker.OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

        }
    };

    public void TM_lanceTables(View view) {
        // recuperation du choix utilisateur
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

        // affichage de la vue calcul pour la multiplication
        Intent intent = new Intent(this, CalculsActivity.class);
        intent.putExtra(CalculsActivity.TABLE_CHOISIE,table);
        intent.putExtra(CalculsActivity.TYPE, "x");
        intent.putExtra(CalculsActivity.DIFFICTULTE, type);
        intent.putExtra(CalculsActivity.PRENOM_KEY, getIntent().getStringExtra(PRENOM_KEY));
        intent.putExtra(CalculsActivity.NOM_KEY, getIntent().getStringExtra(NOM_KEY));
        startActivity(intent);
    }


    public void annuler(View view) {super.finish();}
}