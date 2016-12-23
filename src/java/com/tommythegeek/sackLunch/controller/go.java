/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.controller;

import com.tommythegeek.sackLunch.dao.People;
import com.tommythegeek.sackLunch.dao.Person;
import com.tommythegeek.sackLunch.dao.Status;
import com.tommythegeek.sackLunch.dao.SackLunchPermission;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Thomas Bodine
 */
public class go extends HttpServlet {

 
    
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
        request.getRequestDispatcher("login.jsp").forward(request, response);
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
        SackLunchPermission perm;
        Integer userId;
        String username;
        String fullName;
        String group;
        Pattern compat = Pattern.compile(",");

        Person applicant = new Person();
        username =request.getParameter("username");
        applicant.setLogin(username);
        applicant.setPassword(request.getParameter("password"));
        Status stat = People.validates(applicant);
        if (stat.ok){
            HttpSession session = request.getSession();
            userId = applicant.getRowid();
	    username = applicant.getLogin();
	    fullName = applicant.getName();
	    group = applicant.getCommittees();
            Matcher comat = compat.matcher(group);
            int from =0;
            int count = 0;
            while(comat.find(from)){
                count ++;
                from = comat.start()+1;
            }
	    perm  = applicant.getPermission();
            session.setAttribute("userId", userId.toString());
            session.setAttribute("username", username);
            session.setAttribute("fullName",fullName);
            session.setAttribute("group",group);
            session.setAttribute("perm", perm);
            switch(perm) {
                case FACILITATOR:
                    if ( count < 2 ) {
                      request.getRequestDispatcher("WEB-INF/canvas/FacilitatorMenu.jsp").forward(request, response);    
                    } else {
                      request.getRequestDispatcher("chooseGroup").forward(request, response);    
                    }
                    break;
                case ADMINISTRATOR:
                    request.getRequestDispatcher("/WEB-INF/canvas/siteAdministrator.jsp").forward(request, response);    
                    break;
                default:
                    if ( count < 2){
                        request.getRequestDispatcher("/WEB-INF/canvas/MemberMenu.jsp").forward(request, response);    
                    } else {
                        request.getRequestDispatcher("chooseGroup?menu=MemberMenu").forward(request, response);    
                    }
                    break;
            }
        } else {
            request.setAttribute("flash", stat.message);            
            request.getRequestDispatcher("notloggedin.jsp").forward(request, response);
        }
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
