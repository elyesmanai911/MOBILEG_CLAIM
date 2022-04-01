/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.services;

import com.mycompany.myapp.entities.Jeu;
import com.codename1.ui.List;
import java.util.Date;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Jeu;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import com.mycompany.myapp.entities.Tournoi;

/**
 *
 * @author souma
 */
public class ServiceJeu {
  public ArrayList<Jeu> Jeus;

    public static ServiceJeu instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceJeu() {
        req = new ConnectionRequest();
    }

    public static ServiceJeu getInstance() {
        if (instance == null) {
            instance = new ServiceJeu();
        }
        return instance;
    }
    public boolean addjeu(Jeu h) {
        System.out.println(h);
        System.out.println("********");
        String url = Statics.BASE_URL + "ajoutJeuMobile/?nomjeu=" + h.getNomjeu()+  "&description=" + h.getDescription()+  "&createur=" + h.getCreateur() ;
        //String url = Statics.BASE_URL + "create";

      
     req.setUrl(url);
        req.setPost(false);

       req.addArgument("nomJeu",h.getNomjeu());
    
        req.addArgument("description", h.getDescription()+"");
         
    req.addArgument("createur", h.getCreateur()+"");
         
       
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
public boolean updatejeu(Jeu h) {
        System.out.println(h);
        System.out.println("********");
        String url = Statics.BASE_URL + "updateJeuMobile/"+h.getId()+"?nomjeu=" + h.getNomjeu()+ "&description=" + h.getDescription()+ "&createur=" + h.getCreateur()/*+ "&idjeu=" + h.getIdJeu()*/;
        //String url = Statics.BASE_URL + "create";

      
     req.setUrl(url);
        req.setPost(false);

       req.addArgument("nomjeu",h.getNomjeu());
      
    
        req.addArgument("description", h.getDescription()+"");
             req.addArgument("Createur", h.getImage()+"");
       
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
public ArrayList<Jeu> parseJeus(String jsonText) {
        try {
            Jeus = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> JeuListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) JeuListJson.get("root");
            for(Map<String,Object> obj :list){
           
                Jeu h = new Jeu();

                float id = Float.parseFloat(obj.get("id").toString());
                h.setId((int) id);

                h.setNomjeu(obj.get("nomjeu").toString());
                h.setDescription(obj.get("description").toString());
                h.setCreateur(obj.get("createur").toString());
     
            
               

               

                Jeus.add(h);
            }

        } catch (IOException ex) {

        }
        return Jeus;
    }
      public ArrayList<Jeu> getAllHotels() {
        
 req = new ConnectionRequest();
        String url = Statics.BASE_URL +"Jeu/AllJeus";
        System.out.println("===>" + url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Jeus = parseJeus(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Jeus;
    }
       public void Supprimer(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(Statics.BASE_URL+"deleteJeuMobile/"+id);
        con.setPost(false);
        con.addResponseListener((evt) -> {
            System.out.println(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
    }

