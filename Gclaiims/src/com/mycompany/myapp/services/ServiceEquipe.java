/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.services;

import com.mycompany.myapp.entities.Equipe;
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
 * @author souma
 */
public class ServiceEquipe {
  public ArrayList<Equipe> Equipes;

    public static ServiceEquipe instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceEquipe() {
        req = new ConnectionRequest();
    }

    public static ServiceEquipe getInstance() {
        if (instance == null) {
            instance = new ServiceEquipe();
        }
        return instance;
    }


public boolean addequipe(Equipe h) {
        System.out.println(h);
        System.out.println("********");
        String url = Statics.BASE_URL + "ajoutEquipeMobile?nomEquipe=" + h.getNomEquipe()+ "&Etat=" + h.getEtat()+ "&description=" + h.getDescription()+ "&chef=" + h.getChef()+ "&simpleutilisateurs=" + h.getIdUser();
        //String url = Statics.BASE_URL + "create";

      
     req.setUrl(url);
        req.setPost(false);

       req.addArgument("nomEquipe",h.getNomEquipe());
       req.addArgument("Etat", h.getEtat()+"");
    
        req.addArgument("description", h.getDescription()+"");
            req.addArgument("chef", h.getChef()+"");
             req.addArgument("simpleutilisateurs", h.getIdUser()+"");
         
       
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
public boolean updateequipe(Equipe h) {
        System.out.println(h);
        System.out.println("********");
        String url = Statics.BASE_URL + "updateEquipeMobile/"+h.getId()+"?nomEquipe=" + h.getNomEquipe()+ "&Etat=" + h.getEtat()+ "&description=" + h.getDescription()+ "&chef=" + h.getChef();
        //String url = Statics.BASE_URL + "create";

      
     req.setUrl(url);
        req.setPost(false);

       req.addArgument("nomEquipe",h.getNomEquipe());
       req.addArgument("Etat", h.getEtat()+"");
    
        req.addArgument("description", h.getDescription()+"");
            
       
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
public boolean rejoindreequipe(Equipe h) {
        System.out.println(h);
        System.out.println("********");
        String url = Statics.BASE_URL + "rejoindreequipeMOBILE/"+h.getId()+"?simpleutilisateurs="+ h.getIdUser();
        //String url = Statics.BASE_URL + "create";

      
     req.setUrl(url);
        req.setPost(false);

       req.addArgument("simpleutilisateurs",h.getIdUser()+"");
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
public ArrayList<Equipe> parseEquipes(String jsonText) {
        try {
            Equipes = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> EquipeListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) EquipeListJson.get("root");
            for(Map<String,Object> obj :list){
           
                Equipe h = new Equipe();

                float id = Float.parseFloat(obj.get("id").toString());
                h.setId((int) id);

                h.setNomEquipe(obj.get("nomEquipe").toString());
                h.setDescription(obj.get("description").toString());
                h.setChef(obj.get("chef").toString());
                h.setEtat(obj.get("Etat").toString());
            
               

              //  h.setDateCreation((Date) Date.parseDate(obj.get("DateCreation").toString()));
               

                Equipes.add(h);
            }

        } catch (IOException ex) {

        }
        return Equipes;
    }
      public ArrayList<Equipe> getAllHotels() {
        
 req = new ConnectionRequest();
        String url = Statics.BASE_URL +"equipe/AllEquipes";
        System.out.println("===>" + url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Equipes = parseEquipes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Equipes;
    }
    }

