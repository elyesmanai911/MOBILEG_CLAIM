/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.myapp.services;

import com.mycompany.myapp.entities.Equipe;
//import com.codename1.ui.List;
import java.util.Date;
import com.codename1.io.CharArrayReader;
import com.codename1.ui.TextField;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Article;
import com.mycompany.myapp.entities.Commentaire;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;


/**
 *
 * @author User
 */
public class ServiceArticle {
    public ArrayList<Article> articles;
public ArrayList<Commentaire> commentaire;
    public static ServiceArticle instance ; 
    public boolean resultOK;
    private  ConnectionRequest req; 
 public static final String BASE_URL="http://127.0.0.1:8000/";
 private ServiceArticle() {
        req = new ConnectionRequest() ; 
         }
    
    public static ServiceArticle getInstance() {
        if (instance == null)
        {
            instance = new ServiceArticle();
        }
         return instance;
    }
 
 public ArrayList<Article> parseArticle(String jsonText){
        try {
            articles= new ArrayList<>();

            JSONParser j = new JSONParser();
            Map<String,Object> ArticleListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
           List< Map<String,Object>> list =(List< Map<String,Object>>) ArticleListJson.get("root");
           for ( Map<String,Object> obj: list){
             Article a = new Article();
             float id = Float.parseFloat(obj.get("id").toString());
             float vu = Float.parseFloat(obj.get("nbr_vu").toString());

             a.setId((int)id);
             a.setTitre(obj.get("titre").toString());
             a.setDescription(obj.get("description").toString());
             a.setImage( obj.get("image").toString());
             a.setNbr_vu((int) vu);
             articles.add(a);
         
        } }
           catch (IOException ex) {
//            Logger.getLogger(ServiceOeuvre.class.getName()).log(Level.SEVERE, null, ex);
             
        }
          return articles;
 }
  
     public ArrayList<Article> getAllArticle(){
          String url = BASE_URL+"article/AllArticles";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                articles = parseArticle(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println(articles);
        return articles;
    }
        public void SupprimerArticle(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(BASE_URL+"article/deletearticlejson/"+id);
        con.setPost(false);
        con.addResponseListener((evt) -> {
            System.out.println(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
       public boolean addarticle (TextField Titre ,TextField Desc,TextField img)
    { 

       String url = BASE_URL+"article/addarticlesjson/new?titre="+Titre.getText()+"&description="+Desc.getText()+"&image="+img.getText();
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
           public boolean updateArticle (TextField Titre ,TextField Desc,TextField img ,int id)
    { 

       String url = BASE_URL+"article/editarticlejson/"+id+"/edit?titre="+Titre.getText()+"&description="+Desc.getText()+"&image="+img.getText();
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
               public ArrayList<Article> getaffichearticlejson(int id){
          String url = BASE_URL+"article/affichearticlejson/"+id+"/affiche";
        
        req.setUrl(url);
        req.setPost(false);
       

                System.out.println(articles);
        NetworkManager.getInstance().addToQueueAndWait(req);
        return articles;
    }
               public ArrayList<Commentaire> parseCommentaire(String jsonText){
        try {
           
            commentaire = new ArrayList<>();

            JSONParser j = new JSONParser();
            Map<String,Object> CommentaireListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
           List< Map<String,Object>> list =(List< Map<String,Object>>) CommentaireListJson.get("root");
           for ( Map<String,Object> obj: list){
             Commentaire a = new Commentaire();
             float id = Float.parseFloat(obj.get("id").toString());
             a.setId((int)id);
             a.setCommentaire(obj.get("commentaire").toString());
             a.setUser(obj.get("user").toString());
             commentaire.add(a);
         
        } }
           catch (IOException ex) {
//            Logger.getLogger(ServiceOeuvre.class.getName()).log(Level.SEVERE, null, ex);
             
        }
          return commentaire;
 }
     public ArrayList<Commentaire> getAffcheCommentaire(Article e) {
        
        String url = Statics.BASE_URL +"commentaire/AffcheCommentaire/"+e.getId();
        System.out.println("===>" + url);
        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                commentaire = parseCommentaire(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
       

                System.out.println(commentaire);
        NetworkManager.getInstance().addToQueueAndWait(req);
        return commentaire;
    }
     public ArrayList<Commentaire> getAllCommentaires(){
          String url = BASE_URL+"commentaire/com/AllCommentaires";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                commentaire = parseCommentaire(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println(commentaire);
        return commentaire;
    }
      public boolean addCom (TextField commentaire ,TextField user , Article a)
    { 

       String url = BASE_URL+"commentaire/com/addCommobile/"+a.getId()+"/new?commentaire="+commentaire.getText()+"&user="+user.getText();
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
