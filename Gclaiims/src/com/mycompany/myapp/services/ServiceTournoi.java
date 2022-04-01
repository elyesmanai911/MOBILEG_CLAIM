/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.services;

import com.mycompany.myapp.entities.Tournoi;
import com.codename1.ui.List;
import java.util.Date;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Tournoi;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author souma
 */
public class ServiceTournoi {
  public ArrayList<Tournoi> Tournois;

    public static ServiceTournoi instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceTournoi() {
        req = new ConnectionRequest();
    }

    public static ServiceTournoi getInstance() {
        if (instance == null) {
            instance = new ServiceTournoi();
        }
        return instance;
    }
    public boolean addtournoi(Tournoi h) {
        System.out.println(h);
        System.out.println("********");
        String url = Statics.BASE_URL + "ajoutTournoiMobile/?nomTournoi=" + h.getNomTournoi()+  "&description=" + h.getDescription()+ "&dateev=" + h.getDateev()+  "&image=" + h.getImage() ;
        //String url = Statics.BASE_URL + "create";

      
     req.setUrl(url);
        req.setPost(false);
       req.addArgument("nomTournoi",h.getNomTournoi());
        req.addArgument("description", h.getDescription()+"");
    req.addArgument("image", h.getImage()+"");
    req.addArgument("Dateev", h.getDateev()+"");
         
       
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
public boolean updatetournoi(Tournoi h) {
        System.out.println(h);
        System.out.println("********");
        String url = Statics.BASE_URL + "updateTournoiMobile/"+h.getId()+"?nomTournoi=" + h.getNomTournoi()+ "&description=" + h.getDescription()+ "&image=" + h.getImage();
        //String url = Statics.BASE_URL + "create";

      
     req.setUrl(url);
        req.setPost(false);

       req.addArgument("nomTournoi",h.getNomTournoi());
      
    
        req.addArgument("description", h.getDescription()+"");
             req.addArgument("image", h.getImage()+"");
        
       
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
public ArrayList<Tournoi> parseTournois(String jsonText) {
        try {
            Tournois = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> TournoiListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) TournoiListJson.get("root");
            for(Map<String,Object> obj :list){
                Tournoi h = new Tournoi();
                float id = Float.parseFloat(obj.get("id").toString());
                h.setId((int) id);
                h.setNomTournoi(obj.get("nomtournoi").toString());
                h.setDescription(obj.get("description").toString());
                h.setImage(obj.get("image").toString());          
                Tournois.add(h);
                System.out.println(h);
            }

        } catch (IOException ex) {

        }
        return Tournois;
    }
      public ArrayList<Tournoi> getAllHotels() {
        
 req = new ConnectionRequest();
        String url = Statics.BASE_URL +"Tournoi/AllTournois";
        System.out.println("===>" + url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Tournois = parseTournois(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Tournois;
    }
       public void Supprimer(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(Statics.BASE_URL+"deleteTournoiMobile/"+id);
        con.setPost(false);
        con.addResponseListener((evt) -> {
            System.out.println(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
         public void rejoindre(int id,int idd) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(Statics.BASE_URL+"rejoindreTournoiMOBILE/"+id+"?simpleutilisateurs=" + idd);
        con.setPost(false);
        con.addResponseListener((evt) -> {
            System.out.println(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
    }

