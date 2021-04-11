package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class resultatActivity extends AppCompatActivity {
    //recuperation de tout les choix utilisateur
    public static final String REPONSE = "REPONSE";
    public static final String PRENOM_KEY = "PRENOM";
    public static final String NOM_KEY = "NOM";
    public static final String TYPE_KEY = "TYPE";
    public static final String BORNE = "BORNE";

    //stockage des choix
    private String prenom;
    private String nom;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);

        //recuperation des différents choix utilisateurs et de l'utilisateur
        int nbError = Integer.parseInt(getIntent().getStringExtra(REPONSE));
        int borne = Integer.parseInt(getIntent().getStringExtra(BORNE));
        prenom = getIntent().getStringExtra(PRENOM_KEY);
        nom = getIntent().getStringExtra(NOM_KEY);
        type = getIntent().getStringExtra(TYPE_KEY);

        // mise à jour affichage d'encouragement en fonction du nombre d'erreur par rapport au nombre de question
        TextView fel = findViewById(R.id.felicitation);
        if (!prenom.equals("anonyme") && !nom.equals("anonyme")){ // si l'utilisateur utilise un compte
            if(nbError < borne/3){
                fel.setText("Felicitation " + prenom + " !!");
            }else if (nbError > borne/3 && nbError < borne/3*2){
                fel.setText("Continue comme sa " + prenom + " !!");
            }else{
                fel.setText("Ne te décourage pas " + prenom + " !!");
            }
        } else{ // si il joue en anonyme
            if(nbError < borne/3){
                fel.setText("Felicitation !!");
            }else if (nbError > borne/3 && nbError < borne/3*2){
                fel.setText("Continue comme sa !!");
            }else{
                fel.setText("Ne te décourage pas !!");
            }
        }

        // mise à jour affichage du nombre d'erreur sur le nombre de question
        TextView viewErreur = findViewById(R.id.nbErreur);
        if(type.equals("geo")){
            if (!prenom.equals("anonyme") && !nom.equals("anonyme")){
                fel.setText("Felicitation " + prenom + " !!");
            }else{
                fel.setText("Felicitation !!");
            }
            viewErreur.setText("Votre meilleur combot est de " + nbError + " pour " + borne + " questions !");
        }else {
            viewErreur.setText("Vous avez fait " + nbError + " erreur sur " + borne + " !");
        }
    }

    public void rejouer(View view) {
        if (type.equals("x")){
            // revient au choix de la multiplication
            Intent intent = new Intent(this, TableMultipActivity.class);
            intent.putExtra(TableMultipActivity.PRENOM_KEY, prenom);
            intent.putExtra(TableMultipActivity.NOM_KEY, nom);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //permet d'eviter l'empilement
            startActivity(intent);
        }else if(type.equals("=")){
            // relance une partie comparaison
            Intent intent = new Intent(this, ComparaisonActivity.class);
            intent.putExtra(ComparaisonActivity.PRENOM_KEY, getIntent().getStringExtra(PRENOM_KEY));
            intent.putExtra(ComparaisonActivity.NOM_KEY, getIntent().getStringExtra(NOM_KEY));
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //permet d'eviter l'empilement
            startActivity(intent);
        }else if (type.equals("geo")) {
            // relance une partie de geographie !
            Intent intent = new Intent(this, Geo_Activity.class);
            intent.putExtra(Geo_Activity.PRENOM_KEY, getIntent().getStringExtra(PRENOM_KEY));
            intent.putExtra(Geo_Activity.NOM_KEY, getIntent().getStringExtra(NOM_KEY));
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //permet d'eviter l'empilement
            startActivity(intent);
        }else{
            // revient au choix de la difficultés
            Intent intent = new Intent(this, BorneActivity.class);
            intent.putExtra(BorneActivity.PRENOM_KEY, getIntent().getStringExtra(PRENOM_KEY));
            intent.putExtra(BorneActivity.NOM_KEY, getIntent().getStringExtra(NOM_KEY));
            intent.putExtra(BorneActivity.TYPE, type);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //permet d'eviter l'empilement
            startActivity(intent);
        }

    }

    public void acceuil(View view) {
        // revient au choix du type d'exercice
        Intent intent = new Intent(this, typeActivity.class);
        intent.putExtra(typeActivity.PRENOM_KEY, prenom);
        intent.putExtra(typeActivity.NOM_KEY, nom);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //permet d'eviter l'empilement
        startActivity(intent);
    }
}