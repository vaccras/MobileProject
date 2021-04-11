package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.project.db.Compte;
import com.example.project.db.DatabaseClient;
import com.example.project.math.calcul;

import java.util.ArrayList;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class CalculsActivity extends AppCompatActivity {
    // recuperation de l'utilisateur si il n'est pas en anonyme
    public static final String PRENOM_KEY = "PRENOM";
    public static final String NOM_KEY = "NOM";

    //recuperation des choix utilisateurs
    public static final String TYPE = "TYPE";
    public static final String DIFFICTULTE = "DIFFICTULTE";
    public static final String TABLE_CHOISIE = "TABLE";

    //choix utilisateur en local
    private String type;
    private String difficulte;
    private String prenom;
    private String nom;
    private int increment = 0;
    private int table_choisie;

    //utilitaire pour la creation d'une vue dynamique
    public TextView calcul;
    public TextView nbQ;
    public EditText resultat;
    public TextView titleTimer;

    //Tableaux pour ranger les réponses de l'utilisateurs
    private ArrayList<Integer> repUser = new ArrayList<>();

    //classe operation permettant de faciliter les calculs
    private com.example.project.math.calcul op;

    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculs);

        //Récupération de la table choisie par l'utilisateur et du type de question
        prenom = getIntent().getStringExtra(PRENOM_KEY);
        nom = getIntent().getStringExtra(NOM_KEY);
        type = getIntent().getStringExtra(TYPE);
        difficulte = getIntent().getStringExtra(DIFFICTULTE);
        table_choisie = Integer.parseInt(getIntent().getStringExtra(TABLE_CHOISIE));

        //creation de l'operation en fonction de la table choisis et de l'operateur
        op = new calcul(type, 1, 12, difficulte, table_choisie);

        calcul = findViewById(R.id.calcul);
        resultat = findViewById(R.id.resultat);
        nbQ = findViewById(R.id.nbQuestion);

        if(difficulte.equals("infini")){
            //recuperation du timer et rend visible
            titleTimer = findViewById(R.id.timer);
            titleTimer.setVisibility(View.VISIBLE);

            //mise en place du timer
            timer = new CountDownTimer(60000, 1000) { //40000 milli seconds is total time, 1000 milli seconds is time interval
                public void onTick(long millisUntilFinished) {
                    long inSecond = millisUntilFinished/1000;
                    long min = 0;
                    long sec = 0;
                    if(inSecond>60){
                        min = (int)(inSecond/60);
                        sec = (int)(inSecond - min *60);
                    }else{
                        sec = inSecond;
                    }

                    titleTimer.setText(String.valueOf(min) + ":"+String.valueOf(sec));
                }
                public void onFinish() {
                    titleTimer.setText("FINIT");
                    result();
                }
            }.start();
        }

        //permet de mettre a jour l'affichage une fois la réponse obtenue
        onMaj();

    }

    public void onMaj() {
        //si on a pas fini l'iteration sur le nombre de calcul souhaiter
        if (increment < op.getBorneSup()) {
            // création des deux opérandes à afficher
            int un = max(op.getOperande1(increment), op.getOperande2(increment));
            int deux = min(op.getOperande1(increment), op.getOperande2(increment));
            //affichage du calcul et du resultat vide
            calcul.setText(String.valueOf(un) + type + String.valueOf(deux) + " = ");
            nbQ.setText("question : " + String.valueOf((increment+1)));

            resultat.setText("");
            //mise en place du bouton valider
            Button valider = findViewById(R.id.valider);
            valider.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    increment++;
                    int val;
                    try {
                        //récupération du résultat si celui-ci à était rempli avant de valider
                        val = Integer.parseInt(String.valueOf(resultat.getText()));
                    } catch (Exception e) {
                        val = -1;
                    }
                    //stockage de la réponse utilisateur
                    repUser.add(val);
                    onMaj();
                }
            });
        } else {
            //calcul du résultat
            result();
        }
    }

    public void quitter(View view) {
        //si le mode choisis est infini, on arréte le timer avant de terminer l'activity
        if(difficulte.equals("infini")){
            timer.cancel();
        }
        super.finish();
    }

    public void result(){
        //calcul du résultat en fonction des réponses utilisateurs
        int resu = op.getAllResult(repUser, increment);

        // Récupération du DatabaseClient
        DatabaseClient mDb = DatabaseClient.getInstance(getApplicationContext());

        //faire une AsyncTask
        // Classe asynchrone permettant de récupérer un compte et de le mettre à jour en fonction du resultat à l'ex
        class UpdateCompte extends AsyncTask<Void, Void, Compte> {

            @Override
            protected Compte doInBackground(Void... voids) {
                //recuperation du compte courant
                Compte profile = mDb.getAppDatabase().compteDao().findByName(prenom, nom);

                //mise a jour du résultat de calcul
                int resultat;

                if (profile.getCalcul() == -1) {
                    // pour la première fois ou l'utilisateur enregistre des données
                    resultat = (increment - resu) * 20 / increment;
                } else {
                    //mise à jour moyenne en fonction des nombres de bonnes réponses par rapport au nombre de réponses total raporté sur 20
                    resultat = (profile.getCalcul() + (increment - resu) * 20 / increment) / 2;
                }

                //mise à jour du compte
                profile.setCalcul(resultat);
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
        Intent intent = new Intent(this, resultatActivity.class);
        intent.putExtra(resultatActivity.REPONSE, String.valueOf(resu));
        intent.putExtra(resultatActivity.PRENOM_KEY, prenom);
        intent.putExtra(resultatActivity.NOM_KEY, nom);
        intent.putExtra(resultatActivity.TYPE_KEY, type);
        intent.putExtra(resultatActivity.BORNE, String.valueOf(increment));
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        //Important sinon le timer (autre thread) continue et relance la reussite
        if(timer != null) {
            timer.cancel();
        }
        setResult(0);
        finish();
    }
}
