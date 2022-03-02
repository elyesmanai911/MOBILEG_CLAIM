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
public class Equipe {

    private int id;
    private String nomEquipe;
    private String Description;
    private Date dateCreation;
    private String Etat;
    private String chef;

    public Equipe(String nomEquipe, String description, Date dateCreation, String Etat, String chef) {
        this.nomEquipe = nomEquipe;
        this.Description = description;
        this.dateCreation = dateCreation;
        this.Etat = Etat;
        this.chef = chef;
    }

    public Equipe() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomEquipe() {
        return nomEquipe;
    }

    public void setNomEquipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getEtat() {
        return Etat;
    }

    public void setEtat(String Etat) {
        this.Etat = Etat;
    }

    public String getChef() {
        return chef;
    }

    public void setChef(String chef) {
        this.chef = chef;
    }
 @Override
    public String toString() {
        return "Equipe{" + "id=" + id + ", nomEquipe=" + nomEquipe + ", description=" + Description + ", dateCreation=" + dateCreation + ", Etat=" + Etat + ", chef=" + chef + '}';
    }

}
