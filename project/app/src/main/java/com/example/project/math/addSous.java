package com.example.project.math;

import android.util.Log;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class addSous {
    private ArrayList<Integer> operande1 = new ArrayList<>();
    private ArrayList<Integer> operande2 = new ArrayList<>();

    private int borneInf;
    private int borneSup;
    private String op;

    private int Min = 0;
    private int Max = 100;

    public addSous(String operateur, int inf, int sup) {
        op = operateur;
        borneInf = inf;
        borneSup = sup;

        //initialisation des deux operande
        for (int i = borneInf; i <= borneSup; i++) {
            operande1.add(Min + (int)(Math.random() * ((Max - Min) + 1)));
            operande2.add(Min + (int)(Math.random() * ((Max - Min) + 1)));
        }
    }

    public int getResultat(int nb){
        if(op.equals("+")){
            return operande1.get(nb)+operande2.get(nb);
        }else if(op.equals("-")){
            return operande1.get(nb)-operande2.get(nb);
        }else{
            return -1;
        }
    };

    public boolean isEgual(int resu, int nb){
        return resu == abs(getResultat(nb));
    }

    public int getAllResult(ArrayList<Integer> resu){
        int nb = 0;
        for (int i = 0; i < borneSup; i++){
            if (!isEgual(resu.get(i),i)){
                nb++;
            }
        }
        return nb;
    };

    public String getOp(){return op;};
    public int getOperande1(int nb){return operande1.get(nb);};
    public int getOperande2(int nb){return operande2.get(nb);};
    public int getBorneInf(){return borneInf;};
    public int getBorneSup(){return borneSup;};
}
