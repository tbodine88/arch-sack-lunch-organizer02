/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.controller;

import com.tommythegeek.sackLunch.dao.Committee;
import com.tommythegeek.sackLunch.dao.ItemVolunteered;
import com.tommythegeek.sackLunch.dao.Meeting;
import com.tommythegeek.sackLunch.dao.People;
import com.tommythegeek.sackLunch.dao.Schedule;
import com.tommythegeek.sackLunch.utility.is;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
public class sendReminder extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if ( is.naull(session, "Session", request, response)) return;
        ServletContext ctx = session.getServletContext();
        if ( is.naull(ctx, "ServletContext",request,response)) return;
        Schedule sked= (Schedule) ctx.getAttribute("sked");
        if( is.naull(sked, "Skedule", request, response)) return;
        String messageParam = ctx.getInitParameter("messageFile");
        if(is.naull(messageParam, "Init Parameter 'messageFile'", request, response)) return;
        Calendar today = new GregorianCalendar();
        int nmindex = sked.indexOfNextMonday(today);
        Meeting nextMeet = sked.getMeetingByIndex(nmindex);
        Committee committee =  nextMeet.getCommittee();
        String tofield = People.committeeEmail(committee.getIndex());
        Calendar meetDay = new GregorianCalendar();
        meetDay.setTime(sked.getMeetingByIndex(nmindex).getDate());
        String donators = ItemVolunteered.donators( meetDay);
        String secretWord = request.getParameter("daiski");
        if( secretWord == null) secretWord = "nope";
        
        donators.replaceAll("\\n", "<br/>");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if ( secretWord.equalsIgnoreCase("wuba") ) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet sendReminder</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Sending this</h1>");
                out.println(tofield);
                out.println("<br/>"+ donators);
                out.println("</body>");
                out.println("</html>");
        } else {
            out.println("<html><body>OK</body></html>");
            File messName = new File(messageParam );
                try (PrintWriter pw = new PrintWriter(new FileWriter( messName, true))) {
                    pw.println("from: sacklunch@tommythegeek.com");
                    pw.println("to: " + tofield);
                    pw.println(donators);
                    pw.println();
                    pw.flush();
                }
        }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
