/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author souma
 */
public class SimpleUtilisateur extends Utilisateur {
     protected String fullname;

    public SimpleUtilisateur() {
      
    }

    public SimpleUtilisateur(int id, String username, String password, String verifpassword, String email,String fullname) {
        super(id, username, password, verifpassword, email);
        this.fullname = fullname;
    }
  public SimpleUtilisateur(String username, String password, String verifpassword, String email,String fullname) {
        super(username, password, verifpassword, email);
        this.fullname = fullname;
    }
    public SimpleUtilisateur(int id, String username, String password, String verifpassword, String email, String roles, boolean role, boolean isVerfied,String fullname) {
        super(id,username, password, verifpassword, email, roles, role, isVerfied);
        this.fullname = fullname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
@Override
    public String toString() {
        return "User:" + ", username=" + username + ", email=" + email + ", fullname=" + fullname;
    }

}
