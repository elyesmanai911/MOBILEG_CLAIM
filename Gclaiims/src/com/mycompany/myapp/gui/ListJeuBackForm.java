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
import com.mycompany.myapp.entities.Equipe;
import com.mycompany.myapp.entities.SimpleUtilisateur;
import com.mycompany.myapp.entities.Jeu;
import com.mycompany.myapp.entities.Utilisateur;
import com.mycompany.myapp.entities.coach;
import com.mycompany.myapp.services.ServiceEquipe;
import com.mycompany.myapp.services.ServiceTournoi;
import com.mycompany.myapp.services.ServiceJeu;

import com.mycompany.myapp.services.ServiceUser;
import java.util.ArrayList;

/**
 *
 * @author souma
 */
public class ListJeuBackForm extends BaseFormBack{
public ArrayList<Jeu> Jeu;
    public ListJeuBackForm(Resources res) {
        super("ListCoach", BoxLayout.y());
  super.addSideMenu(res);
  Jeu=ServiceJeu.getInstance().getAllHotels();
Label labe=new Label("LISTE DES Jeus ");
addStringValue("",labe);
int i=1;
for (Jeu e :Jeu)
{ Label l=new Label("Jeu N°"+i++);
l.getAllStyles().setFgColor(0xFFA500);
addStringValue("",l);

Label xx =new Label("NOM Du Jeu");
xx.getAllStyles().setFgColor(0x00000);
Label qq =new Label("Description");
qq.getAllStyles().setFgColor(0x00000);
Label tt =new Label("Createur");
tt.getAllStyles().setFgColor(0x00000);

addStringValue(e.getNomjeu(),xx);
addStringValue(e.getDescription(),qq);
addStringValue(e.getCreateur(),tt);

Label Modiff =new Label("Modifier un Jeu");
Modiff.setUIID("NewsTopLine");
Style modiffStyle =new Style(Modiff.getUnselectedStyle());
modiffStyle.setFgColor(0xc24400);
FontImage mffontimage=FontImage.createMaterial(FontImage.MATERIAL_EDIT,modiffStyle);
Modiff.setIcon(mffontimage);
Modiff.setTextPosition(LEFT);
addStringValue("", BoxLayout.encloseY(Modiff));
Modiff.addPointerPressedListener(ll->new ModifJeuForm(res,e).show()); 
      Label Modif =new Label("Supprimer un Jeu");
Modif.setUIID("NewsTopLine");
Style modifStyle =new Style(Modif.getUnselectedStyle());
modifStyle.setFgColor(0xc24400);
FontImage mfontimage=FontImage.createMaterial(FontImage.MATERIAL_DELETE,modifStyle);
Modif.setIcon(mfontimage);
Modif.setTextPosition(LEFT);


addStringValue("", BoxLayout.encloseY(Modif));
Modif.addPointerPressedListener(new ActionListener() {
       public void actionPerformed(ActionEvent evt) {
                
                    ServiceJeu.getInstance().Supprimer(e.getId());

                };

            
       } );
Modif.addPointerPressedListener(ll->new ListJeuBackForm(res).show());  
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
