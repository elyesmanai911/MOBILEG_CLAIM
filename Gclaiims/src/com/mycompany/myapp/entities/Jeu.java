/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author souma
 */
import java.util.Date;
public class Jeu {
    private int id;
    private String nomjeu;
    private String description;
    private String createur;
    private Date datec;
    private String image;


     public Jeu(int id, String nomjeu, String description, String createur, Date datec, String image) {
        this.id = id;
        this.nomjeu = nomjeu;
        this.description = description;
        this.createur = createur;
        this.datec = datec;
        this.image = image;
    }

    public Jeu(String nomjeu, String description, String createur) {
        this.nomjeu = nomjeu;
        this.description = description;
        this.createur = createur;
    }

    public Jeu(int id, String nomjeu, String description, String createur) {
        this.id = id;
        this.nomjeu = nomjeu;
        this.description = description;
        this.createur = createur;
    }
     
   public Jeu() {
    }
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomjeu() {
        return nomjeu;
    }

    public void setNomjeu(String nomjeu) {
        this.nomjeu = nomjeu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateur() {
        return createur;
    }

    public void setCreateur(String createur) {
        this.createur = createur;
    }

    public Date getDatec() {
        return datec;
    }

    public void setDatec(Date datec) {
        this.datec = datec;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

     @Override
    public String toString() {
        return "Jeu{" + "id=" + id + ", nomjeu=" + nomjeu + ", description=" + description + ", createur=" + createur + ", datec=" + datec + ", image=" + image + '}';
    }

}
