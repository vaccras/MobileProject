package com.example.project.math;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class comparaison implements Parcelable {
    //information necessaire a la mise en place des comparaisons

    private int[] operande1;
    private int[] operande2;
    private String[] op;
    private String val[] = {"<",">"};
    private boolean[] repUser;

    private int borneInf;
    private int borneSup;
    private int increment;

    private int Min = 0;
    private int Max = 100;

    public comparaison(int inf, int sup){
        borneInf = inf;
        borneSup = sup;
        increment = 0;
        int temp;

        operande1 = new int[borneSup - borneInf + 1];
        operande2 = new int[borneSup - borneInf + 1];
        op = new String[borneSup - borneInf + 1];
        repUser = new boolean[borneSup - borneInf + 1];

        //construction des listes des operandes et du comparateur
        for (int i = borneInf; i <= borneSup; i++) {
            operande1[i] = (Min + (int)(Math.random() * ((Max - Min) + 1)));
            operande2[i] = (Min + (int)(Math.random() * ((Max - Min) + 1)));

            temp = 0 + (int)(Math.random() * ((1 - 0) + 1));
            op[i] = (val[temp]);
        }
    }

    public boolean getResultat(int nb){
        // recuperation du resultat a un instant t en fonction du type de comparaison réalisé
        if(op[nb].equals("<")){
            return operande1[nb] < operande2[nb];
        }else if(op[nb].equals(">")){
            return operande1[nb] > operande2[nb];
        }else {
            return false;
        }
    };

    public boolean isEgual(boolean resu, int nb){
        // verification de la justesse d'une réponse à un instant t
        return (resu && getResultat(nb)) || (!resu && !getResultat(nb));
    }

    public int getAllResult(){
        // récupération de tout les résultats
        int nb = 0;
        for (int i = 0; i < borneSup; i++){
            if (!isEgual(repUser[i],i)){
                nb++; // compter le nombres d'erreur réaliser par l'utilisateur
            }
        }
        return nb;
    };

    // getteur utile
    public int getOperande1(int nb){return operande1[nb];};
    public int getOperande2(int nb){return operande2[nb];};
    public String getOp(int nb){return op[nb];};
    public int getBorneSup(){return borneSup;};
    public int getIncrement(){return increment;};
    public void setIncrement(){increment++;};
    public void setRepUser(int nb, boolean rep){repUser[nb] = rep;};

    // implements Parcelable
    protected comparaison(Parcel in) {
        increment = in.readInt();
        borneInf = in.readInt();
        borneSup = in.readInt();
        operande1 = new int[borneSup-borneInf];
        operande2 = new int[borneSup-borneInf];
        op = new String[borneSup-borneInf];
        repUser = new boolean[borneSup-borneInf];
        in.readIntArray(operande1);
        in.readIntArray(operande2);
        in.readStringArray(op);
        in.readBooleanArray(repUser);
        Min = in.readInt();
        Max = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(increment);
        dest.writeInt(borneInf);
        dest.writeInt(borneSup);
        dest.writeIntArray(operande1);
        dest.writeIntArray(operande2);
        dest.writeStringArray(op);
        dest.writeBooleanArray(repUser);
        dest.writeInt(Min);
        dest.writeInt(Max);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<comparaison> CREATOR = new Parcelable.Creator<comparaison>() {
        @Override
        public comparaison createFromParcel(Parcel in) {
            return new comparaison(in);
        }

        @Override
        public comparaison[] newArray(int size) {
            return new comparaison[size];
        }
    };
}
