/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author moham
 */
public class Commande {
    private int id;
    private int User;
    private String total;
    private String dateAchat;
    private String livrer;

    public Commande() {
    }

    public int getId() {
        return id;
    }

    public Commande(int id, int User, String total, String dateAchat, String livrer) {
        this.id = id;
        this.User = User;
        this.total = total;
        this.dateAchat = dateAchat;
        this.livrer = livrer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser() {
        return User;
    }

    public void setUser(int User) {
        this.User = User;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(String dateAchat) {
        this.dateAchat = dateAchat;
    }

    public String getLivrer() {
        return livrer;
    }

    public void setLivrer(String livrer) {
        this.livrer = livrer;
    }

}
