/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author yusse
 */
import java.util.Date;
public class Profil {
    private int id;
    private String Description;
    private String Username;
    private String game;
    private int numero;
    private int user;
    
    

    public Profil(String Description, String Username, String game, int numero,int user) {
        this.Description = Description;
        this.Username = Username;
        this.game = game;
        this.numero = numero;
        this.user = user;
    }

    public Profil(int id, String Description, String Username, String game, int numero, int user) {
        
        this.id = id;
        this.Description = Description;
        this.Username = Username;
         this.game = game;
        this.numero = numero;
        this.user = user;
      
    }
     public Profil( int id,String Description, String Username, String game, int numero) {
     
   
               this.id = id;

       
        this.Description = Description;
        this.Username = Username;
         this.game = game;
        this.numero = numero;
       
      
    }

    public Profil() {
     
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }
public String getgame() {
        return game;
    }

    public void setgame(String game) {
        this.game = game;
    }
    public int getnumero() {
        return numero;
    }

    public void setnumero(int numero) {
        this.numero = numero;
    }
    public int getuser() {
        return user;
    }

    public void setuser(int user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Profil{" + "id=" + id + ", Description=" + Description + ", Username=" + Username + "game=" + game + "numero=" + numero + "user=" + user +   '}';
    }
    
}