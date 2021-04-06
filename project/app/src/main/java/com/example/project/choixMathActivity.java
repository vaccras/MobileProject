package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class choixMathActivity extends AppCompatActivity {
    //recuperation des informations utilisateur
    public static final String PRENOM_KEY = "PRENOM";
    public static final String NOM_KEY = "NOM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_math);

        //affichage du bouton comparaison
        Button btn = findViewById(R.id.comp);
        btn.setText("><");
    }

    public void quitter(View view) {
        super.finish();
    }

    public void comp(View view) {
        //intent view comparaison
        Intent intent = new Intent(this, ComparaisonActivity.class);
        intent.putExtra(ComparaisonActivity.PRENOM_KEY, getIntent().getStringExtra(PRENOM_KEY));
        intent.putExtra(ComparaisonActivity.NOM_KEY, getIntent().getStringExtra(NOM_KEY));
        startActivity(intent);
    }

    public void sous(View view) {
        //intent view soustraction
        Intent intent = new Intent(this, BorneActivity.class);
        intent.putExtra(BorneActivity.PRENOM_KEY, getIntent().getStringExtra(PRENOM_KEY));
        intent.putExtra(BorneActivity.NOM_KEY, getIntent().getStringExtra(NOM_KEY));
        intent.putExtra(BorneActivity.TYPE, "-");
        startActivity(intent);
    }

    public void add(View view) {
        //intent view addition
        Intent intent = new Intent(this, BorneActivity.class);
        intent.putExtra(BorneActivity.PRENOM_KEY, getIntent().getStringExtra(PRENOM_KEY));
        intent.putExtra(BorneActivity.NOM_KEY, getIntent().getStringExtra(NOM_KEY));
        intent.putExtra(BorneActivity.TYPE, "+");
        startActivity(intent);
    }

    public void mult(View view) {
        //intent view mutliplication
        Intent intent = new Intent(this, TableMultipActivity.class);
        intent.putExtra(TableMultipActivity.PRENOM_KEY, getIntent().getStringExtra(PRENOM_KEY));
        intent.putExtra(TableMultipActivity.NOM_KEY, getIntent().getStringExtra(NOM_KEY));
        startActivity(intent);
    }

    public void aide(View view) {
        //intent view aide
        Intent intent = new Intent(this, AideActivity.class);
        intent.putExtra(AideActivity.PRENOM_KEY, getIntent().getStringExtra(PRENOM_KEY));
        intent.putExtra(AideActivity.NOM_KEY, getIntent().getStringExtra(NOM_KEY));
        intent.putExtra(AideActivity.TYPE, "math");
        startActivity(intent);
    }
}