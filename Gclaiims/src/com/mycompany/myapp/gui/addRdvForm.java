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
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Equipe;
import com.mycompany.myapp.entities.Profil;
import com.mycompany.myapp.entities.Rdv;
import com.mycompany.myapp.services.ServiceEquipe;
import com.mycompany.myapp.services.ServiceRdv;
import java.util.ArrayList;
import java.util.Date;

/**
 * Sign in UI
 *
 * @author Shai Almog
 */
public class addRdvForm extends BaseForm {
Form current;

    public addRdvForm(Resources res,Profil pro) {
        super(new BorderLayout());

        if (!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout) getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
        getTitleArea().setUIID("Container");
        setUIID("SignIn");

        add(BorderLayout.NORTH, new Label(res.getImage("Logo.png"), "LogoLabel"));

      Picker datePicker = new Picker();
      datePicker.setType(Display.PICKER_TYPE_DATE);

      
        Button signIn = new Button("ajouter votre rendez-vous");

        Container content = BoxLayout.encloseY(
                datePicker,
                createLineSeparator(),
              
                signIn
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
        signIn.requestFocus();
   signIn.addActionListener(ee
                -> {
       SimpleDateFormat formatD = new SimpleDateFormat("yyyy-MM-dd");
 Rdv eq=new Rdv(pro.getUsername(),SessionManager.getUserName(),formatD.format(datePicker.getDate()));

  System.out.println("data rdv =="+eq);
                         if (ServiceRdv.getInstance().addRdv(eq)) {
                              
                            Dialog.show("Success", "Connection accepted", new Command("OK"));
                        } else {
                     

                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }

    
 }
            

            
        );
signIn.addActionListener(l->new ProfilForm(current,res).show());
}

    
}

       /* signIn.addActionListener(e
                -> {
                            SimpleDateFormat formatD = new SimpleDateFormat("yyyy-MM-dd");

                   
                  Rdv eq=new Rdv(SessionManager.getUserName(),formatD.format(datePickerD.getDate()));
                  
                    System.out.println("data equipe =="+eq);
                         if (ServiceRdv.getInstance().addRdv(eq)) {
                            Dialog.show("Success", "Connection accepted", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }

 }
            
        
        );
signIn.addActionListener(ee-> new ProfilForm(current,res).show());

    
    }
 private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));

    }
    

}
*/