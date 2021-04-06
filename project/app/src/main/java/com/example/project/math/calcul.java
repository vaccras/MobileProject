package com.example.project.math;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

import static java.lang.Math.abs;

public class calcul {
    //information necessaire a la mise en place des calculs

    private ArrayList<Integer> operande1 = new ArrayList<>();
    private ArrayList<Integer> operande2 = new ArrayList<>();

    private int borneInf;
    private int borneSup;
    private String op;

    private int Min = 0;
    private int Max;

    public calcul(String operateur, int inf, int sup, String type, int mult) {
        op = operateur;
        borneInf = inf;
        borneSup = sup;

        // en fonction du choix de difficulté pour addition et soustraction
        if (type.equals("entier")){
            Max = 10;
        }else if(type.equals("dizaine")){
            Max = 100;
        }else if(type.equals("centaine")){
            Max = 1000;
        }else if(type.equals("infini") && mult == -1){ // infini pour addition et soustraction
            Max = 50;
            borneSup = 200;
        } else if (type.equals("infini") && mult == 1){ // infini pour multiplication
            Max = 10;
            borneSup = 200;
        } else { // création de la mutliplication
            for (int i = borneInf; i <= borneSup; i++){
                operande1.add(mult);
                operande2.add(i);
            }

            // si l'utilisateur fait une table de mutliplication dans le désordre
            if (type.equals("shuffle")){
                Collections.shuffle(operande2);
            }
        }
        
        //initialisation des deux operande
        for (int i = borneInf; i <= borneSup; i++) {
            operande1.add(Min + (int)(Math.random() * ((Max - Min) + 1)));
            operande2.add(Min + (int)(Math.random() * ((Max - Min) + 1)));
        }
    }

    public int getResultat(int nb){
        // recuperation du resultat a un instant t en fonction du type de calcul réalisé
        if(op.equals("+")){
            return operande1.get(nb)+operande2.get(nb);
        }else if(op.equals("-")){
            return operande1.get(nb)-operande2.get(nb);
        }else if (op.equals("x")){
            return operande1.get(nb)*operande2.get(nb);
        } else{
            return -1;
        }
    };

    // verification de la justesse d'une réponse à un instant t
    public boolean isEgual(int resu, int nb){
        return resu == abs(getResultat(nb));
    }

    public int getAllResult(ArrayList<Integer> resu, int borne){
        // récupération de tout les résultats
        int nb = 0;
        for (int i = 0; i < borne; i++){
            if (!isEgual(resu.get(i),i)){
                nb++; // compter le nombres d'erreur réaliser par l'utilisateur
            }
        }
        return nb;
    };

    // getteur utile
    public int getOperande1(int nb){return operande1.get(nb);};
    public int getOperande2(int nb){return operande2.get(nb);};
    public int getBorneSup(){return borneSup;};
}
