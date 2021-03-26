package com.example.project.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Compte implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "prenom")
    private String prenom;

    @ColumnInfo(name = "nom")
    private String nom;

    @ColumnInfo(name = "age")
    private int age;

    @ColumnInfo(name = "calcul")
    private int calcul;

    @ColumnInfo(name = "geometrie")
    private int geometrie;

    @ColumnInfo(name = "culture")
    private int culture;

    /*** Getters and Setters ***/
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public int getCalcul() {
        return calcul;
    }
    public void setCalcul(int calcul) {
        this.calcul = calcul;
    }

    public int getGeometrie() {
        return geometrie;
    }
    public void setGeometrie(int geometrie) {
        this.geometrie = geometrie;
    }

    public int getCulture() {
        return culture;
    }
    public void setCulture(int culture) {
        this.culture = culture;
    }

}