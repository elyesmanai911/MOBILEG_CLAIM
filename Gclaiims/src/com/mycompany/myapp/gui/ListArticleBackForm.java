/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
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
import com.mycompany.myapp.entities.Article;
import com.mycompany.myapp.services.ServiceArticle;
import java.util.ArrayList;
import java.io.IOException;
import com.codename1.ui.Tabs;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Graphics;
import com.codename1.ui.RadioButton;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.messaging.Message;

/**
 *
 * @author User
 */
public class ListArticleBackForm extends BaseFormBack {

    public ListArticleBackForm (Form previous,Resources res) throws IOException {
          super("Article ! ", BoxLayout.y());
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
          ArrayList<Article> list;
        list = new ArrayList<>();
        list = ServiceArticle.getInstance().getAllArticle();

        for ( Article a : list) {
            
              
                  Label i = new Label();

         SpanLabel spl = new SpanLabel("Titre de l article: " + "  " + a.getTitre()); 
          SpanLabel spl7 = new SpanLabel("Description: " + "  " + a.getDescription());
          //SpanLabel spl6 = new SpanLabel("Image: " + "  " + a.getImage());
         SpanLabel spl5 = new SpanLabel("article Views: " + "  " + a.getNbr_vu());
       



//         SpanLabel spl6 = new SpanLabel("artist's ID: " + "  " + o.getIdArtiste());
         Button sup = new Button("Delete");
                  Button upd = new Button("Update");

             //c1.add(BorderLayout.CENTER,i);
             //c1.add(BorderLayout.CENTER,spl);
             
             //c2.addAll(spl2,spl3,spl4,spl5);
                sup.addActionListener((evt) -> {
                   ServiceArticle.getInstance().SupprimerArticle(a.getId());
                    System.out.println("article deleted successfully");
                    Dialog.show("Alert", "Delete article ?", new Command("OK"));
                    Dialog.show("Success", "article deleted successfully", new Command("OK"));
                     Message m = new Message("Your article has been deleted successfully !");
                        Display.getInstance().sendMessage(new String[] {"zeineb.bouayed@esprit.tn"}, "Confirmation", m);
                    });
                 upd.addActionListener((evt) -> {
                        new EditArticleForm(this.getComponentForm(),res,a).show();
                    });
        addAll(spl,spl5, spl7,sup,upd)
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

