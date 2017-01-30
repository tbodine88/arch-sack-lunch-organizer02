/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.controller;
import com.tommythegeek.sackLunch.utility.is;
import com.tommythegeek.sackLunch.dao.People;
import com.tommythegeek.sackLunch.dao.SackLunchPermission;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Owner
 */
public class memberMessage extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String flash ="";
        HttpSession session = request.getSession(false);
        if(is.nullSession(session,"Members must log in",request, response)) return;
        ServletContext ctx = request.getServletContext();
        if(is.naull(ctx, "servlet Context", request, response)) return;
        String messageParam = ctx.getInitParameter("messageFile");
        if(is.naull(messageParam, "Init Parameter 'messageFile'", request, response)) return;
        
        String userId= (String) session.getAttribute("userId");
	if ( is.naull(userId,"userId",request,response)) return;
        String username= (String) session.getAttribute("username");
	if ( is.naull(username,"username",request,response)) return;
        String fullName = (String) session.getAttribute("fullName");
	if ( is.naull(fullName ,"fullName ",request,response)) return;
        String group = (String) session.getAttribute("group");
	if ( is.naull(group ,"group ",request,response)) return;
	SackLunchPermission perm =  (SackLunchPermission) session.getAttribute("perm");
	if ( is.naull(perm ,"perm ",request,response)) return;

        ArrayList<String> error = new ArrayList<>();
        File messName = new File(messageParam );
        String recipient = request.getParameter("mailto");
        if( is.naull(recipient, "recipient", request, response)) return;
        String weekOfMonth = (String)session.getAttribute("week");
        if(is.naull(weekOfMonth,"week", request, response)) return;
        String message = request.getParameter("message");
        if ( message == null || message.isEmpty()){
            error.add("No nessage entered");
            request.setAttribute("flash", 
                    "Need a message to send");
            request.setAttribute("error", error);
            request.getRequestDispatcher("WEB-INF/error/badParameter.jsp")
                    .forward(request, response);
            return;
        }
        try{
          int bee = Integer.parseInt(weekOfMonth);
            if ( recipient.equals("Facilitator")){
                recipient = People.facilitatorEmail(bee);
            } else {
                recipient = People.committeeEmail(bee);
            }
            flash = "Message sent to " + recipient;
            request.setAttribute("menu","menuSel?sel=MemberMenu");
            int id4user = Integer.parseInt(userId); 
            PrintWriter pw = new PrintWriter(new FileWriter( messName ));
            pw.println("from: "+ People.personById(id4user).getEmail());
            pw.println("to: " + recipient);
            pw.println();
            pw.flush();
            flash = flash + "<br/>Message saved";
        } catch( IOException | NumberFormatException e){
                error.add("Message not Sent: " + e.getMessage());
        }

        if(  error.isEmpty()) {
            request.setAttribute("flash",flash);
            request.getRequestDispatcher("WEB-INF/canvas/success.jsp").
                    forward(request, response);
        } else {
            request.setAttribute("flash", 
                    "There are problems with the data enterd.");
            request.setAttribute("error", error);
            request.getRequestDispatcher("WEB-INF/error/internalError.jsp")
                    .forward(request, response);
        } //end if    }
    } // end processRequest
    
    // <editor-fold  desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
