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
    private int position=-1;
    private TextView textViewIntitu;
    private TextView textViewQuestion;
    private TextView textViewAide;
    private ArrayList<Integer> questions;
    private ArrayList<Integer> Areponses;
    private boolean creation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hist);
        textViewIntitu = (TextView) findViewById(R.id.HIST_intitule);
        textViewQuestion = (TextView) findViewById(R.id.HIST_Question);
        textViewAide = (TextView) findViewById(R.id.HIST_indice);
        // Lier l'adapter au listView
        mDb = DatabaseClient.getInstance(getApplicationContext());
        questions = new ArrayList<>(10);
        Areponses = new ArrayList<>(10);
        for (int i = 0 ; i<10; i++){
            questions.add(i);
        }
        Collections.shuffle(questions);

        onEnter(null);
        creation = false;
    }

    public void onEnter(View view) {
        position++;
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

            }
        }


        GetHist gt = new GetHist();
        gt.execute();

        EditText reponse = findViewById(R.id.HIST_zonesaisie);

        if(creation==false) {


            if (reponse.getText().toString().equals(String.valueOf(reponse_courante))) {
                Areponses.add(Integer.valueOf(reponse.getText().toString()));
            } else {
                Areponses.add(9999);
            }
        }

        if (position>=10){
            System.out.println("ON Y EST");
            Intent intent = new Intent(this, HistResultActivity.class);
            intent.putExtra("question",questions);
            intent.putExtra("reponses",Areponses);
            startActivity(intent);
        }

    }



}