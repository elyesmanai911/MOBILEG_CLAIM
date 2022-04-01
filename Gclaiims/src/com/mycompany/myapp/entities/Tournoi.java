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
public class Tournoi {

    private int id;
    private String nomtournoi;
    private String description;
    private Date datec;
    private Date dateev;
    private String image;
    
    public Tournoi(int id, String nomtournoi,String description, Date datec, Date dateev,String image) {
        this.nomtournoi = nomtournoi;
        this.description = description;
        this.datec = datec;
        this.dateev = dateev;
        this.image = image;
    }

    public Tournoi() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomTournoi() {
        return nomtournoi;
    }

    public void setNomTournoi(String nomTournoi) {
        this.nomtournoi = nomTournoi;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDatec() {
        return datec;
    }

    public void setDateev(Date dateev) {
        this.dateev = dateev; 
    }

    public String getImage() {
        return image;
    }

    public void setImage(String Image) {
        this.image = image;
    }

 @Override
    public String toString() {
        return "Tournoi{" + "id=" + id + ", nomEquipe=" + nomtournoi + ", description=" + description + ", dateCreation=" + datec + ", Etat=" + dateev + ", image=" + image + '}';
    }

}
