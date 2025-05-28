package com.lib.helper;


import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.util.Properties;

public class EmailUtil {

    public static boolean sendEmail(String toEmail, String subject, String messageText) {
        final String fromEmail = "ankit.dhakariya@webkorps.com"; 
        final String password = "lzfgmmxjjcvvqbld";    

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");  
        props.put("mail.smtp.port", "587");             
        props.put("mail.smtp.auth", "true");            
        props.put("mail.smtp.starttls.enable", "true"); 

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(fromEmail, "Library System"));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            msg.setSubject(subject);
            msg.setText(messageText);
            Transport.send(msg);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
