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
import com.codename1.ui.Calendar;
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
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Tournoi;
import com.mycompany.myapp.services.ServiceTournoi;
import java.util.Date;

/**
 * Sign in UI
 *
 * @author Shai Almog
 */
public class AddTournoiForm extends BaseFormBack {
Form current;
    public AddTournoiForm(Resources res) {
        super(new BorderLayout());

        if (!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout) getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
        getTitleArea().setUIID("Container");
        setUIID("SignIn");

        add(BorderLayout.NORTH, new Label(res.getImage("Logo.png"), "LogoLabel"));
        TextField nomtournoi = new TextField("", "Nom Tournoi", 20, TextField.ANY);
        TextField description = new TextField("", "Description", 20, TextField.ANY);
   
       // TextField idjeu = new TextField("", "idjeu", 20, TextField.ANY);
        TextField image = new TextField("", "Image", 20, TextField.ANY);
       
        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
    
        
        nomtournoi.setSingleLineTextArea(false);
        description.setSingleLineTextArea(false);
      //  dateev.setSingleLineTextArea(false);
       //  idjeu.setSingleLineTextArea(false);
        image.setSingleLineTextArea(false);
        
       
        Button signIn = new Button("Cr??er votre Tournoi");

        Container content = BoxLayout.encloseY(
                new FloatingHint(nomtournoi),
                createLineSeparator(),
                new FloatingHint(description),
                createLineSeparator(),          
                //new FloatingHint(idjeu),
                createLineSeparator(),
                new FloatingHint(image),
                createLineSeparator(),
                datePicker,
                createLineSeparator(),
                signIn
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
        signIn.requestFocus();
        signIn.addActionListener(e
                -> {
            try {
                if ((nomtournoi.getText().equals("")) || (description.getText().equals(""))) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                  System.out.println( datePicker.getDate());
                  Tournoi to= new Tournoi(nomtournoi.getText(),description.getText(),image.getText(),datePicker.getDate());
                    System.out.println("data tournoi =="+to);
                         if (ServiceTournoi.getInstance().addtournoi(to)) {
                            Dialog.show("Success", "Connection accepted", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
 }
            } catch (Exception e1) {
                System.out.println(e1.getMessage());
            }
        }
        );
signIn.addActionListener(ee
                -> new ListTournoiBackForm(res).show());
    }

  

}
