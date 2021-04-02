package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.project.db.Compte;
import com.example.project.db.DatabaseClient;
import com.example.project.math.comparaison;

import java.util.ArrayList;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class ComparaisonActivity extends AppCompatActivity {
    // recuperation de l'utilisateur
    public static final String PRENOM_KEY = "PRENOM";
    public static final String NOM_KEY = "NOM";

    LinearLayout linear;

    //choix utilisateur en local
    private String prenom;
    private String nom;
    private int increment = 0;

    //utilitaire pour la creation d'une vue dynamique
    public TextView calcul;

    //Tableaux pour ranger les réponses de l'utilisateurs
    private ArrayList<Boolean> repUser = new ArrayList<>();

    //classe operation permettant de faciliter les calculs
    private comparaison op;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparaison);

        //Récupération de la table choisie par l'utilisateur et du type de question
        prenom = getIntent().getStringExtra(PRENOM_KEY);
        nom = getIntent().getStringExtra(NOM_KEY);

        //creation de l'operation en fonction de la table choisis et de l'operateur
        op = new comparaison(1, 12);

        //recuperation de l'objet calcul à modifier
        calcul = findViewById(R.id.comparaison);

        //permet de mettre a jour l'affichage une fois la réponse obtenue
        onMaj();

    }

    public void onMaj() {
        //si on a pas fini l'iteration sur le nombre de calcul souhaiter
        if (increment < op.getBorneSup()) {
            calcul.setText(String.valueOf(op.getOperande1(increment)) + " " + op.getOp(increment) + " " + String.valueOf(op.getOperande2(increment)));
            //cas ou l'utilisateur clique sur le bouton vrai
            Button btnVrai = findViewById(R.id.btnVrai);
            Log.i("TAG", String.valueOf(btnVrai));
            btnVrai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    increment++;
                    repUser.add(true);
                    onMaj();
                }
            });
            //cas ou l'utilisateur clique sur le bouton faux
            Button btnFaux = findViewById(R.id.btnFaux);
            btnFaux.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    increment++;
                    repUser.add(false);
                    onMaj();
                }
            });
        } else {
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

                    if (profile.getComparaison() == -1) {
                        resultat = (12 - resu) * 20 / 12; // pour la première fois ou l'utilisateur enregistre des données
                    } else {
                        resultat = (profile.getComparaison() + (12 - resu) * 20 / 12) / 2;
                    }

                    profile.setComparaison(resultat);
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
            intent.putExtra(resultatMathActivity.TYPE_KEY, "=");
            intent.putExtra(resultatMathActivity.BORNE, String.valueOf(increment));
            startActivity(intent);
        }
    }

    public void quitter(View view) {super.finish();}
}
