/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.controller;

import com.tommythegeek.sackLunch.dao.Inventory;
import com.tommythegeek.sackLunch.dao.SackLunchPermission;
import static com.tommythegeek.sackLunch.dao.SackLunchPermission.MEMBER;
import java.io.IOException;
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
public class ItemMan extends HttpServlet {

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
        HttpSession session = request.getSession(false);
        if( session==null){
             request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        @SuppressWarnings("null")
        Object operm = session.getAttribute("perm");
        SackLunchPermission perm = (SackLunchPermission) ((operm == null) ? MEMBER : operm) ;
        String group = (String) session.getAttribute("group");
        if (perm.compareTo(SackLunchPermission.FACILITATOR) == -1 ){
            session.invalidate();
            request.setAttribute("flash", "You may not access that page");
            request.getRequestDispatcher("notloggedin.jsp").forward(request, response);
        }
        ServletContext sce = request.getServletContext();
        Inventory ivan = (Inventory) sce.getAttribute("inventory");
        ArrayList<String> itemid = new ArrayList<>();
        ArrayList<String> item = new ArrayList<>();
        ArrayList<String> itemGroup = new ArrayList<>();
        Inventory.dumpThings( itemid,itemGroup,item);
        if ( !(request.getAttribute("error") == null)){
            request.removeAttribute("error");
        }
        request.setAttribute("itemid", itemid);
        request.setAttribute("item", item);
        request.setAttribute("itemGroup", itemGroup);
        
        request.getRequestDispatcher("WEB-INF/canvas/itemManage.jsp").forward(request, response);
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
