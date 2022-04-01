/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.services.ServicePanier;
import java.util.ArrayList;

/**
 *
 * @author moham
 */
public class ListCommandeBackForm extends BaseFormBack{
    public ArrayList<Commande> commande;
    public ListCommandeBackForm(Resources res) {
        super("ListCommande", BoxLayout.y());
  super.addSideMenu(res);
  commande=ServicePanier.getInstance().getCommandeList();
Label labe=new Label("LISTE DES Commandes ");
labe.getAllStyles().setFgColor(0xc24400);
addStringValue("",labe);
int i=1;
for (Commande c :commande)
{ 
Label l=new Label("Commande N°"+i++);
l.getAllStyles().setFgColor(0x000000);
addStringValue("",l);
addButton(res.getImage("avatar.png"),"Specialite="+c.getId()+ "\n" +"Email="+c.getDateAchat()+ "\n" +"Livrer="+c.getLivrer()+"\n" +"Total="+c.getTotal()+ "\n", false, 26, 32);
      //  sp.setText(sp.getText()+"\n"+e.getDescription().toString());

  Label Modif =new Label("Update Status");
Modif.setUIID("NewsTopLine");
Style modifStyle =new Style(Modif.getUnselectedStyle());
modifStyle.setFgColor(0xc24400);
FontImage mfontimage=FontImage.createMaterial(FontImage.MATERIAL_DELETE,modifStyle);
Modif.setIcon(mfontimage);
Modif.setTextPosition(LEFT);


addStringValue("", BoxLayout.encloseY(Modif));
Modif.addPointerPressedListener(new ActionListener() {
       public void actionPerformed(ActionEvent evt) {
                
                    ServicePanier.getInstance().statusUpdate(c.getId());

                };

            
       } );
Modif.addPointerPressedListener(ll->new ListCommandeBackForm(res).show());
Label PDF =new Label("Send Pdf");
PDF.setUIID("NewsTopLine");
Style modifStyles =new Style(PDF.getUnselectedStyle());
modifStyle.setFgColor(0xc24400);
FontImage mfontimages=FontImage.createMaterial(FontImage.MATERIAL_DELETE,modifStyles);
PDF.setIcon(mfontimages);
PDF.setTextPosition(LEFT);


addStringValue("", BoxLayout.encloseY(PDF));
PDF.addPointerPressedListener(new ActionListener() {
       public void actionPerformed(ActionEvent evt) {
                
                    ServicePanier.getInstance().SendPdf(c.getId());

                };

            
       } );
PDF.addPointerPressedListener(ll->new ListCommandeBackForm(res).show());
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
