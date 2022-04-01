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
import com.mycompany.myapp.entities.SimpleUtilisateur;
import com.mycompany.myapp.entities.coach;
import com.mycompany.myapp.services.ServiceUser;

/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class AddCoachForm extends BaseForm {

    Form current;

    public AddCoachForm(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);

        tb.addSearchCommand(e -> {
        });

   Label us=new Label("REMPLIR LE FORMULAIRE POUR AJOUTER UN COACH ");
us.getAllStyles().setFgColor(0xc24400);
addStringValue("", us);
   Label ligne=new Label("");
addStringValue("", ligne);
        TextField username = new TextField("", "Username", 20, TextField.ANY);
        username.setUIID("TextFieldBlack");
        addStringValue("Username", username);

        TextField email = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
        email.setUIID("TextFieldBlack");
        addStringValue("E-Mail", email);

        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        password.setUIID("TextFieldBlack");
        addStringValue("Password", password);
 TextField verifpassword = new TextField("", "VerifPassword", 20, TextField.PASSWORD);
         verifpassword.setUIID("TextFieldBlack");
        addStringValue("Confirmer password", verifpassword);
        TextField fullname = new TextField("", "Specialite", 20, TextField.ANY);
        fullname.setUIID("TextFieldBlack");
        addStringValue("Specialite", fullname);
        Button modif = new Button("Créer");
        
        addStringValue("", BoxLayout.encloseY(modif));
       
  modif.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent evt) {
                if ((username.getText().length() == 0) || (email.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        coach h = new coach(username.getText(), password.getText().toString(), password.getText().toString(), email.getText().toString() ,fullname.getText().toString());

                        if (ServiceUser.getInstance().addCoach(h)) {
                            Dialog.show("Success", "Connection accepted", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                }

            }
        });
modif.addActionListener(tt->new ListCoachBackForm(res).show());
}

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));

    }
}
