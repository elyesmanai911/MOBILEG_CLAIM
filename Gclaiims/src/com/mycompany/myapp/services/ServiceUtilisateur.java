/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.gui.BackForm;
import com.mycompany.myapp.gui.ProfileForm;

import com.mycompany.myapp.gui.SessionManager;
import com.mycompany.myapp.gui.WalkthruForm;
import com.mycompany.myapp.gui.addEquipeForm;
import com.mycompany.myapp.utils.Statics;
import java.util.Map;
import java.util.Vector;

public class ServiceUtilisateur {

    //singleton 
    public static ServiceUtilisateur instance = null;

    public static boolean resultOk = true;
    String json;

    //initilisation connection request 
    private ConnectionRequest req;

    public static ServiceUtilisateur getInstance() {
        if (instance == null) {
            instance = new ServiceUtilisateur();
        }
        return instance;
    }

    public ServiceUtilisateur() {
        req = new ConnectionRequest();

    }

    /*
    //Signup
    public void signup(TextField username,TextField password,TextField email,TextField confirmPassword, ComboBox<String> roles , Resources res ) {
        
     
        
        String url = Statics.BASE_URL+"/user/signup?username="+username.getText().toString()+"&email="+email.getText().toString()+
                "&password="+password.getText().toString()+"&roles="+roles.getSelectedItem().toString();
        
        req.setUrl(url);
       
        //Control saisi
        if(username.getText().equals(" ") && password.getText().equals(" ") && email.getText().equals(" ")) {
            
            Dialog.show("Erreur","Veuillez remplir les champs","OK",null);
            
        }
        
        //hethi wa9t tsir execution ta3 url 
        req.addResponseListener((e)-> {
         
            //njib data ly7atithom fi form 
            byte[]data = (byte[]) e.getMetaData();//lazm awl 7aja n7athrhom ke meta data ya3ni na5o id ta3 kol textField 
            String responseData = new String(data);//ba3dika na5o content 
            
            System.out.println("data ===>"+responseData);
        }
        );
        
        
        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
        
            
        
    }*/
    //SignIn
    public void signin(TextField username, TextField password, Resources rs) {

        String url = Statics.BASE_URL + "signinmobile?username=" + username.getText().toString() + "&password=" + password.getText().toString();
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);

        req.addResponseListener((e) -> {

            JSONParser j = new JSONParser();

            String json = new String(req.getResponseData()) + "";

            try {

                if (json.equals("failed")) {
                    Dialog.show("Echec d'authentification", "Username ou mot de passe ??ronn??", "OK", null);
                } else {

                    System.out.println("data ==" + json);

                    Map<String, Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    if (user.get("roles").toString().equals("[ROLE_COACH]")) {
                        //Session coach
                        float id = Float.parseFloat(user.get("id").toString());
                        SessionManager.setId((int) id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i

                        SessionManager.setPassowrd(user.get("password").toString());
                        SessionManager.setUserName(user.get("username").toString());
                        SessionManager.setEmail(user.get("email").toString());

                        SessionManager.setFullname(user.get("specialite").toString());
                    } else {
                        //Session user
                        float id = Float.parseFloat(user.get("id").toString());
                        SessionManager.setId((int) id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i

                        SessionManager.setPassowrd(user.get("password").toString());
                        SessionManager.setUserName(user.get("username").toString());
                        SessionManager.setEmail(user.get("email").toString());
                        SessionManager.setFullname(user.get("fullname").toString());
                    }
                    if (user.size() > 0 && user.get("roles").toString().equals("[ROLE_ADMIN]")) // l9a user
                    {
                        new BackForm(rs).show();
                    } else {
                        new ProfileForm(rs).show();
                    }

                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    //heki 5dmtha taw nhabtha ala description
    public String getPasswordByEmail(String email, Resources rs) {

        String url = Statics.BASE_URL + "reset-password/emailMobile?email=" + email;
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);

        req.addResponseListener((e) -> {

            JSONParser j = new JSONParser();

            json = new String(req.getResponseData()) + "";

            try {

                System.out.println("data ==" + json);

                Map<String, Object> password = j.parseJSON(new CharArrayReader(json.toCharArray()));

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
        return json;
    }

}
