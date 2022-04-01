/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Profil;
import com.mycompany.myapp.services.ServiceProfil;
import com.mycompany.myapp.entities.Rdv;
import com.mycompany.myapp.services.ServiceRdv;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Map;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.ActionEvent;


public class ProfilForm extends BaseForm{
Form current;
public ArrayList<Profil> Profils;
    public ProfilForm(Form previous,Resources res) {
        super("ListProfil", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Liste Profil");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("cg-5.jpg"), spacer1, "15 Likes  ", "85 Comments", "Rejoindre une équipe pour participer aux tournois ");
        addTab(swipe, res.getImage("cg-10.jpg"), spacer2, "100 Likes  ", "66 Comments", "les Equipes de GCLAIM");
                
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
        
        ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle("All", barGroup);
        all.setUIID("SelectBar");
        RadioButton Equipe = RadioButton.createToggle("Equipe", barGroup);
        Equipe.setUIID("SelectBar");
        RadioButton Tournoi = RadioButton.createToggle("Tournoi", barGroup);
        Tournoi.setUIID("SelectBar");
        RadioButton Coach = RadioButton.createToggle("Coach", barGroup);
        Coach.setUIID("SelectBar");
        RadioButton Article = RadioButton.createToggle("Article", barGroup);
        Article.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
       
        Equipe.setUIID("SelectBar");
        add(LayeredLayout.encloseIn(
               GridLayout.encloseIn(4, all, Equipe,Tournoi, Coach,Article),
                FlowLayout.encloseBottom(arrow)
        ));
        
        all.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(all, arrow);
        });
        bindButtonSelection(all, arrow);
        bindButtonSelection(Equipe, arrow);
        bindButtonSelection(Tournoi, arrow);
        bindButtonSelection(Coach, arrow);
          bindButtonSelection(Article, arrow);
        
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
     
        Equipe.addActionListener( (e) -> {
            new ListEquipeForm(current,res).show();

        });
        Tournoi.addActionListener( (e) -> {
            new ListTournoiForm(current,res).show();

        });
Coach.addActionListener( (e) -> {
            new ProfilForm(current,res).show();

        });
       SpanLabel sp = new SpanLabel();
Profils=ServiceProfil.getInstance().getAllcoachs();
for (Profil e :Profils)
{ addButton(res.getImage("news-item-1.jpg"),e.getUsername().toString()+ "\n" +e.getDescription().toString()+ "\n" +Integer.toString(e.getnumero())+ "\n", false, 26, 32);
      //  sp.setText(sp.getText()+"\n"+e.getDescription().toString());
 
System.out.println(e.getUsername());
System.out.println(e.getgame());
  System.out.println(SessionManager.getId());
 System.out.println(e.getnumero());

   
 // add(sp);
if(SessionManager.getUserName().equals(e.getUsername()) )
{
 Label Modif =new Label("MODIFIER VOTRE Profile ");
Modif.setUIID("NewsTopLine");
Style modifStyle =new Style(Modif.getUnselectedStyle());
modifStyle.setFgColor(0xf7ad02);
FontImage mfontimage=FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT,modifStyle);
Modif.setIcon(mfontimage);
Modif.setTextPosition(LEFT);


addStringValue("", BoxLayout.encloseY(Modif));
Modif.addPointerPressedListener(ll->new ModifProfilForm(res,e).show());
}
else 
{
     System.out.println("fffffff");
    Label Modif =new Label("Reservez un rendez-vous");
Modif.setUIID("NewsTopLine");
Style modifStyle =new Style(Modif.getUnselectedStyle());
modifStyle.setFgColor(0xf7ad02);
FontImage mfontimage=FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT,modifStyle);
Modif.setIcon(mfontimage);
Modif.setTextPosition(LEFT);


addStringValue("", BoxLayout.encloseY(Modif));
Modif.addPointerPressedListener(ll->new addRdvForm(res,e).show());
}
}
 
    }
    
    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
        
        
    }
    
    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                image,
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                            new SpanLabel(text, "LargeWhiteText"),
                            FlowLayout.encloseIn(likes, comments),
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
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
    
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }

       private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));

    }
}
