/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author azizk
 */
public class ServiceProduit {
     public ArrayList<Produit> produits;
    public static ServiceProduit instance ; 
    public boolean resultOK;
    private  ConnectionRequest req; 
 public static final String BASE_URL="http://127.0.0.1:8000/produit";
 private ServiceProduit() {
        req = new ConnectionRequest() ; 
         }
    
    public static ServiceProduit getInstance() {
        if (instance == null)
        {
            instance = new ServiceProduit();
        }
         return instance;
    }
 
 public ArrayList<Produit> parseProduits(String jsonText){
        try {
            produits= new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> ProduitListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
           List< Map<String,Object>> list =(List< Map<String,Object>>) ProduitListJson.get("root");
           for ( Map<String,Object> obj: list){
             Produit p = new Produit();
             float id = Float.parseFloat(obj.get("id_produit").toString());
             float vu = Float.parseFloat(obj.get("nbr_vu").toString());
             float qte = Float.parseFloat(obj.get("Qte_produit").toString());

             p.setId_produit((int)id);
             p.setNom_produit(obj.get("nom_produit").toString());
             p.setDescription(obj.get("description").toString());
             p.setPrix_produit((double) obj.get("prix_produit"));
             p.setNbr_vu((int) vu);
             p.setQte_produit((int) qte);
             produits.add(p);
         
        } }
           catch (IOException ex) {
//            Logger.getLogger(ServiceOeuvre.class.getName()).log(Level.SEVERE, null, ex);
             
        }
          return produits;
 }
     public ArrayList<Produit> getAllOeuvres(){
          String url = BASE_URL+"/prod/Allproducts";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                produits = parseProduits(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produits;
    }
        public void Supprimer(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(BASE_URL+"/deleteproduitjson/"+id);
        con.setPost(false);
        con.addResponseListener((evt) -> {
            System.out.println(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
       public boolean addProd (TextField nomProduit,TextField Desc,TextField prixProd,TextField qte)
    { 

       String url = BASE_URL+"/addproduitjson/new?nom_produit="+nomProduit.getText()+"&description="+Desc.getText()+"&prix_produit="+prixProd.getText()+"&Qte_produit="+qte.getText();
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
           public boolean updateProd (TextField nomProduit,TextField Desc,TextField prixProd,TextField qte,int id)
    { 

       String url = BASE_URL+"/updateproduitjson/"+id+"/edit?nom_produit="+nomProduit.getText()+"&description="+Desc.getText()+"&prix_produit="+prixProd.getText()+"&Qte_produit="+qte.getText();
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
