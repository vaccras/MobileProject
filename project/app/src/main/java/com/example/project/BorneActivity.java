package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BorneActivity extends AppCompatActivity {
    public static final String PRENOM_KEY = "PRENOM";
    public static final String NOM_KEY = "NOM";
    public static final String TYPE = "TYPE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borne);
    }

    public void entier(View view) {
        Intent intent = new Intent(this, As_CalculsActivity.class);
        intent.putExtra(As_CalculsActivity.PRENOM_KEY, getIntent().getStringExtra(PRENOM_KEY));
        intent.putExtra(As_CalculsActivity.NOM_KEY, getIntent().getStringExtra(NOM_KEY));
        intent.putExtra(As_CalculsActivity.TYPE, getIntent().getStringExtra(TYPE));
        intent.putExtra(As_CalculsActivity.DIFFICTULTE, "entier");
        startActivity(intent);
    }

    public void dizaine(View view) {
        Intent intent = new Intent(this, As_CalculsActivity.class);
        intent.putExtra(As_CalculsActivity.PRENOM_KEY, getIntent().getStringExtra(PRENOM_KEY));
        intent.putExtra(As_CalculsActivity.NOM_KEY, getIntent().getStringExtra(NOM_KEY));
        intent.putExtra(As_CalculsActivity.TYPE, getIntent().getStringExtra(TYPE));
        intent.putExtra(As_CalculsActivity.DIFFICTULTE, "dizaine");
        startActivity(intent);
    }

    public void centaine(View view) {
        Intent intent = new Intent(this, As_CalculsActivity.class);
        intent.putExtra(As_CalculsActivity.PRENOM_KEY, getIntent().getStringExtra(PRENOM_KEY));
        intent.putExtra(As_CalculsActivity.NOM_KEY, getIntent().getStringExtra(NOM_KEY));
        intent.putExtra(As_CalculsActivity.TYPE, getIntent().getStringExtra(TYPE));
        intent.putExtra(As_CalculsActivity.DIFFICTULTE, "centaine");
        startActivity(intent);
    }


    public void retour(View view) {super.finish();}
}