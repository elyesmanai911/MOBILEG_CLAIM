/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.services;

import com.mycompany.myapp.entities.Profil;
import com.codename1.ui.List;
import java.util.Date;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author yusse
 */
public class ServiceProfil {
  public ArrayList<Profil> Profils;

    public static ServiceProfil instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceProfil() {
        req = new ConnectionRequest();
    }

    public static ServiceProfil getInstance() {
        if (instance == null) {
            instance = new ServiceProfil();
        }
        return instance;
    }
public ArrayList<Profil> parseProfils(String jsonText) {
        try {
            Profils = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ProfilListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) ProfilListJson.get("root");
            for(Map<String,Object> obj :list){
           
                Profil h = new Profil();

                float id = Float.parseFloat(obj.get("id").toString());
                h.setId((int) id);

                h.setUsername(obj.get("username").toString());
                h.setDescription(obj.get("description").toString());
                h.setgame(obj.get("game").toString());
                    float numero = Float.parseFloat(obj.get("numero").toString());
                h.setnumero((int) numero);/*
               float user = Float.parseFloat(obj.get("user").toString());
                h.setuser((int) user);*/
             
           
                Profils.add(h);
            }

        } catch (IOException ex) {

        }
        return Profils;
    }
      public ArrayList<Profil> getAllcoachs() {
        
 req = new ConnectionRequest();
        String url = Statics.BASE_URL +"profil/AllProfils";
        System.out.println("===>" + url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Profils = parseProfils(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Profils;
    }
      
public boolean addprofil(Profil h) {
        System.out.println(h);
        System.out.println("********");
        String url = Statics.BASE_URL + "profil/ajoutProfilMobile?username=" + h.getUsername()+ "&description=" + h.getDescription()+ "&game=" + h.getgame()+ "&numero=" + h.getnumero()+ "&user=" + h.getuser();
        //String url = Statics.BASE_URL + "create";

      
     req.setUrl(url);
        req.setPost(false);

       req.addArgument("username",h.getUsername());
       req.addArgument("description", h.getDescription()+"");
    
        req.addArgument("game", h.getgame()+"");
            req.addArgument("numero", h.getnumero()+"");
             req.addArgument("user", h.getuser()+"");

       
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
public boolean modifprofil(Profil h) {
        System.out.println(h);
        System.out.println("********");
        String url = Statics.BASE_URL + "profil/updateProfilMobile/"+h.getId()+"?username=" + h.getUsername()+ "&description=" + h.getDescription()+ "&game=" + h.getgame()+ "&numero=" + h.getnumero();
        //String url = Statics.BASE_URL + "create";

      
     req.setUrl(url);
        req.setPost(false);

         req.addArgument("username",h.getUsername());
       req.addArgument("description", h.getDescription()+"");
    
        req.addArgument("game", h.getgame()+"");
            req.addArgument("numero", h.getnumero()+"");
       
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
 public void Supprimer(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(Statics.BASE_URL+"profil/deleteProfilMobile/"+id);
        con.setPost(false);
        con.addResponseListener((evt) -> {
            System.out.println(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
    

    }

