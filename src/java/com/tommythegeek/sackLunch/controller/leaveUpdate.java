/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.controller;

import com.tommythegeek.sackLunch.dao.Council;
import com.tommythegeek.sackLunch.dao.People;
import com.tommythegeek.sackLunch.dao.Person;
import com.tommythegeek.sackLunch.dao.SackLunchPermission;
import com.tommythegeek.sackLunch.dao.Schedule;
import com.tommythegeek.sackLunch.utility.email;
import com.tommythegeek.sackLunch.utility.is;
import java.io.IOException;
import java.io.PrintWriter;
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
public class leaveUpdate extends HttpServlet {

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
        Schedule sked = (Schedule) ctx.getAttribute("sked");
        Council council = (Council) ctx.getAttribute("council");
        if ( is.naull(council,"council", request, response)) return;
        String userId= (String) session.getAttribute("userId");
	if ( is.naull(userId,"userId",request,response)) return;
        String action = request.getParameter("action");
        if ( is.badParamCheck(action, "action",request, response)) return;
        String username= (String) session.getAttribute("username");
	if ( is.naull(username,"username",request,response)) return;
        String fullName = (String) session.getAttribute("fullName");
	if ( is.naull(fullName ,"fullName ",request,response)) return;
        String group = (String) session.getAttribute("group");
	if ( is.naull(group ,"group ",request,response)) return;
	SackLunchPermission perm =  (SackLunchPermission) session.getAttribute("perm");
	if ( is.naull(perm ,"perm ",request,response)) return;
        String weekOfMonth = (String)session.getAttribute("week");
        if(is.naull(weekOfMonth,"week", request, response)) return;
        int week = Integer.parseInt(weekOfMonth);
        Person alumni = People.personById(userId);
        if ( action.equals("Leave")){
            alumni.setPermission(SackLunchPermission.ALUMNI);
            email.send(ctx, alumni.getEmail(), People.facilitatorEmail(week), 
                fullName + "has left", 
                fullName + "has decided to leave the sack lunch " + 
                        council.subcommittee.get(week).getFullname() +
                        "committee."
            );
            request.getRequestDispatcher("logout").forward(request, response);
            return;
        }
        request.getRequestDispatcher("WEB-INF/canvas/Gratitude.jsp").forward(request, response);
    }

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
        return "Members leave the sack lunch committee with this servlet";
    }// </editor-fold>

}
