package com.tommythegeek.sackLunch.utility;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;

public class email{
   public static void send(ServletContext ctx,
           String sender,
           String recipient, 
           String subject,
           String message) throws IOException, NullPointerException{
       String messName = ctx.getInitParameter("messageFile");
       try (PrintWriter pw = new PrintWriter(new FileWriter( messName,true ))) {
           pw.println("from: " + sender);
           pw.println("to: " + recipient);
           pw.println("subj: " + subject);
           pw.println();
           pw.println(message);
           pw.println();
           pw.flush();
       }
   }
} // end email