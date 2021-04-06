package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AideActivity extends AppCompatActivity {
    // recuperation de l'utilisateur si il n'est pas en anonyme
    public static final String PRENOM_KEY = "PRENOM";
    public static final String NOM_KEY = "NOM";

    //recuperation des choix utilisateurs
    public static final String TYPE = "TYPE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aide);

        //recuperation des valeurs
        String prenom = getIntent().getStringExtra(PRENOM_KEY);
        String nom = getIntent().getStringExtra(NOM_KEY);
        String type = getIntent().getStringExtra(TYPE);

        //verification du type de jeu pour afficher le nom
        if(!prenom.equals("anonyme") && !nom.equals("anonyme") ){
            TextView accueil = findViewById(R.id.acceuil);
            accueil.setText("Bonjour "+prenom + " !");
        }

        //mise à jour de l'explication en fonction des math ou de l'histoire
        TextView description = findViewById(R.id.explication);
        if (type.equals("math")){
            description.setText("Tu as accés à 2 type d'exercices. La comparaison ou tu devras cliquer sur vrai ou faux suivant la question donné (ex: 60<5 est faux)." +
                    "Et les exercices de calcul, l'addition, la soustraction, et la mulitiplication." +
                    "Pour la multiplication tu pourras choisir la difficutlé en choisissant le premier operateur entre 1 et 12, ainsi que l'ordre ou désordre des questions." +
                    "Pour l'addition et la soustraction, tu pourra choisir si c'est sur des entiers, des dizaines ou des centaines pour régler la difficulté." +
                    "Bon courage, et amuse toi en apprenant !!");
        }else {
            description.setText("exercice de culture gé, Sevan fait la description!");
        }
    }

    public void retour(View view) {super.finish();}
}