package com.example.project.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Compte.class, Histoire.class} , version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CompteDao compteDao();
    public abstract HistoireDao histoireDao();
}