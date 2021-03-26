						package com.example.project.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface HistoireDao {

    @Query("SELECT * FROM Histoire")
    List<Histoire> getAll();

    @Query("SELECT * FROM Histoire WHERE id LIKE :id")
    Histoire findByName(String id);
    //LiveData<Histoire> findByName(String pr, String nm);

    @Insert
    void insert(Histoire question);

    @Insert
    long[] insertAll(Histoire... question);

    @Delete
    void delete(Histoire question);

    @Update
    void update(Histoire question);

}