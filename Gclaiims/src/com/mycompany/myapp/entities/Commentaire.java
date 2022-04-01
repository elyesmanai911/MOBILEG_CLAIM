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
public class Commentaire {
     private int id;
    private String commentaire;
    private String user;

    public Commentaire() {
    }

    public Commentaire(int id, String commentaire, String user) {
        this.id = id;
        this.commentaire = commentaire;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", commentaire=" + commentaire + ", user=" + user + '}';
    }
    
}
