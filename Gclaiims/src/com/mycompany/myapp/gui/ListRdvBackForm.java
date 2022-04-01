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
import com.mycompany.myapp.entities.Rdv;
import com.mycompany.myapp.entities.SimpleUtilisateur;
import com.mycompany.myapp.entities.Tournoi;
import com.mycompany.myapp.services.ServiceProfil;
import com.mycompany.myapp.services.ServiceRdv;
import com.mycompany.myapp.services.ServiceTournoi;
import com.mycompany.myapp.services.ServiceUser;
import java.util.ArrayList;

/**
 *
 * @author souma
 */
public class ListRdvBackForm extends BaseFormBack{
public ArrayList<Rdv> rdv;
    public ListRdvBackForm(Resources res) {
        super("ListRdv", BoxLayout.y());
  super.addSideMenu(res);
  rdv=ServiceRdv.getInstance().getAllRdv();
Label labe=new Label("LISTE DES Rendez-vous ");
addStringValue("",labe);
int i=1;
for (Rdv e :rdv)
{ 
Label l=new Label("Rendez-vous N°"+i++);
addStringValue("",l);
addButton(res.getImage("avatar.png"),"\n" +"Client Name="+e.getuser()+ "\n" +"Date="+e.getdate()+"\n", false, 26, 32);
      //  sp.setText(sp.getText()+"\n"+e.getDescription().toString());

      System.out.println("fffffff");
    Label Modif =new Label("modifier rdv");
Modif.setUIID("NewsTopLine");
Style modifStyle =new Style(Modif.getUnselectedStyle());
modifStyle.setFgColor(0xf7ad02);
FontImage mfontimage=FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT,modifStyle);
Modif.setIcon(mfontimage);
Modif.setTextPosition(LEFT);


addStringValue("", BoxLayout.encloseY(Modif));
Modif.addPointerPressedListener(x->new ModifRdvForm(res,e).show());
    
Label Modife =new Label("Supprimer rdv");
Modife.setUIID("NewsTopLine");
Style modifeStyle =new Style(Modife.getUnselectedStyle());
modifeStyle.setFgColor(0xc24400);
FontImage mfontimagee=FontImage.createMaterial(FontImage.MATERIAL_DELETE,modifeStyle);
Modife.setIcon(mfontimagee);
Modife.setTextPosition(LEFT);


addStringValue("", BoxLayout.encloseY(Modife));
Modife.addPointerPressedListener(new ActionListener() {
       public void actionPerformed(ActionEvent evt) {
                
                    ServiceRdv.getInstance().Supprimer(e.getId());

                };

            
       } );
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
