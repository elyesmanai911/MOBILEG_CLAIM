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
public class Rdv {

    private int id;
    private String coach;
    private String user;
    private String date;
    
    public Rdv(int id, String coach, String user,String date) {
           this.id = id;
        this.coach = coach;
        this.user = user;
        this.date = date;
       
    }
      public Rdv(int id, String date) {
           this.id = id;
       
        this.date = date;
       
    }

 public Rdv() {
     
    }
    
    public Rdv( String coach,String user, String date) {
         this.coach = coach;
        this.user = user;
        this.date = date;
       
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getdate() {
        return date;
    }

    public void setdate(String date) {
        this.date = date;
    }

    public String getcoach() {
        return coach;
    }

    public void setcoach(String coach) {
        this.coach = coach;
    }

    public String getuser() {
        return user;
    }

    public void setuser(String user) {
        this.user = user;
    }

  
 @Override
    public String toString() {
        return "Equipe{" + "id=" + id + ", date=" + date + ", coach=" + coach + ", user=" + user +   '}';
    }

}
