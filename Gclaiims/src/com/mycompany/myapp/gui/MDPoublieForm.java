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
import com.mycompany.myapp.entities.Equipe;
import com.mycompany.myapp.services.ServiceEquipe;
import com.mycompany.myapp.services.ServiceUtilisateur;
import com.sun.mail.smtp.SMTPTransport;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Sign in UI
 *
 * @author Shai Almog
 */
public class MDPoublieForm extends BaseForm {
Form current;
TextField username;
    public MDPoublieForm(Resources res) {
        super(new BorderLayout());

        if (!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout) getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
        getTitleArea().setUIID("Container");
        setUIID("SignIn");

        add(BorderLayout.NORTH, new Label(res.getImage("Logo.png"), "LogoLabel"));

         username = new TextField("", "Votre Email", 20, TextField.ANY);
       
        username.setSingleLineTextArea(false);
      
        Button signIn = new Button("Récupérer votre mot de passe ");

        Container content = BoxLayout.encloseY(
                new FloatingHint(username),
                createLineSeparator(),
                signIn
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
        signIn.requestFocus();
        signIn.addActionListener(e
                -> {
            try {
                if ((username.getText().equals("")) ) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                   
             ServiceUtilisateur.getInstance().getPasswordByEmail(username.getText(),res);
 Dialog.show("Alert", "Verifier Votre mail ", new Command("OK"));
                        new SignInForm(res).show();

 }
            } catch (Exception e1) {
                System.out.println(e1.getMessage());
            }
        }
        );

    }
public void sendMail(Resources res)
{
try{

Properties props=new Properties();
props.put("mail.transport.protocol","smtp");
props.put("mail.smtps.host","smtp.gmail.com");
props.put("mail.smtps.auth","true");
Session session =Session.getInstance(props,null);
MimeMessage msg =new MimeMessage(session);
msg.setFrom(new InternetAddress("Reintialisation mot de passe"));
msg.setRecipients(Message.RecipientType.TO, username.getText().toString());
msg.setSubject("Your password reset request");
msg.setSentDate(new Date(System.currentTimeMillis()));
   String mp =ServiceUtilisateur.getInstance().getPasswordByEmail(username.getText(),res);
String txt="voici votre mot de passe:"+mp+"Cordialement Gclaim by BITS&BYTES";
msg.setText(txt);
SMTPTransport st=(SMTPTransport)session.getTransport("smtps");
st.connect("smtp.gmail.com",465,"gclaimpidev@gmail.com","Gclaim2022");
st.sendMessage(msg,msg.getAllRecipients());
    System.out.println("server response:"+st.getLastServerResponse());

}catch(Exception e)
{e.printStackTrace();
}
}

}
