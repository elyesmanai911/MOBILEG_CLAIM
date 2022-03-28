/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.Entities.Categorie;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author azizk
 */
public class ServiceCategorie {
    public ArrayList<Categorie> categories;
    public static ServiceCategorie instance ; 
    public boolean resultOK;
    private  ConnectionRequest req; 
 public static final String BASE_URL="http://127.0.0.1:8000/categorie";
 private ServiceCategorie() {
        req = new ConnectionRequest() ; 
         }
    
    public static ServiceCategorie getInstance() {
        if (instance == null)
        {
            instance = new ServiceCategorie();
        }
         return instance;
    }
 
 public ArrayList<Categorie> parseCategories(String jsonText){
        try {
            categories= new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> CategorieListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
           List< Map<String,Object>> list =(List< Map<String,Object>>) CategorieListJson.get("root");
           for ( Map<String,Object> obj: list){
             Categorie c = new Categorie();
             float id = Float.parseFloat(obj.get("id_categorie").toString());
             
             c.setId_categorie((int)id);
             c.setNom_categorie(obj.get("nom_categorie").toString());
             c.setType_categorie(obj.get("type_categorie").toString());
             
             
             categories.add(c);
         
        } }
           catch (IOException ex) {
//            Logger.getLogger(ServiceOeuvre.class.getName()).log(Level.SEVERE, null, ex);
             
        }
          return categories;
 }
     public ArrayList<Categorie> getAllOeuvres(){
          String url = BASE_URL+"/categ/Allcategories";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                categories = parseCategories(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return categories;
    }
        public void Supprimer(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(BASE_URL+"/deletecategoriejson/"+id);
        con.setPost(false);
        con.addResponseListener((evt) -> {
            System.out.println(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
       public boolean addcateg (TextField Nomcategorie,TextField Typecategorie)
    { 

       String url = BASE_URL+"/addcategoriejson/new?nom_categorie="+Nomcategorie.getText()+"&type_categorie="+Typecategorie.getText();
       req.setUrl(url);
       req.addResponseListener(new ActionListener<NetworkEvent>(){ 
           @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
             }
    });
        System.out.println(""+resultOK);
       NetworkManager.getInstance().addToQueue(req);
        return resultOK;
    }
           public boolean updateCateg (TextField Nomcategorie,TextField Typecategorie,int id)
    { 

       String url = BASE_URL+"/updatecategoriejson/"+id+"/edit?nom_categorie="+Nomcategorie.getText()+"&type_categorie="+Typecategorie.getText();
       req.setUrl(url);
       req.addResponseListener(new ActionListener<NetworkEvent>(){ 
           @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
             }
    });
        System.out.println(""+resultOK);
       NetworkManager.getInstance().addToQueue(req);
        return resultOK;
    }
}
