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
            description.setText("Tu as accès à 2 types d'exercices. La comparaison où tu devras cliquer sur vrai ou faux selon l'inégalité proposée (ex: 60<5 est faux). " +
                    "Et des exercices de calculs concernant l'addition, la soustraction, et la mulitiplication ; soit avec 12 questions soit en une minute. " +
                    "Pour la multiplication tu pourras définir la difficulté en choisissant le premier opérateur entre 1 et 12, ainsi que l'ordre ou le désordre des questions. " +
                    "Pour l'addition et la soustraction, tu pourras choisir si tu travailles avec des nombres entiers à 1 chiffre (unités), 2 chiffres (dizaines) ou 3 chiffres (centaines) pour régler la difficulté. " +
                    "Bon courage, et amuse toi en apprenant !!");
        }else {
            description.setText("Tu as accès à 2 types d'exercices : un quizz d'histoire où la réponse attendue est un nombre (une petite aide est généralement fournie) " +
                    " et un jeu de reconnaissance des pays sur une map colorée, en choisissant une couleur parmi trois, en essayant d'avoir le maximum de réponses justes à la suite ");
        }
    }

    public void retour(View view) {super.finish();}
}