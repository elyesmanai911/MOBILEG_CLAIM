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
public class LigneCommande {

    private int id;
    private int Produit;
    private int quantite;
    private int Commande;

    public LigneCommande() {
    }

    public LigneCommande(int id, int Produit, int quantite, int Commande) {
        this.id = id;
        this.Produit = Produit;
        this.quantite = quantite;
        this.Commande = Commande;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduit() {
        return Produit;
    }

    public void setProduit(int Produit) {
        this.Produit = Produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getCommande() {
        return Commande;
    }

    public void setCommande(int Commande) {
        this.Commande = Commande;
    }

}
