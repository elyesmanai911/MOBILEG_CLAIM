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

import com.codename1.components.FloatingHint;
import com.codename1.components.InfiniteProgress;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Profil;
import com.mycompany.myapp.services.ServiceEquipe;
import com.mycompany.myapp.services.ServiceProfil;
import static java.lang.Integer.parseInt;
import java.util.Date;

/**
 * Sign in UI
 *
 * @author Shai Almog
 */
public class addProfilForm extends BaseForm {
Form current;
    public addProfilForm(Resources res) {
        super(new BorderLayout());

        if (!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout) getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
        getTitleArea().setUIID("Container");
        setUIID("SignIn");

        add(BorderLayout.NORTH, new Label(res.getImage("Logo.png"), "LogoLabel"));

        TextField username = new TextField(SessionManager.getUserName());
        TextField game = new TextField("", "game", 20, TextField.ANY);
        TextField description = new TextField("", "description", 20, TextField.ANY);
          TextField numero = new TextField("", "numero", 20, TextField.ANY);
        
        

        username.setSingleLineTextArea(false);
        game.setSingleLineTextArea(false);
        description.setSingleLineTextArea(false);
        numero.setSingleLineTextArea(false);
      
        Button signIn = new Button("Devenir coach");

        Container content = BoxLayout.encloseY(
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(game),
                createLineSeparator(),
                new FloatingHint(description),
                createLineSeparator(),
                 new FloatingHint(numero),
                createLineSeparator(),
                signIn
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
        signIn.requestFocus();
        signIn.addActionListener(e
                -> {
            try {
                if ((username.getText().equals("")) || (game.getText().equals("")) || (description.getText().equals(""))) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                   
                  Profil eq=new Profil(description.getText(),username.getText(),game.getText(),Integer.parseInt(numero.getText()),SessionManager.getId());
                

                    System.out.println("data profil =="+eq);
                         if (ServiceProfil.getInstance().addprofil(eq)) {
                               System.out.println("okkkkkkkkkkkkkkkkkk");
                            Dialog.show("Success", "Connection accepted", new Command("OK"));
                        } else {
                       System.out.println("ereuuuuuuuur");

                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }

 }
            } catch (Exception e1) {
                System.out.println(e1.getMessage());
            }
        }
        );
signIn.addActionListener(ee
                -> new ProfilForm(current,res).show());
    }

}
