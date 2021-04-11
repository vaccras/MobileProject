package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class choixCultureActivity extends AppCompatActivity {
    public static final String PRENOM_KEY = "PRENOM";
    public static final String NOM_KEY = "NOM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_culture);
    }

    public void histoire(View view) {
        Intent intent = new Intent(this, HistActivity.class);
        intent.putExtra(HistActivity.PRENOM_KEY, getIntent().getStringExtra(PRENOM_KEY));
        intent.putExtra(HistActivity.NOM_KEY, getIntent().getStringExtra(NOM_KEY));
        startActivity(intent);
    }

    public void geographie(View view) {
        Intent intent = new Intent(this, Geo_Activity.class);
        intent.putExtra(Geo_Activity.PRENOM_KEY, getIntent().getStringExtra(PRENOM_KEY));
        intent.putExtra(Geo_Activity.NOM_KEY, getIntent().getStringExtra(NOM_KEY));
        startActivity(intent);
    }

    public void aide(View view) {
        Intent intent = new Intent(this, AideActivity.class);
        intent.putExtra(AideActivity.PRENOM_KEY, getIntent().getStringExtra(PRENOM_KEY));
        intent.putExtra(AideActivity.NOM_KEY, getIntent().getStringExtra(NOM_KEY));
        intent.putExtra(AideActivity.TYPE, "histoire");
        startActivity(intent);
    }

    public void quitter(View view) {
        super.finish();
    }
}