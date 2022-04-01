/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.services.ServiceProduit;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.mycompany.myapp.entities.Cart;
import com.mycompany.myapp.entities.Panier;
import com.mycompany.myapp.gui.SessionManager;

import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author moham
 */
public class ServicePanier {

    private ConnectionRequest req;
    public ArrayList<Produit> produits;
    public ArrayList<Panier> paniers;
    public ArrayList<Cart> Carts;
    public ArrayList<Commande> commandes;
    public static ServicePanier instance = null;
    public boolean resultOK;

    private ServicePanier() {
        req = new ConnectionRequest();
    }

    public static ServicePanier getInstance() {
        if (instance == null) {
            instance = new ServicePanier();
        }
        return instance;
    }

    //Panier     
    public ArrayList<Panier> getCart() {
       // req = new ConnectionRequest();
        String url = Statics.BASE_URL + "Api/GetCart";
        System.out.println("===>" + url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                paniers = parsePanier(new String(req.getResponseData())+"");
                System.out.println("p=" + paniers);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return paniers;
    }

    // parseProduits
    public ArrayList<Panier> parsePanier(String jsonText) {
        try {
            System.out.println("parsePanier=" + jsonText);
            System.out.println("parsePanierToCharArray=" + jsonText.toCharArray());
            paniers = new ArrayList<>();
            JSONParser j = new JSONParser();

            Map<String, Object> PanierListesJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) PanierListesJson.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Panier p = new Panier();

                float id = Float.parseFloat(obj.get("id").toString());
                float quantite = Float.parseFloat(obj.get("quantite").toString());
                float total = Float.parseFloat(obj.get("total").toString());

                p.setId((int) id);
                p.setProduit(obj.get("produit").toString());
                p.setQuantite((int) quantite);
                p.setTotal((int) total);
                System.out.println("p and please work!" + p);
                paniers.add(p);

            }
        } catch (IOException ex) {
//            Logger.getLogger(ServiceOeuvre.class.getName()).log(Level.SEVERE, null, ex);

        }
        return paniers;
    }
    //Supprimer la Quantite de l'item de la panier

    public boolean CartDeleteItem(int id) {
        //req = new ConnectionRequest();
        this.req.setUrl(Statics.BASE_URL + "Api/Cartdelete?id=" + id);
         this.req.setPost(false);
         this.req.addResponseListener((evt) -> {
            System.out.println(req.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
return true;
    }

    //Ajouter la Quantite d'un item de la panier
    public boolean CartAddQuantityItem(int id) {
        //req = new ConnectionRequest();
        this.req.setUrl(Statics.BASE_URL + "Api/Cartplus?id=" + id);
         this.req.setPost(false);
         this.req.addResponseListener((evt) -> {
            System.out.println(req.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
return true;
    }

    //Diminuer la Quantite d'un item de la panier
    public boolean CartMinusQuantityItem(int id) {
        //req = new ConnectionRequest();
        this.req.setUrl(Statics.BASE_URL + "Api/Cartmoins?id=" + id);
        this.req.setPost(false);
        this.req.addResponseListener((evt) -> {
            System.out.println(this.req.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
return true;
    }

    public boolean AddItemToCart(int id) {
        //req = new ConnectionRequest();
        this.req.setUrl(Statics.BASE_URL + "Api/AddToCart?id=" + id);
        System.out.println(req.getUrl());
        this.req.setPost(false);
        this.req.addResponseListener((evt) -> { 
            System.out.println("here!!");
            System.out.println(this.req.getResponseData()+"");

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
return true;
    }

    public ArrayList<Commande> parseEvents(String jsonText) {
        try {

            commandes = new ArrayList<>();
            JSONParser j = new JSONParser();
            System.out.println("parseEvents=" + jsonText);
            System.out.println("parseEventsToCharArray=" + jsonText.toCharArray());
            Map<String, Object> commandesListesJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) commandesListesJson.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Commande c = new Commande();

                float id = Float.parseFloat(obj.get("id").toString());

                //float idUser = Float.parseFloat(obj.get("User").toString());
                c.setId((int) id);
                c.setDateAchat(obj.get("dateAchat").toString());
                c.setTotal(obj.get("total").toString());
                c.setLivrer(obj.get("livrer").toString());

                commandes.add(c);
            }

        } catch (IOException ex) {

        }
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
         */
        return commandes;
    }

    public ArrayList<Commande> getCommandeList() {
        req = new ConnectionRequest();
        req.setUrl(Statics.BASE_URL + "Api/AllCommandes");
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                commandes = parseEvents(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return commandes;
    }

    public boolean statusUpdate(int id) {

        req = new ConnectionRequest();
        req.setUrl(Statics.BASE_URL + "Api/statusUpdate?id=" + id);
        req.setPost(false);
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
     public boolean ConfirmCart() {

        req = new ConnectionRequest();
        req.setUrl(Statics.BASE_URL + "Api/NewCommande?User="+SessionManager.getId());
        req.setPost(false);
        System.out.println(req.getUrl());
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
    
     public boolean SendPdf(int id) {

        req = new ConnectionRequest();
        req.setUrl(Statics.BASE_URL + "Api/ToPDF?id="+id);
        req.setPost(false);
        System.out.println(req.getUrl());
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
}
