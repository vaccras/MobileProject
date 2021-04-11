package com.example.project.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executors;

public class DatabaseClient {

    // Instance unique permettant de faire le lien avec la base de données
    private static DatabaseClient instance;

    // Objet représentant la base de données de votre application
    private AppDatabase appDatabase;

    // Constructeur
    private DatabaseClient(final Context context) {

        // Créer l'objet représentant la base de données de votre application
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "CompteJeune").build();

        // REMPLIR LA BD à la première création à l'aide de l'objet roomDatabaseCallback
        // Ajout de la méthode addCallback permettant de populate (remplir) la base de données à sa création
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "CompteJeune").addCallback(roomDatabaseCallback).build();
    }

    // Méthode statique
    // Retourne l'instance de l'objet DatabaseClient
    public static synchronized DatabaseClient getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseClient(context);
        }
        return instance;
    }

    // Retourne l'objet représentant la base de données de votre application
    public AppDatabase getAppDatabase() {
        return appDatabase;
    }

    // Objet permettant de populate (remplir) la base de données à sa création
    RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {

        // Called when the database is created for the first time.
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            db.execSQL("INSERT INTO Compte (prenom, nom, age, calcul, comparaison, culture) VALUES(\"Louise\", \"Charpe\",6,10,20,8);");
            db.execSQL("INSERT INTO Compte (prenom, nom, age, calcul, comparaison, culture) VALUES(\"Romain\", \"Favier\",8,15,10,3);");
            db.execSQL("INSERT INTO Compte (prenom, nom, age, calcul, comparaison, culture) VALUES(\"Cloé\", \"Dupuy\",9,5,10,20);");

            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"Révolution française\", \"En quelle année a-t-elle débuté ?\",1789,\"\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"Europe\", \"En 2021, combien de pays font partie de l'Union Européenne ?\",27,\"n'oublies pas le Brexit\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"Charlemagne\", \"En quelle année a-t-il été couronné ?\",800,\"fondation de l'empire Carolingien\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"5ème république\", \" En quelle année a commencé la 5ème république ?\",1958,\"suite de la guerre d'Algérie\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"Jesus Christ\", \"Quand est-il né ?\",0,\"Début du calendrier\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"Saison\", \"Combien de saison y a-t-il dans une année ?\",4,\"\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"Général de Gaulle\", \"En quelle année a-t-il prononcé son fameux discours <je vous ai compris... > \",1958,\"Du haut du balcon du gouvernement général d'Algérie\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"Cromagnon\", \"De quand date l'homme moderne ?\",300000,\"il y a ... ans\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"Louis XIV\", \"En quelle année est-il mort ?\",1715,\"appelé le roi soleil\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"Droit des Femmes\", \"En quelle année les femmes ont obtenu le droit de vote en France ?\",1944,\"un an avant la fin de la seconde guerre mondiale\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"Guerre\", \"Année du début de la première guerre mondiale\",1914,\"\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"Guerre\", \"Année du début de la seconde guerre mondiale\",1939,\"\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"Jeanne pucelle d'Orléans\", \"En qu'elle année est elle morte ?\",1431,\"\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"Révolution française\", \"En quelle année a débuté le régime de la terreur ?\",1793,\"se termine en 1794 avec la chute de Robespierre\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"Académie Française\", \"En quelle année Richelieu a-t-il fondé cette institution ?\",1635,\"\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"Comédie Française\", \"En quelle année Louis XIV a-t-il créé la comédie Française ?\",1680,\"\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"Esclavage\", \"En quelle année la France a-t-elle aboli définitivement l'esclavage ?\",1848,\"bien après la première abolition en 1794 en France\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"Moyen-âge\", \"Quand débute-t-il ?\",476,\"fin de l'empire Romain d'Occident\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"Amériques\", \"Quand Christophe Colomb a-t-il découvert l'Amérique ?\",1492,\"\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"Invention\", \"Quand Gutemberg a-t-il inventé l'imprimerie ?\",1454,\"\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"Guerre\", \"Quand a eu lieu la bataille de Marignan ?\",1515,\"\");");

        }
    };
}
