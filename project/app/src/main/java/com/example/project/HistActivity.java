package com.example.project;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.project.db.DatabaseClient;
import com.example.project.db.Histoire;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
public class HistActivity extends AppCompatActivity {

    private DatabaseClient mDb;

    private TextView textViewIntitu;
    private TextView textViewQuestion;
    private TextView textViewAide;

    private List<Histoire> ARR_histoires;
    private ArrayList<Integer> ARR_questions;
    private ArrayList<Integer> ARR_reponses;

    private int position = 0;
    private int step = 0;
    private EditText reponse;

    public static final String PRENOM_KEY = "PRENOM";
    public static final String NOM_KEY = "NOM";
    private String prenom;
    private String nom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //===constructeur, passage de parametres===========================================//
            super.onCreate(savedInstanceState);
            prenom = getIntent().getStringExtra(PRENOM_KEY);
            nom = getIntent().getStringExtra(NOM_KEY);
            setContentView(R.layout.activity_hist);

        //==========elmt d'affichage=======================================================//
            textViewIntitu = (TextView) findViewById(R.id.HIST_intitule);
            textViewQuestion = (TextView) findViewById(R.id.HIST_Question);
            textViewAide = (TextView) findViewById(R.id.HIST_indice);


        //=====Lier l'adapter au listView==================================================//
            mDb = DatabaseClient.getInstance(getApplicationContext());

        //===========init des list=========================================================//
            ARR_histoires = new ArrayList<>();
            ARR_questions = new ArrayList<>();
            ARR_reponses = new ArrayList<>();

        //========valeurs aléatoires=======================================================
            ArrayList<Integer> temp=new ArrayList<>();
            for (int i = 0 ; i<12; i++){
                temp.add(i);
            }
            Collections.shuffle(temp);
            for (int i = 0 ; i<10; i++){
                ARR_questions.add(temp.get(i));
            }
        //=================================================================================//

            //a ce stade : ARR_questions contient des nombres aléatoires entre 0 et 11
            //ARR_reponses est vide
            //ARR_histoires est vide

        //=================================================================================
            class GetHist extends AsyncTask<Void, Void, List<Histoire>> {
                @Override
                protected List<Histoire> doInBackground(Void... voids) {
                    List<Histoire> listHistoires = mDb.getAppDatabase().histoireDao().getAll();
                    return listHistoires;
                }

                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                protected void onPostExecute(List<Histoire> listHistoires) {
                    super.onPostExecute(listHistoires);
                    int i =0;
                    for (Histoire hist:listHistoires){
                        ARR_histoires.add(hist);
                        System.out.println("ARR_Histoires se remplie, contient "+ i + " histoires");
                        i++;
                    }
                }
            }
            GetHist gt = new GetHist();
            gt.execute();
        //============ARR_Histoires est plein=============================================//

            onEnter(null);
    }

    public void onEnter(View view) {

        Button b = findViewById(R.id.HIST_valider);
        TextView questioncourante =  findViewById(R.id.HIST_numero);

        if(step==0){ // PREMIERE ETAPE : page d'aide avec bouton commencer
            //System.out.println("Step 0");
            b.setText("Commencer");
            textViewIntitu.setText("Pour commencer cliquer sur le bouton");
            textViewQuestion.setText("Pour chaque question répondez par un numérique, souvent une date");
            textViewAide.setText("");
            questioncourante.setText("");

        }else if(step==1){ // DEUXIEME ETAPE : Affichage première question
            //System.out.println("Step 1");
            b.setText("Valider");
            textViewIntitu.setText(ARR_histoires.get(ARR_questions.get(position)).getIntitulee());
            textViewQuestion.setText(ARR_histoires.get(ARR_questions.get(position)).getQuestion());
            textViewAide.setText(ARR_histoires.get(ARR_questions.get(position)).getAide());
            position++;
            questioncourante.setText("question numéro : "+(position));

        }else if(step<=10){ // TROISIEME ETAPE -> ONZIEMME ETAPE  : recup réponce de la question précédente, affichage de la question courante
            //System.out.println("Step "+step);
            reponse = findViewById(R.id.HIST_zonesaisie);
            textViewIntitu.setText(ARR_histoires.get(ARR_questions.get(position)).getIntitulee());
            textViewQuestion.setText(ARR_histoires.get(ARR_questions.get(position)).getQuestion());
            textViewAide.setText(ARR_histoires.get(ARR_questions.get(position)).getAide());

            //System.out.println(reponse.getText().length() + "  reponse enregistrée numéro :  " + position);

            if (reponse.getText().length()==0) {
                ARR_reponses.add(9999+position);
            } else {
                ARR_reponses.add(Integer.valueOf(reponse.getText().toString()));
            }
            position++;
            questioncourante.setText("question numéro : "+(position));

        }else{ // DOUXIEMME ETAPE : recup réponce de la question précédente, lancement de l'activité de résultat
            //System.out.println("Step "+step);
            //System.out.println(reponse.getText().length() + "  reponse enregistrée numéro : " + position);
            if (reponse.getText().length()==0) {
                ARR_reponses.add(9999+position);
            } else {
                ARR_reponses.add(Integer.valueOf(reponse.getText().toString()));
            }
            //System.out.println("ON Y EST : " +  step + " doit etre égale à 12");
            //System.out.println("position " +  position + " doit etre égale à 9");

            Intent intent = new Intent(this, HistResultActivity.class);
            intent.putExtra("question", ARR_questions);
            intent.putExtra("reponses", ARR_reponses);
            intent.putExtra(ProfileActivity.PRENOM_KEY, prenom);
            intent.putExtra(ProfileActivity.NOM_KEY, nom);
            startActivity(intent);

        }
        step++;
    }


    public void retour(View view) {super.finish();}




}