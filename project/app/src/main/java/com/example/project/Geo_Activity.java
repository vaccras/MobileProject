package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;


public class Geo_Activity extends AppCompatActivity {



    private ImageView mapMonde;
    private ArrayList<Integer> Couleur;
    private ArrayList<String> Pays;
    private ArrayList<Integer> ARR_questions;
    private ArrayList<Integer> ARR_reponses;
    private ArrayList<Integer> temp;

    private int step=0;
    private TextView question;
    private Button boutonA;
    private Button boutonB;
    private Button boutonC;
    private int combo=0;
    private int meilleurcombo=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo);

        ARR_questions= new ArrayList<>();
        ARR_reponses=new ArrayList<>();
        boutonA = findViewById(R.id.GEO_BOUT_A);
        boutonB = findViewById(R.id.GEO_BOUT_B);
        boutonC = findViewById(R.id.GEO_BOUT_C);
        question = findViewById(R.id.GEO_question);


        Pays=new ArrayList<>(Arrays.asList("l'Allemagne", "l'Autriche", "l'Espagne", "la Belgique", "la Biélorussie","la Bulgarie", "la Croatie", "le Danemark",
                "l'Estonie", "la Finlande", "la France", "la Grèce", "la Hongrie", "l'Irlande", "l'Islande", "l'Italie", "la Lettonie", "la Lituanie", "la Yougoslavie",
                "la Moldavie", "la Norvège", "les Pays-Bas", "la Pologne", "le Portugal", "la République tchèque", "la Roumanie", "le Royaume-Uni", "la Russie",
                "la Slovaquie", "la Suède", "la Suisse","la Turquie", "l'Ukraine","le Maroc","L'algérie et la Tunisie"));

        Couleur=new ArrayList<>(Arrays.asList(new Integer[]{Color.parseColor("#404040"), Color.parseColor("#B6A348"),Color.parseColor("#FFD800"),
                                                            Color.parseColor("#9F6D4B"),Color.parseColor("#C2B743"),Color.parseColor("#FF9F5B"),
                                                            Color.parseColor("#7F0037"),Color.parseColor("#B43535"),Color.parseColor("#731D93"),
                                                            Color.parseColor("#7FFFC5"),Color.parseColor("#0026FF"),Color.parseColor("#FFFFFF"),
                                                            Color.parseColor("#A17FFF"),Color.parseColor("#007F0E"),Color.parseColor("#0094FF"),
                                                            Color.parseColor("#FF0000"),Color.parseColor("#864B4B"),Color.parseColor("#D35D5D"),
                                                            Color.parseColor("#FF006E"),Color.parseColor("#8DE567"),Color.parseColor("#7F92FF"),
                                                            Color.parseColor("#6A81FF"),Color.parseColor("#4CFF00"),Color.parseColor("#F3CB76"),
                                                            Color.parseColor("#21007F"),Color.parseColor("#27B236"),Color.parseColor("#FF7F7F"),
                                                            Color.parseColor("#B6FF00"),Color.parseColor("#4800FF"),Color.parseColor("#D67FFF"),
                                                            Color.parseColor("#7F0000"),Color.parseColor("#FFE97F"),Color.parseColor("#29411F"),
                                                            Color.parseColor("#7F3300"),Color.parseColor("#FF6A00")}));
        //========valeurs aléatoires=======================================================

        System.out.println(Couleur);
        System.out.println(Pays);
        System.out.println("=========================etgegrf=======================");
        ArrayList<Integer> temp=new ArrayList<>();
        for (int i = 0 ; i<Couleur.size(); i++){
            temp.add(i);
        }
        Collections.shuffle(temp);
        for (int i = 0 ; i<10; i++){
            ARR_questions.add(temp.get(i));
        }
        //=================================================================================//

        OnSelectedCountry(null);
    }



    public void OnSelectedCountry(View view){


        Random rand = new Random();


        if (step==0){
            temp = new ArrayList<>();
            question.setText(Pays.get(ARR_questions.get(step)));
            temp.add(ARR_questions.get(step));
            temp.add(rand.nextInt(Pays.size()));
            while (temp.size()<3){
                int tempcouleur = rand.nextInt(Pays.size());
                if (temp.get(1)!=tempcouleur){
                    temp.add(tempcouleur);
                }
            }
            Collections.shuffle(temp);

            boutonA.setBackgroundColor(Couleur.get(temp.get(0)));
            boutonB.setBackgroundColor(Couleur.get(temp.get(1)));
            boutonC.setBackgroundColor(Couleur.get(temp.get(2)));
        }else if(step<10){

            if (view.getId()==R.id.GEO_BOUT_A){
                ARR_reponses.add(temp.get(0));
            }else if(view.getId()==R.id.GEO_BOUT_B){
                ARR_reponses.add(temp.get(1));
            }else{
                ARR_reponses.add(temp.get(2));
            }

            if (ARR_reponses.get(step-1)==ARR_questions.get(step-1)){
                combo++;
            }else{
                if (combo>meilleurcombo){
                    meilleurcombo=combo;
                }
                combo=0;
            }

            question.setText(Pays.get(ARR_questions.get(step)));
            temp = new ArrayList<>();
            temp.add(ARR_questions.get(step));
            temp.add(rand.nextInt(Pays.size()));
            while (temp.size()<3){
                int tempcouleur = rand.nextInt(Pays.size());
                if (temp.get(1)!=tempcouleur){
                    temp.add(tempcouleur);
                }
            }
            Collections.shuffle(temp);

            boutonA.setBackgroundColor(Couleur.get(temp.get(0)));
            boutonB.setBackgroundColor(Couleur.get(temp.get(1)));
            boutonC.setBackgroundColor(Couleur.get(temp.get(2)));


        }else{
            if (view.getId()==R.id.GEO_BOUT_A){
                ARR_reponses.add(temp.get(0));
            }else if(view.getId()==R.id.GEO_BOUT_B){
                ARR_reponses.add(temp.get(1));
            }else{
                ARR_reponses.add(temp.get(2));
            }
            if (ARR_reponses.get(step-1)==ARR_questions.get(step-1)){
                combo++;
            }else{
                if (combo>meilleurcombo){
                    meilleurcombo=combo;
                }
                combo=0;
            }

            retour(view);

        }

        TextView comboView = findViewById(R.id.GEO_Combo);
        comboView.setText("COMBO = "+ combo);

        step++;



    }


    public void retour(View view) {super.finish();}
}