/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Jeu;
import com.mycompany.myapp.services.ServiceJeu;


/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class ModifJeuForm extends BaseFormBack {

    Form current;

    public ModifJeuForm(Resources res,Jeu to) {
        
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("MODIFIER Jeu");
        getContentPane().setScrollVisible(false);
        super.addSideMenu(res);

        tb.addSearchCommand(e -> {
        });

        Image img = res.getImage("news-item-1.jpg");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

   
        TextField nomjeu = new TextField(to.getNomjeu());
        nomjeu.setUIID("TextFieldBlack");
        addStringValue("nomjeu", nomjeu);

        TextField description = new TextField(to.getDescription());
        description.setUIID("TextFieldBlack");
        addStringValue("Description", description);
        TextField createur = new TextField(to.getCreateur());
        createur.setUIID("TextFieldBlack");
        addStringValue("createur", createur);
        Button modif = new Button("modifier votre Jeu");
        
        addStringValue("", BoxLayout.encloseY(modif));
        
  modif.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent evt) {
                if ((nomjeu.getText().length() == 0) || (description.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Jeu h = new Jeu(to.getId(),nomjeu.getText(), description.getText(),createur.getCloudBoundProperty());
                        if (ServiceJeu.getInstance().updatejeu(h)) {
                            Dialog.show("Success update", "Connection accepted", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                }

            }
        });
modif.addActionListener(l->new ListJeuBackForm(res).show());
}

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));

    }
}
