package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.project.db.Compte;
import com.example.project.db.DatabaseClient;
import com.example.project.db.Histoire;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
public class HistActivity extends AppCompatActivity {

    private DatabaseClient mDb;
    private Integer reponse_courante=0;
    private int position=0;
    private TextView textViewIntitu;
    private TextView textViewQuestion;
    private TextView textViewAide;
    private ArrayList<Integer> questions;
    private ArrayList<Integer> Areponses;
    private boolean creation = true;
    private EditText reponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hist);
        textViewIntitu = (TextView) findViewById(R.id.HIST_intitule);
        textViewQuestion = (TextView) findViewById(R.id.HIST_Question);
        textViewAide = (TextView) findViewById(R.id.HIST_indice);
        // Lier l'adapter au listView
        mDb = DatabaseClient.getInstance(getApplicationContext());
        questions = new ArrayList<>();
        Areponses = new ArrayList<>();
        ArrayList<Integer> temp=new ArrayList<>();
        for (int i = 0 ; i<12; i++){
            temp.add(i);
        }
        Collections.shuffle(temp);
        for (int i = 0 ; i<10; i++){
            questions.add(temp.get(i));
        }


        onEnter(null);

    }

    class GetHist extends AsyncTask<Void, Void, List<Histoire>> {
        @Override
        protected List<Histoire> doInBackground(Void... voids) {
            List<Histoire> listExo = mDb.getAppDatabase().histoireDao().getAll();
            return listExo;
        }

        @Override
        protected void onPostExecute(List<Histoire> histoires) {
            super.onPostExecute(histoires);

            textViewIntitu.setText(histoires.get(questions.get(position)).getIntitulee());
            textViewQuestion.setText(histoires.get(questions.get(position)).getQuestion());
            textViewAide.setText(histoires.get(questions.get(position)).getAide());
            reponse_courante = histoires.get(questions.get(position)).getReponse();
            maj(null);

        }
    }

    public void maj(View view) {



        if(creation==false) {
            System.out.println(reponse.getText().length() + "  sdfdddddddddddddddddddddd");
            if (reponse.getText().length()==0) {
                Areponses.add(9999+position);
            } else {
                Areponses.add(Integer.valueOf(reponse.getText().toString()));
            }

            position++;
        }
        creation = false;


    }

    public void onEnter(View view) {


        reponse = findViewById(R.id.HIST_zonesaisie);
        if (position>=10){
            position=0;
            System.out.println("ON Y EST");
            Intent intent = new Intent(this, HistResultActivity.class);
            intent.putExtra("question",questions);
            intent.putExtra("reponses",Areponses);
            startActivity(intent);
        }else {
            GetHist gt = new GetHist();
            gt.execute();
        }


    }
    public void retour(View view) {super.finish();}


}