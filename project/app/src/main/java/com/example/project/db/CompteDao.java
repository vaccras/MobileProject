package com.example.project.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CompteDao {
    // selection de l'integraliter des comptes existants
    @Query("SELECT * FROM Compte")
    List<Compte> getAll();

    // selection d'un compte donné en fonction du nom et du prenom
    @Query("SELECT * FROM Compte WHERE prenom LIKE :pr AND nom LIKE :nm LIMIT 1 ")
    Compte findByName(String pr, String nm);

    // insert, delete, et update pour un Compte donné
    @Insert
    void insert(Compte cmpt);

    @Insert
    long[] insertAll(Compte... cmpts);

    @Delete
    void delete(Compte cmpt);

    @Update
    void update(Compte cmpt);

}