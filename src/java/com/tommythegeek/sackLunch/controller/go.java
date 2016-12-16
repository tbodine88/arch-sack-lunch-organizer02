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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Owner
 */
public class go extends HttpServlet {

 
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
        Person applicant = new Person();
        String username =request.getParameter("username");
        applicant.setLogin(username);
        applicant.setPassword(request.getParameter("password"));
        Status stat = People.validates(applicant);
        if (stat.ok){
            HttpSession session = request.getSession();
            Integer userId = applicant.getRowid();
            session.setAttribute("userId", userId.toString());
            session.setAttribute("username", username);
            switch(applicant.getPermission()) {
                case MEMBER:
                    request.getRequestDispatcher("/WEB-INF/canvas/MemberMenu.jsp").forward(request, response);    
                case FACILITATOR:
                    request.getRequestDispatcher("/WEB-INF/canvas/FacilitatorMenu.jsp").forward(request, response);    
                case ADMINISTRATOR:
                    request.getRequestDispatcher("/WEB-INF/canvas/siteAdministrator.jsp").forward(request, response);    
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
