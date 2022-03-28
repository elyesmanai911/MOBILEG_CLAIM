/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;


import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Services.ServiceCategorie;
import com.mycompany.myapp.Entities.Categorie;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author azizk
 */
public class ListCategoriesForm extends BaseForm {
     public ListCategoriesForm(Form previous,Resources res) throws IOException {
          super("Artwork", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Expositions");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        //tb.addSearchCommand(e -> {});
                Tabs swipe = new Tabs();
                        Label spacer1 = new Label();
        Label spacer2 = new Label();

        addTab(swipe, spacer1);
          swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        
        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        
          swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
              setTitle("Add Artwork");
        setLayout(BoxLayout.yCenter());
          ArrayList<Categorie> list;
        list = new ArrayList<>();
        list = ServiceCategorie.getInstance().getAllOeuvres();

        for ( Categorie c : list) {
            
              
                  Label i = new Label();
                  
                  /* String imageFile = FileSystemStorage.getInstance().getAppHomePath() + ev.getImage();
                  try(OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
                  ImageIO.getImageIO().save(Image.createImage(500,500), os, ImageIO.FORMAT_JPEG, 1);
                  } catch(IOException err) {
                  Log.e(err);
                  }*/
                 // Image img1 = Image.createImage("/"+o.getImageoeuvre());
  // i.setIcon(img1);
        /*   Image img;
           EncodedImage enc;
           enc = EncodedImage.create("/faza.jpg");
           enc.scale(570,620);
           String url="http://127.0.0.1:8080/ArtDome_WEB/public/pi/"+p.getImageoeuvre();
           img =URLImage.createToStorage(enc, url, url);
           img.scaledHeight(1);
           img.fill(570,620);
           ImageViewer imgv;
           imgv= new ImageViewer(img);*/
         SpanLabel spl = new SpanLabel("categorie name: " + "  " + c.getNom_categorie()); 
         SpanLabel spl2 = new SpanLabel("categorie type: " + "  " + c.getType_categorie()); 
        // SpanLabel spl3 = new SpanLabel("oeuvre category: " + "  " + o.getNomcat());
         //SpanLabel spl4 = new SpanLabel("Date: " + "  " + ev.getDate());
          


//         SpanLabel spl6 = new SpanLabel("artist's ID: " + "  " + o.getIdArtiste());
         Button sup = new Button("Delete");
                  Button upd = new Button("Update");

             //c1.add(BorderLayout.CENTER,i);
             //c1.add(BorderLayout.CENTER,spl);
             
             //c2.addAll(spl2,spl3,spl4,spl5);
                sup.addActionListener((evt) -> {
                   ServiceCategorie.getInstance().Supprimer(c.getId_categorie());
                    System.out.println("categorie deleted successfully");
                    Dialog.show("Alert", "Delete categorie ?", new Command("OK"));
                    Dialog.show("Success", "categorie deleted successfully", new Command("OK"));
                     Message m = new Message("Your categorie has been deleted successfully !");
                        Display.getInstance().sendMessage(new String[] {"mohamedaziz.kchouk@esprit.tn"}, "Confirmation", m);
                    });
                 upd.addActionListener((evt) -> {
                        new EditCategorieForm(this.getComponentForm(),res,c.getId_categorie()).show();
                    });
        addAll(spl,spl2,sup,upd)
                ;}
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
    private void addTab(Tabs swipe,  Label spacer) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
               
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                           
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }
}
