package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Tm_Calculs extends AppCompatActivity {


    public static final String TABLE_CHOISIE = "TABLE";
    public static final String REPONSE = "REPONSE";
    public int increment = 0 ;
    public int keyTampon = 0 ;
    LinearLayout linear;
    int table_choisie = 0;

    ///calcul
    public LinearLayout linearTMP;
    public TextView calcul;
    public EditText resultat;
    //Tableaux pour ranger les réponses de l'utilisateurs et les bonnes réponses

    ArrayList<EditText> reponsesUser = new ArrayList<>();
    ArrayList<Integer> reponses = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tm__calculs);

        linear = findViewById(R.id.linear);

        //Récupération de la table choisie par l'utilisateur
        table_choisie = Integer.parseInt(getIntent().getStringExtra(TABLE_CHOISIE));
        reponses = getIntent().getIntegerArrayListExtra(REPONSE);

        linearTMP = new LinearLayout(this);
        calcul = new TextView(this);
        resultat = new EditText(this);

        resultat.setInputType(InputType.TYPE_CLASS_NUMBER);
        //resultat.setHint("?");
        resultat.setGravity(Gravity.CENTER);
        calcul.setGravity(Gravity.CENTER);
        calcul.setTextSize(50);
        resultat.setTextSize(50);
        //resultat.setInputType(Integer.parseInt("numberPassword"));
        linearTMP.addView(calcul);
        linearTMP.addView(resultat);
        linear.addView(linearTMP);

        onMaj();

    }

    public void onMaj(){

        linearTMP.setOrientation(LinearLayout.HORIZONTAL);
        linearTMP.setGravity(Gravity.CENTER);
        if (increment<=12){

            calcul.setText(reponses.get(increment)/table_choisie + " x " + table_choisie + " = ");
            resultat.setText("");
            resultat.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (event.getAction()==KeyEvent.KEYCODE_1){
                        try {

                            onKeyUp();

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    return false;
                }
            });
        }
    }

    public void onKeyUp() throws InterruptedException {
        keyTampon++;

        if(keyTampon==1){
            Thread.sleep(100);
            increment++;
            keyTampon=0;
            onMaj();
        }
    }

}

