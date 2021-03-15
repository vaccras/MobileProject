package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class compteActivity extends AppCompatActivity {
    //public final static int ANONYME_REQUEST = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compte);
    }

    public void CreerCompte(View view) {
        //creation d'un nouveau compte utilisateur
    }

    public void Annuler(View view) {
        //retour a la page d'accueil
        setResult(RESULT_OK);
        super.finish();
    }

    public void AnonymeActivity(View view) {
        //jouer en anonyme -> pas de compte de score accés restreint ?
        Intent intent = new Intent(this, typeActivity.class);
        intent.putExtra(typeActivity.ANONYME, "1");
        //startActivityForResult(intent, ANONYME_REQUEST);
        startActivity(intent);
    }
}