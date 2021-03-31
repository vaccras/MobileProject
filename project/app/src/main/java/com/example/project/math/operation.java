package com.example.project.math;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

public class operation {
    private int operande1;
    private String op;
    private int borneInf;
    private int borneSup;
    private ArrayList<Integer> operande2 = new ArrayList<>();

    public operation(int op1, String operateur, int inf, int sup, String type){
        operande1 = op1; op = operateur;
        borneInf = inf; borneSup = sup;

        for (int i = borneInf; i <= borneSup; i++){
            operande2.add(i);
        }

        if (type.equals("shuffle")){
            Collections.shuffle(operande2);
        }
        Log.i("TAG operande2", String.valueOf(operande2));

    }

    public int getOperande1(){return operande1;};
    public String getOp(){return op;};
    public int getOperande2(int nb){return operande2.get(nb);};
    public int getBorneInf(){return borneInf;};
    public int getBorneSup(){return borneSup;};

    public int getResultat(int nb){
        if (op.equals("x")){
            return operande1*operande2.get(nb);
        }else{
            return -1;
        }
    };

    public boolean isEgual(int resu, int nb){
        return resu == getResultat(nb);
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
}
