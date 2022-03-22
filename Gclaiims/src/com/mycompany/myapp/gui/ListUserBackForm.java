/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.SimpleUtilisateur;
import com.mycompany.myapp.entities.Tournoi;
import com.mycompany.myapp.services.ServiceTournoi;
import com.mycompany.myapp.services.ServiceUser;
import java.util.ArrayList;

/**
 *
 * @author souma
 */
public class ListUserBackForm extends BaseFormBack{
public ArrayList<SimpleUtilisateur> SimpleUtilisateur;
    public ListUserBackForm(Resources res) {
        super("ListUser", BoxLayout.y());
  super.addSideMenu(res);
  SimpleUtilisateur=ServiceUser.getInstance().getAllUsers();
Label labe=new Label("LISTE DES UTILISATEURS ");
addStringValue("",labe);
int i=1;
for (SimpleUtilisateur e :SimpleUtilisateur)
{ 
Label l=new Label("Utilisateur N°"+i++);
addStringValue("",l);
addButton(res.getImage("avatar.png"),"FULLNAME="+e.getFullname()+ "\n" +"Email="+e.getEmail()+ "\n" +"USERNAME="+e.getUsername()+ "\n" +"PASSWORD="+e.getVerifpassword()+ "\n", false, 26, 32);
      //  sp.setText(sp.getText()+"\n"+e.getDescription().toString());
  Label Modif =new Label("Supprimer un utilisateur");
Modif.setUIID("NewsTopLine");
Style modifStyle =new Style(Modif.getUnselectedStyle());
modifStyle.setFgColor(0xc24400);
FontImage mfontimage=FontImage.createMaterial(FontImage.MATERIAL_DELETE,modifStyle);
Modif.setIcon(mfontimage);
Modif.setTextPosition(LEFT);


addStringValue("", BoxLayout.encloseY(Modif));
Modif.addPointerPressedListener(new ActionListener() {
       public void actionPerformed(ActionEvent evt) {
                
                    ServiceUser.getInstance().Supprimer(e.getId());

                };

            
       } );
Modif.addPointerPressedListener(ll->new ListCoachBackForm(res).show());
      
}

}
  private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));

    }
private void addButton(Image img, String title, boolean liked, int likeCount, int commentCount) {
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
       TextArea ta = new TextArea(title);
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);

       Label likes = new Label(likeCount + " Likes  ", "NewsBottomLine");
       likes.setTextPosition(RIGHT);
       if(!liked) {
           FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
       } else {
           Style s = new Style(likes.getUnselectedStyle());
           s.setFgColor(0xff2d55);
           FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
           likes.setIcon(heartImage);
       }
       Label comments = new Label(commentCount + " Comments", "NewsBottomLine");
       FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);
       
       
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ta,
                       BoxLayout.encloseX(likes, comments)
               ));
       add(cnt);
       image.addActionListener(e -> ToastBar.showMessage(title, FontImage.MATERIAL_INFO));
   }
}
