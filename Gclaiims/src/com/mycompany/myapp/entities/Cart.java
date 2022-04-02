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
public class Cart {
    private static int idCart;
    private static int quantiteCart;

    public Cart() {
    }

    public Cart(int id, int quantite) {
        this.idCart = id;
        this.quantiteCart = quantite;
    }

    public int getId() {
        return idCart;
    }

    public int getQuantite() {
        return quantiteCart;
    }

    public void setId(int id) {
        this.idCart = id;
    }

    public void setQuantite(int quantite) {
        this.quantiteCart = quantite;
    }
    
}
