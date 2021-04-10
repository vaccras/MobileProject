package com.example.project;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
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

    public static final String PRENOM_KEY = "PRENOM";
    public static final String NOM_KEY = "NOM";
    private DatabaseClient mDb;
    private List<Histoire> histoires;
    private int incre=0;
    private String prenom;
    private String nom;
    private int score=0;//sur 10

    public ArrayList<Integer> reponsesUser = new ArrayList<>();
    public ArrayList<Integer> question = new ArrayList<>();

    public LinearLayout Tableau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prenom = getIntent().getStringExtra(PRENOM_KEY);
        nom = getIntent().getStringExtra(NOM_KEY);
        setContentView(R.layout.activity_hist_result);

        Tableau = findViewById(R.id.HIST_Tabl);


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

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            protected void onPostExecute(List<Histoire> histoire) {
                super.onPostExecute(histoire);
                for (Histoire hist:histoire){
                    histoires.add(hist);
                    System.out.println("ON Y PASSE");
                }
                maj();
            }
        }
        GetHist gt = new GetHist();
        gt.execute();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void maj() {

        System.out.println(histoires);
        for (int num : reponsesUser) {
            System.out.println("la reponse : "+num);
        }

        for (int num : question) {

            TextView intitule = new TextView(this);
            TextView quest = new TextView(this);
            TextView resp = new TextView(this);
            TextView respAtt = new TextView(this);
           // quest.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            Histoire hist = histoires.get(num);

            LinearLayout current = new LinearLayout(this);

            intitule.setText(hist.getIntitulee());
            quest.setText(hist.getQuestion());
            quest.setTextColor( getColor(R.color.black));
            intitule.setTextColor( getColor(R.color.black));
            intitule.setTextSize(25);
            quest.setTextSize(20);
            quest.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            resp.setTextSize(20);
            respAtt.setTextSize(20);

            current.setBackgroundColor(getColor(R.color.LightCoral));

            if (reponsesUser.get(incre)>=9999){
                resp.setText("pas de réponses");
            }else {
                resp.setText("votre réponse : "+reponsesUser.get(incre));
            }
            respAtt.setText("attendue : "+hist.getReponse());
            respAtt.setTextColor( getColor(R.color.green));
            resp.setTextColor( getColor(R.color.red));

            if(reponsesUser.get(incre)==hist.getReponse()){
                score++;
                resp.setTextColor( getColor(R.color.green));
                current.setBackgroundColor(getColor(R.color.PaleGreen));
                System.out.println(score+" le score ");
            }


            current.addView(intitule);
            current.addView(quest);
            current.addView(resp);
            current.addView(respAtt);
            current.setOrientation(LinearLayout.VERTICAL);
            current.setGravity(Gravity.CENTER);


            Tableau.addView(current);
            System.out.println("passage B");
            incre++;
        }
        incre=0;

    }
    public void retour(View view) {
        // revient au choix du type d'exercice
        Intent intent = new Intent(this, typeActivity.class);
        intent.putExtra(typeActivity.PRENOM_KEY, prenom);
        intent.putExtra(typeActivity.NOM_KEY, nom);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //permet d'eviter l'empilement
        startActivity(intent);
    }
    public void rejouer(View view) {

    // revient au choix de la multiplication
    Intent intent = new Intent(this, HistActivity.class);
    intent.putExtra(HistActivity.PRENOM_KEY, prenom);
    intent.putExtra(HistActivity.NOM_KEY, nom);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //permet d'eviter l'empilement
    startActivity(intent);


    }
}