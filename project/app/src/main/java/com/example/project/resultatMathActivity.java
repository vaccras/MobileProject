package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class resultatMathActivity extends AppCompatActivity {
    public static final String REPONSE = "REPONSE";
    public static final String PRENOM_KEY = "PRENOM";
    public static final String NOM_KEY = "NOM";

    String prenom;
    String nom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat_math);

        int nbError = Integer.parseInt(getIntent().getStringExtra(REPONSE));
        prenom = getIntent().getStringExtra(PRENOM_KEY);
        nom = getIntent().getStringExtra(NOM_KEY);

        if (!prenom.equals("anonyme") && !nom.equals("anonyme")){
            TextView fel = findViewById(R.id.felicitation);
            fel.setText("Felicitation " + prenom + " !!");
        }
        TextView viewErreur = findViewById(R.id.nbErreur);
        viewErreur.setText("Vous avez fait " + nbError + " erreur.");
    }

    public void rejouer(View view) {
        Intent intent = new Intent(this, TableMultipActivity.class);
        intent.putExtra(TableMultipActivity.PRENOM_KEY, prenom);
        intent.putExtra(TableMultipActivity.NOM_KEY, nom);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //permet d'eviter l'empilement
        startActivity(intent);
    }

    public void acceuil(View view) {
        Intent intent = new Intent(this, typeActivity.class);
        intent.putExtra(typeActivity.PRENOM_KEY, prenom);
        intent.putExtra(typeActivity.NOM_KEY, nom);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //permet d'eviter l'empilement
        startActivity(intent);
    }
}