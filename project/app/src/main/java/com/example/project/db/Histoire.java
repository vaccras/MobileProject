package com.example.project.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Histoire implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "intitulee")
    private String intitulee;

    @ColumnInfo(name = "question")
    private String question;

    @ColumnInfo(name = "reponse")
    private int reponse;

    @ColumnInfo(name = "aide")
    private String aide;

    /*** Getters and Setters ***/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntitulee() {
        return intitulee;
    }

    public void setIntitulee(String intitulee) {
        this.intitulee = intitulee;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getReponse() {
        return reponse;
    }

    public void setReponse(int reponse) {
        this.reponse = reponse;
    }

    public String getAide() {
        return aide;
    }

    public void setAide(String aide) {
        this.aide = aide;
    }
}
