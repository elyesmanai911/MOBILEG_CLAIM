/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author User
 */
public class Article {
  private int id;
  private String titre;
  private String description;
  private String image;
  private int nbr_vu;

    public Article() {
    }

    public Article(int id, String titre, String description, String image, int nbr_vu) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.image = image;
        this.nbr_vu = nbr_vu;
    }

    public Article(int id, String titre, String description, String image) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getNbr_vu() {
        return nbr_vu;
    }

    public void setNbr_vu(int nbr_vu) {
        this.nbr_vu = nbr_vu;
    }

    @Override
    public String toString() {
        return "Article{" + "id=" + id + ", titre=" + titre + ", description=" + description + ", image=" + image + ", nbr_vu=" + nbr_vu + '}';
    }
        
}
