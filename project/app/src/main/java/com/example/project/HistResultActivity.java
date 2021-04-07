package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.project.db.DatabaseClient;
import com.example.project.db.Histoire;

import java.util.ArrayList;
import java.util.List;

public class HistResultActivity extends AppCompatActivity {


    public static final String QUESTION = "TABLE";
    public static final String REPONSE = "REPONSE";
    private DatabaseClient mDb;
    private List<Histoire> histoires;

    private int score=0;//sur 10

    public ArrayList<Integer> reponsesUser = new ArrayList<>();
    public ArrayList<Integer> question = new ArrayList<>();

    public LinearLayout Tableau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hist_result);

        Tableau = findViewById(R.id.HIST_Tabl);
        Tableau.setOrientation(LinearLayout.VERTICAL);

        //Récupération des réponses donnée par l'utilisateur et des question liées
        question = (ArrayList<Integer>) getIntent().getSerializableExtra("question");
        reponsesUser = (ArrayList<Integer>) getIntent().getSerializableExtra("reponses");

        mDb = DatabaseClient.getInstance(getApplicationContext());
        histoires = new ArrayList<>();



        class GetHist extends AsyncTask<Void, Void, List<Histoire>> {
            @Override
            protected List<Histoire> doInBackground(Void... voids) {
                List<Histoire> listExo = mDb.getAppDatabase().histoireDao().getAll();
                return listExo;
            }

            @Override
            protected void onPostExecute(List<Histoire> histoire) {
                super.onPostExecute(histoire);
                for (Histoire hist:histoire){
                    histoires.add(hist);
                }
                maj();
            }
        }
        GetHist gt = new GetHist();
        gt.execute();
    }

    public void maj() {

        System.out.println(histoires);
        LinearLayout temp = new LinearLayout(this);;
        boolean col = true;

        for (int num : question) {

            TextView intitule = new TextView(this);
            TextView quest = new TextView(this);
            TextView resp = new TextView(this);
            TextView respAtt = new TextView(this);

            Histoire hist = histoires.get(num);

            LinearLayout current = new LinearLayout(this);
            intitule.setText(hist.getIntitulee());
            quest.setText(hist.getQuestion());
            if (reponsesUser.get(num)>=9999){
                resp.setText("pas de réponses");
            }else {
                resp.setText("votre réponse : "+reponsesUser.get(num));
            }
            respAtt.setText("attendue : "+hist.getReponse());

            if(reponsesUser.get(num)==hist.getReponse()){
                score++;
            }

            current.addView(intitule);
            current.addView(quest);
            current.addView(resp);
            current.addView(respAtt);
            current.setOrientation(LinearLayout.VERTICAL);
            current.setGravity(Gravity.CENTER);


            if (col){
                temp = new LinearLayout(this);
                temp.setOrientation(LinearLayout.HORIZONTAL);
                temp.addView(current);

                System.out.println("passage A");

            }else{
                temp.addView(current);
                Tableau.addView(temp);System.out.println("passage B");
            }
            col=!col;


        }

    }

}