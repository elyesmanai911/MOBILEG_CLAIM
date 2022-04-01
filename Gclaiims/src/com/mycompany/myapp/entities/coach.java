/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author souma
 */
public class coach extends Utilisateur {
    String specialite;

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public coach(String specialite) {
        this.specialite = specialite;
    }
 public coach() {
        
    }

    public coach(String username, String password, String verifpassword, String email,String specialite) {
        super(username, password, verifpassword, email);
        this.specialite = specialite;
    }

    public coach(int id, String username, String password, String verifpassword, String email,String specialite) {
        super(id, username, password, verifpassword, email);
        this.specialite = specialite;
    }

    public coach(int id, String username, String password, String verifpassword, String email, String roles, boolean role, boolean isVerfied,String specialite) {
        super(id, username, password, verifpassword, email, roles, role, isVerfied);
        this.specialite = specialite;
    }

}
