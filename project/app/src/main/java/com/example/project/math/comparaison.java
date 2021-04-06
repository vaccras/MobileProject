package com.example.project.math;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class comparaison {
    //information necessaire a la mise en place des comparaisons

    private ArrayList<Integer> operande1 = new ArrayList<>();
    private ArrayList<Integer> operande2 = new ArrayList<>();
    private ArrayList<String> op = new ArrayList<String>();
    private String val[] = {"<",">"};

    private int borneInf;
    private int borneSup;

    private int Min = 0;
    private int Max = 100;

    public comparaison(int inf, int sup){
        borneInf = inf;
        borneSup = sup;

        int temp;
        //construction des listes des operandes et du comparateur
        for (int i = borneInf; i <= borneSup; i++) {
            operande1.add(Min + (int)(Math.random() * ((Max - Min) + 1)));
            operande2.add(Min + (int)(Math.random() * ((Max - Min) + 1)));

            temp = 0 + (int)(Math.random() * ((1 - 0) + 1));
            op.add(val[temp]);
        }
    }

    public boolean getResultat(int nb){
        // recuperation du resultat a un instant t en fonction du type de comparaison réalisé
        if(op.get(nb).equals("<")){
            return operande1.get(nb) < operande2.get(nb);
        }else if(op.get(nb).equals(">")){
            return operande1.get(nb) > operande2.get(nb);
        }else {
            return false;
        }
    };

    public boolean isEgual(boolean resu, int nb){
        // verification de la justesse d'une réponse à un instant t
        return (resu && getResultat(nb)) || (!resu && !getResultat(nb));
    }

    public int getAllResult(ArrayList<Boolean> resu){
        // récupération de tout les résultats
        int nb = 0;
        for (int i = 0; i < borneSup; i++){
            if (!isEgual(resu.get(i),i)){
                nb++; // compter le nombres d'erreur réaliser par l'utilisateur
            }
        }
        return nb;
    };

    // getteur utile
    public int getOperande1(int nb){return operande1.get(nb);};
    public int getOperande2(int nb){return operande2.get(nb);};
    public String getOp(int nb){return op.get(nb);};
    public int getBorneSup(){return borneSup;};
}
