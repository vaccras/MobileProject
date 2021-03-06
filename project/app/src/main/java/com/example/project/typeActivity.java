package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class typeActivity extends AppCompatActivity {
    //recuperation information utilisateur
    public static final String PRENOM_KEY = "PRENOM";
    public static final String NOM_KEY = "NOM";

    private String prenom ="anonyme";
    private String nom ="anonyme";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);

        //recuperation du prenom et du nom de l'utilisateur courant
        prenom = getIntent().getStringExtra(PRENOM_KEY);
        nom = getIntent().getStringExtra(NOM_KEY);

        // rendre le bouton profile accessible si l'utilisateur n'est pas anonyme
        if (!prenom.equals("anonyme") && !nom.equals("anonyme")){
            Button profil = findViewById(R.id.profil);
            profil.setVisibility(View.VISIBLE);
        }
    }

    public void MathActivity(View view) {
        // lancer l'activity math
        Intent intent = new Intent(this, choixMathActivity.class);
        intent.putExtra(choixMathActivity.PRENOM_KEY, prenom);
        intent.putExtra(choixMathActivity.NOM_KEY, nom);
        startActivity(intent);

    }

    public void CultureActivity(View view) {
        // lancer l'activity culture
        Intent intent = new Intent(this, choixCultureActivity.class);
        intent.putExtra(choixCultureActivity.PRENOM_KEY, prenom);
        intent.putExtra(choixCultureActivity.NOM_KEY, nom);
        startActivity(intent);
    }

    public void annuler(View view) {
        super.finish();
    }

    public void profil(View view) {
        // lancer l'activity profile
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra(ProfileActivity.PRENOM_KEY, prenom);
        intent.putExtra(ProfileActivity.NOM_KEY, nom);
        startActivity(intent);
    }
}