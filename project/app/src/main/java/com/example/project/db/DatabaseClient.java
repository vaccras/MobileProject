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

            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"Le bon roi Charlemagne\", \"En qu'elle année à t-il été couronné\",800,\"none\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"Charle Martel\", \"En qu'elle année à t-il bouté les métèques hors de notre sainte france\",732,\"none\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"Jeanne pucelle d'Orléans\", \"En qu'elle année à t-elle bouté les rosebeef hors de notre sainte france\",6,\"Quand à t-il été couronné\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"ssss\", \"En qu'elle année à t-il été couronné\",800,\"none\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"qqqq\", \"En qu'elle année à t-il bouté les métèques hors de notre sainte france\",800,\"none\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"ggggg\", \"En qu'elle année à t-elle bouté les rosebeef hors de notre sainte france\",800,\"Quand à t-il été couronné\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"zzzzzzzz\", \"En qu'elle année à t-il été couronné\",800,\"none\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"rrrrrrl\", \"En qu'elle année à t-il bouté les métèques hors de notre sainte france\",800,\"none\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"hhhhhhhhhs\", \"En qu'elle année à t-elle bouté les rosebeef hors de notre sainte france\",800,\"Quand à t-il été couronné\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"yyyyyyyyyyyyyyyyyy\", \"En qu'elle année à t-il été couronné\",800,\"none\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\";;;;;;;;;;;;;;;;\", \"En qu'elle année à t-il bouté les métèques hors de notre sainte france\",800,\"none\");");
            db.execSQL("INSERT INTO Histoire (intitulee, question, reponse, aide) VALUES(\"Jbbbbbbbbbbbbbb\", \"En qu'elle année à t-elle bouté les rosebeef hors de notre sainte france\",800,\"Quand à t-il été couronné\");");

        }
    };
}
