package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BorneActivity extends AppCompatActivity {
    // recuperation de l'utilisateur et du type (+/-)
    public static final String PRENOM_KEY = "PRENOM";
    public static final String NOM_KEY = "NOM";
    public static final String TYPE = "TYPE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borne);
    }

    public void lanceTables(View view) {
        // recuperation du choix utilisateur pour la difficulté
        String type = "";
        if (view.getId() == R.id.entier) {
            type = "entier";
        } else if (view.getId() == R.id.dizaine) {
            type = "dizaine";
        } else if (view.getId() == R.id.centaine) {
            type = "centaine";
        } else if (view.getId() == R.id.infini) {
            type = "infini";
        }

        // lancement du calcul en fonction du choix
        Intent intent = new Intent(this, CalculsActivity.class);
        intent.putExtra(CalculsActivity.PRENOM_KEY, getIntent().getStringExtra(PRENOM_KEY));
        intent.putExtra(CalculsActivity.NOM_KEY, getIntent().getStringExtra(NOM_KEY));
        intent.putExtra(CalculsActivity.TYPE, getIntent().getStringExtra(TYPE));
        intent.putExtra(CalculsActivity.DIFFICTULTE, type);
        intent.putExtra(CalculsActivity.TABLE_CHOISIE,String.valueOf(-1));
        startActivity(intent);
    }
    public void retour(View view) {super.finish();}
}