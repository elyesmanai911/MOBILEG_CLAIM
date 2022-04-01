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
import com.mycompany.myapp.entities.Rdv;
import com.mycompany.myapp.entities.Utilisateur;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author souma
 */
public class ServiceRdv {

    public ArrayList<Rdv> Rdv;
    public static ServiceRdv instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceRdv() {
        req = new ConnectionRequest();
    }

    public static ServiceRdv getInstance() {
        if (instance == null) {
            instance = new ServiceRdv();
        }
        return instance;
    }

    public boolean addRdv(Rdv h) {
        System.out.println(h);
        System.out.println("********");
        String url = Statics.BASE_URL + "rdv/ajoutRdvMobile?user=" + h.getuser() + "&coach=" + h.getcoach() + "&date=" + h.getdate();
        //String url = Statics.BASE_URL + "create";

        req.setUrl(url);
        req.setPost(false);

        req.addArgument("user", h.getuser());
        req.addArgument("coach", h.getcoach() + "");

        req.addArgument("date", h.getdate() + "");

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

    public boolean updateRdv(Rdv h) {
        System.out.println(h);
        System.out.println("********");
        String url = Statics.BASE_URL + "rdv/updateEquipeMobile/" + h.getId() + "?date=" + h.getdate();
        //String url = Statics.BASE_URL + "create";

        req.setUrl(url);
        req.setPost(false);

        req.addArgument("date", h.getdate() + "");

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

    public ArrayList<Rdv> parseRdvs(String jsonText) {
        try {
            System.out.println(jsonText);
            Rdv = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> RdvListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) RdvListJson.get("root");
            for (Map<String, Object> obj : list) {
                System.out.println("daaaaaate");

                Rdv h = new Rdv();

                float id = Float.parseFloat(obj.get("id").toString());
                h.setId((int) id);

                /*  h.setdate(obj.get("date"));
                h.setcoach(obj.get("description").toString());*/

                h.setcoach(obj.get("coachname").toString());
                h.setuser(obj.get("username").toString());
                h.setdate(obj.get("date").toString());

                //  h.setDateCreation((Date) Date.parseDate(obj.get("DateCreation").toString()));
                Rdv.add(h);
            }

        } catch (IOException ex) {

        }
        return Rdv;
    }

    public ArrayList<Rdv> getAllRdv() {

        req = new ConnectionRequest();
        String url = Statics.BASE_URL + "rdv/AllRdvs";
        System.out.println("===>" + url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Rdv = parseRdvs(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Rdv;
    }

    public void Supprimer(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(Statics.BASE_URL + "rdv/deleteRdvMobile/" + id);
        con.setPost(false);
        con.addResponseListener((evt) -> {
            System.out.println(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }

}
