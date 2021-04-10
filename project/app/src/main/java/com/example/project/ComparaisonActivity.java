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
import com.example.project.math.calcul;
import com.example.project.math.comparaison;

import java.util.ArrayList;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class ComparaisonActivity extends AppCompatActivity {
    // recuperation de l'utilisateur
    public static final String PRENOM_KEY = "PRENOM";
    public static final String NOM_KEY = "NOM";

    //choix utilisateur en local
    private String prenom;
    private String nom;

    // sauvegarde
    public static final String STATE_CALCUL = "calcul";

    //utilitaire pour la creation d'une vue dynamique
    public TextView calcul;
    public TextView nbQ;

    //classe comparaison permettant de faciliter les calculs
    private comparaison op;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparaison);

        //Récupération de l'utilisateur courant
        prenom = getIntent().getStringExtra(PRENOM_KEY);
        nom = getIntent().getStringExtra(NOM_KEY);

        // Check whether we're recreating a previously destroyed instance
        if (savedInstanceState != null) {
            // Restore value of members from saved state
            op = (comparaison) savedInstanceState.getParcelable(STATE_CALCUL);

        } else {
            //creation de l'operation en fonction de la table choisis et de l'operateur
            op = new comparaison(0, 12);
        }

        //recuperation de l'objet calcul à modifier
        calcul = findViewById(R.id.comparaison);
        nbQ = findViewById(R.id.nbQuestion);

        //permet de mettre a jour l'affichage une fois la réponse obtenue
        onMaj();

    }

    public void onMaj() {
        //si on a pas fini l'iteration sur le nombre de calcul souhaiter
        if (op.getIncrement() < op.getBorneSup()) {
            int increment = op.getIncrement();
            //affichage du calcul sur la vue
            calcul.setText(String.valueOf(op.getOperande1(increment)) + " " + op.getOp(increment) + " " + String.valueOf(op.getOperande2(increment)));

            nbQ.setText("question : " + String.valueOf((increment+1)));

            //cas ou l'utilisateur clique sur le bouton vrai
            Button btnVrai = findViewById(R.id.btnVrai);
            btnVrai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //stockage de la réponse utilisateur
                    op.setRepUser(increment, true);
                    op.setIncrement();
                    onMaj(); //boucle sur la même fonction
                }
            });
            //cas ou l'utilisateur clique sur le bouton faux
            Button btnFaux = findViewById(R.id.btnFaux);
            btnFaux.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //stockage de la réponse utilisateur
                    op.setRepUser(increment, false);
                    op.setIncrement();
                    onMaj(); //boucle sur la même fonction
                }
            });
        } else {
            int resu = op.getAllResult();

            // Récupération du DatabaseClient
            DatabaseClient mDb = DatabaseClient.getInstance(getApplicationContext());

            //faire une AsyncTask
            // Classe asynchrone permettant de récupérer des taches et de mettre à jour le listView de l'activité
            class UpdateCompte extends AsyncTask<Void, Void, Compte> {
                @Override
                protected Compte doInBackground(Void... voids) {
                    //recuperation du compte courant
                    Compte profile = mDb.getAppDatabase().compteDao().findByName(prenom, nom);

                    // variable du résultat de calcul
                    int resultat;

                    if (profile.getComparaison() == -1) {
                        // pour la première fois ou l'utilisateur enregistre des données
                        resultat = (12 - resu) * 20 / 12;
                    } else {
                        // calcul nouvelle moyenne en fonction du résultat de l'utilisateur
                        resultat = (profile.getComparaison() + (12 - resu) * 20 / 12) / 2;
                    }

                    //mise à jour du compte
                    profile.setComparaison(resultat);
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

            //affichage de la vue résultat
            Intent intent = new Intent(this, resultatMathActivity.class);
            intent.putExtra(resultatMathActivity.REPONSE, String.valueOf(resu));
            intent.putExtra(resultatMathActivity.PRENOM_KEY, prenom);
            intent.putExtra(resultatMathActivity.NOM_KEY, nom);
            intent.putExtra(resultatMathActivity.TYPE_KEY, "=");
            intent.putExtra(resultatMathActivity.BORNE, String.valueOf("12"));
            startActivity(intent);
        }
    }

    public void quitter(View view) {super.finish();}

    @Override
    public void onSaveInstanceState(Bundle outState) {

        // Save the user's current game state
        outState.putParcelable(STATE_CALCUL, op);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(outState);
    }
}
