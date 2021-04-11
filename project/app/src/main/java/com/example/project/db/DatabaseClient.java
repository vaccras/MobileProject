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

            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"Revolution française\", \"En qu'elle année débute t'elle\",1789,\"none\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"Europe\", \"Combien de pays font partie de l'union Europeen\",28,\"none\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"Le bon roi Charlemagne\", \"En qu'elle année à t-il été couronné\",800,\"fondation de l'empire carolegien\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"5eme république\", \"Fondation de la 5eme république\",1958,\"suite de la guerre d'Algérie\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"Jesus Christ\", \"Quand est'il née\",0,\"Début du calendrier\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"Saison\", \"Combien de saison à-t'on par ans\",5,\"attention au piège\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"General de Gaulle\", \"En qu'elle année à t-il prononcé son fameux discours <je vous ai compris... > \",1958,\"Du haut du balcon du gouvernement general d'algerie\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"CLovis\", \"En quelle année à t-il était baptisé\",496,\"none\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"Louis XIV\", \"En quelle année est-il mort\",1715,\"appelé le roi soleil\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"Droit des Femmes\", \"En qu'elle année les femmes ont obtenue le droit de vote en France\",1944,\"un ans avant la fin de la second guerre mondial\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"Guerre\", \"Année du début de la première guerre mondiale\",1914,\"none\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"Guerre 2\", \"Année du début de la seconde guerre mondiale\",1939,\"none\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"Jeanne pucelle d'Orléans\", \"En qu'elle année à t-elle remporté la victoire contre les anglais\",1429,\"Quand à t-il été couronné\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"Revolutin française\", \"En quelle année à débuté le régime de la terreur\",1793,\"se termine en 1794 avec la chute de Robespierre\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"Académie Française\", \"En quelle année Richellieu fonde cette institution\",1635,\"none\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"Comédie Française\", \"En quelle année Louis XIV à créer la comédie Française\",1680,\"none\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"Exclavage\", \"En quelle année la France a-t-elle abolie definitivement l'esclavage\",1848,\"different de la premiere abolition en 1794\");");

        }
    };
}
