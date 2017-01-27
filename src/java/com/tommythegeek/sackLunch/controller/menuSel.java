/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.controller;

import com.tommythegeek.sackLunch.dao.Check;
import com.tommythegeek.sackLunch.dao.People;
import com.tommythegeek.sackLunch.dao.Person;
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
public class menuSel extends HttpServlet {

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
        ServletContext ctx = request.getServletContext();
        if( ctx == null){
             request.getRequestDispatcher("index.jsp").forward(request, response);
             return;
        }
        HttpSession session = request.getSession(false);
        if( session==null){
             request.getRequestDispatcher("index.jsp").forward(request, response);
             return;
        }
        String selectedMenu = request.getParameter("sel");
        if ( selectedMenu == null || selectedMenu.isEmpty()){
            request.setAttribute("item", "selectedMenu");
            request.getRequestDispatcher("WEB-INF/error/badParameter.jsp").forward(request, response);
            return;
        }
        if( selectedMenu.equals("MemberMenu")){
            String userid = (String) session.getAttribute("userId");
            Person user = People.personById(Integer.parseInt(userid));
            String group = user.getCommittees();
            Handle.memberMenu(group, request, response);
            return;
        }
        String destination = "WEB-INF/canvas/" + selectedMenu + ".jsp";
        request.getRequestDispatcher(destination).forward(request, response);
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
