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
    private int idjeu;
    
    public Tournoi(int id, String nomtournoi,String description, Date datec, Date dateev,String image,int idjeu) {
        this.id = id;
        this.nomtournoi = nomtournoi;
        this.description = description;
        this.datec = datec;
        this.dateev = dateev;
        this.image = image;
        this.idjeu = idjeu;
    }
      public Tournoi(int id, String nomtournoi,String description,String image,int idjeu) {
        this.id = id;
        this.nomtournoi = nomtournoi;
        this.description = description;
        this.image = image;
        this.idjeu = idjeu;
    }
    
     public Tournoi(String nomtournoi,String description, Date datec, Date dateev,String image,int idjeu) {
        this.nomtournoi = nomtournoi;
        this.description = description;
        this.datec = datec;
        this.dateev = dateev;
        this.image = image;
        this.idjeu = idjeu;
    }
      public Tournoi(int id,String nomtournoi,String description) {
        this.id = id;
        this.nomtournoi = nomtournoi;
        this.description = description;
        }
       public Tournoi(String nomtournoi,String description,String image,Date dateev) {
        this.nomtournoi = nomtournoi;
        this.description = description;
        this.image = image;
        this.dateev=dateev;
        }
    public Tournoi(int id,int idjeu) {
        this.id = id;
        this.idjeu = idjeu;
    }

    public Tournoi() {
    }
    public int getIdJeu() {
        return idjeu;
    }

    public void setIdJeu(int idJeu) {
        this.idjeu = idJeu;
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

    public void setDatec(Date datec) {
        this.datec = datec;
    }

    public Date getDateev() {
        return dateev;
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
        return "Tournoi{" + "id=" + id + ", nomtournoi=" + nomtournoi + ", description=" + description + ", datec=" + datec + ", dateev=" + dateev + ", image=" + image + ", idjeu=" + idjeu + '}';
    }


}
