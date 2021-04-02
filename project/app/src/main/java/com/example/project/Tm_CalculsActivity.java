package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.project.db.Compte;
import com.example.project.db.DatabaseClient;
import com.example.project.math.operation;

import java.util.ArrayList;

public class Tm_CalculsActivity extends AppCompatActivity {
    // recuperation de l'utilisateur
    public static final String PRENOM_KEY = "PRENOM";
    public static final String NOM_KEY = "NOM";

    //recuperation des choix utilisateurs
    public static final String TABLE_CHOISIE = "TABLE";
    public static final String TYPE = "TYPE";

    LinearLayout linear;
    //choix utilisateur en local
    private int table_choisie = 0;
    private String type;
    private String prenom;
    private String nom;
    private int increment =0;

    //utilitaire pour la creation d'une vue dynamique
    public TextView calcul;
    public EditText resultat;

    //Tableaux pour ranger les réponses de l'utilisateurs
    private ArrayList<Integer> repUser = new ArrayList<>();

    //classe operation permettant de faciliter les calculs
    private operation op;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tm__calculs);

        //Récupération de la table choisie par l'utilisateur et du type de question
        table_choisie = Integer.parseInt(getIntent().getStringExtra(TABLE_CHOISIE));
        prenom = getIntent().getStringExtra(PRENOM_KEY);
        nom = getIntent().getStringExtra(NOM_KEY);
        type = getIntent().getStringExtra(TYPE);

        //creation de l'operation en fonction de la table choisis et de l'operateur
        op = new operation(table_choisie,"x",1,12, type);

        calcul = findViewById(R.id.calcul);
        resultat = findViewById(R.id.resultat);

        //permet de mettre a jour l'affichage une fois la réponse obtenue
        onMaj();

    }

    public void onMaj(){
        //si on a pas fini l'iteration sur le nombre de calcul souhaiter
        if(increment < op.getBorneSup()){
            calcul.setText(String.valueOf(op.getOperande2(increment)) + "x" + String.valueOf(op.getOperande1()) + " = ");
            resultat.setText("");
            Button valider = findViewById(R.id.valider);
            valider.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    increment++;
                    int val;
                    try {
                        val = Integer.parseInt(String.valueOf(resultat.getText()));
                    } catch (Exception e){
                        val = -1;
                    }
                    repUser.add(val);
                    onMaj();
                }
            });
        }else{
            int resu = op.getAllResult(repUser);

            // Récupération du DatabaseClient
            DatabaseClient mDb = DatabaseClient.getInstance(getApplicationContext());

            //faire une AsyncTask
            // Classe asynchrone permettant de récupérer des taches et de mettre à jour le listView de l'activité
            class UpdateCompte extends AsyncTask<Void, Void, Compte> {

                @Override
                protected Compte doInBackground(Void... voids) {
                    //recuperation du compte courant
                    Compte profile = mDb.getAppDatabase().compteDao().findByName(prenom, nom);
                    //mise a jour du résultat de calcul
                    int resultat;

                    if (profile.getCalcul() == -1){
                        resultat = (12-resu)*20/12; // pour la première fois ou l'utilisateur enregistre des données
                    }else {
                        resultat = (profile.getCalcul() + (12-resu)*20/12) /2;
                    }

                    profile.setCalcul(resultat);
                    //mise à jour du compte
                    mDb.getAppDatabase().compteDao().update(profile);
                    return profile;
                }

                @Override
                protected void onPostExecute(Compte profile) {
                    super.onPostExecute(profile);
                }
            }

            // IMPORTANT bien penser à executer la demande asynchrone
            // Création d'un objet de type UpdateCompte et execution de la demande asynchrone uniquement si l'utilisateur n'est pas en anonyme
            if (!prenom.equals("anonyme") && !nom.equals("anonyme")) {
                UpdateCompte gc = new UpdateCompte();
                gc.execute();
            }

            Intent intent = new Intent(this, resultatMathActivity.class);
            intent.putExtra(resultatMathActivity.REPONSE, String.valueOf(resu));
            intent.putExtra(resultatMathActivity.PRENOM_KEY, prenom);
            intent.putExtra(resultatMathActivity.NOM_KEY, nom);
            intent.putExtra(resultatMathActivity.TYPE_KEY, "x");
            startActivity(intent);
        }
    }

    public void quitter(View view) {super.finish();}
}

