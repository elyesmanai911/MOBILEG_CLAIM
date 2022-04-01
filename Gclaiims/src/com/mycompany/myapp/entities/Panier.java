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
public class Panier {

    private int id;
    private String produit;
    private int quantite;
    private int total;

    public Panier() {
    }

    public Panier(int id, String produit, int quantite, int total) {
        this.id = id;
        this.produit = produit;
        this.quantite = quantite;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public String getProduit() {
        return produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public int getTotal() {
        return total;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    

}
