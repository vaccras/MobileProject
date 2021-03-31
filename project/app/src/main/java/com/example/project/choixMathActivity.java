package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class choixMathActivity extends AppCompatActivity {
    public static final String PRENOM_KEY = "PRENOM";
    public static final String NOM_KEY = "NOM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_math);
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
        Intent intent = new Intent(this, As_CalculsActivity.class);
        intent.putExtra(As_CalculsActivity.PRENOM_KEY, getIntent().getStringExtra(PRENOM_KEY));
        intent.putExtra(As_CalculsActivity.NOM_KEY, getIntent().getStringExtra(NOM_KEY));
        intent.putExtra(As_CalculsActivity.TYPE, "-");
        startActivity(intent);
    }

    public void add(View view) {
        //intent view addition
        Intent intent = new Intent(this, As_CalculsActivity.class);
        intent.putExtra(As_CalculsActivity.PRENOM_KEY, getIntent().getStringExtra(PRENOM_KEY));
        intent.putExtra(As_CalculsActivity.NOM_KEY, getIntent().getStringExtra(NOM_KEY));
        intent.putExtra(As_CalculsActivity.TYPE, "+");
        startActivity(intent);
    }

    public void mult(View view) {
        Intent intent = new Intent(this, TableMultipActivity.class);
        intent.putExtra(TableMultipActivity.PRENOM_KEY, getIntent().getStringExtra(PRENOM_KEY));
        intent.putExtra(TableMultipActivity.NOM_KEY, getIntent().getStringExtra(NOM_KEY));
        startActivity(intent);
    }
}