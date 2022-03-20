/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.SimpleUtilisateur;
import com.mycompany.myapp.entities.Utilisateur;
import com.mycompany.myapp.gui.SessionManager;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author souma
 */
public class ServiceUser {
    public ArrayList<SimpleUtilisateur> users;
    
    public static ServiceUser instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    
     private ServiceUser() {
         req = new ConnectionRequest();
    }
     
     public static ServiceUser getInstance() {
        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
    }
public boolean adduser(SimpleUtilisateur h) {
        System.out.println(h);
        System.out.println("********");
        String url = Statics.BASE_URL + "addusermobile?username=" + h.getUsername() + "&password=" + h.getPassword() + "&verifpassword=" + h.getVerifpassword() + "&email=" + h.getEmail() + "&fullname=" + h.getFullname();
        //String url = Statics.BASE_URL + "create";

        req.setUrl(url);
        req.setPost(false);

       req.addArgument("username",h.getUsername());
       req.addArgument("password", h.getPassword()+"");
    
        req.addArgument("verifpassword", h.getVerifpassword()+"");
            req.addArgument("email", h.getEmail()+"");
             req.addArgument("fullname", h.getFullname()+"");
         
       
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
public boolean updateuser(SimpleUtilisateur h) {
        System.out.println(h);
        System.out.println("********");

        String url = Statics.BASE_URL + "updateusermobile/"+SessionManager.getId()+"?username=" + h.getUsername() + "&password=" + h.getPassword() + "&verifpassword=" + h.getVerifpassword() + "&email=" + h.getEmail() + "&fullname=" + h.getFullname();
        //String url = Statics.BASE_URL + "create";

        req.setUrl(url);
        req.setPost(false);

       req.addArgument("username",h.getUsername());
       req.addArgument("password", h.getPassword()+"");
    
        req.addArgument("verifpassword", h.getVerifpassword()+"");
            req.addArgument("email", h.getEmail()+"");
             req.addArgument("fullname", h.getFullname()+"");
         
       
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

       public ArrayList<SimpleUtilisateur> parseEvents(String jsonText){
        try {
            users=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
             
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                SimpleUtilisateur u = new SimpleUtilisateur();
                float id = Float.parseFloat(obj.get("id").toString());
                u.setId((int)id);
                u.setUsername(obj.get("username").toString());
                u.setPassword(obj.get("password").toString());
                u.setVerifpassword(obj.get("verifpassword").toString()); 
                u.setEmail(obj.get("email").toString()); 
 u.setFullname(obj.get("fullname").toString()); 
                users.add(u);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return users;
    }
    
    public ArrayList<SimpleUtilisateur> getAllUsers(){
        String url = Statics.BASE_URL+"/listUsers";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseEvents(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;
    }
 public void Supprimer(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(Statics.BASE_URL+"/deleteUser/"+id);
        con.setPost(false);
        con.addResponseListener((evt) -> {
            System.out.println(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }

    public boolean addUser(SimpleUtilisateur h) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
