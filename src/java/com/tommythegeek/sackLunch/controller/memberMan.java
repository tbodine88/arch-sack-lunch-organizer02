/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.controller;

import com.tommythegeek.sackLunch.dao.People;
import com.tommythegeek.sackLunch.dao.Person;
import com.tommythegeek.sackLunch.dao.SackLunchPermission;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Owner
 */
@WebServlet(name = "memberMan", urlPatterns = {"/memberMan"})
public class memberMan extends HttpServlet {

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
                ServletContext sce = request.getServletContext();
        Person guy;
        String guyName;
        
        SackLunchPermission perm;
        People pop = (People) sce.getAttribute("people");
        ArrayList<String> rowid = new ArrayList<>();
        ArrayList<String> name = new ArrayList<>();
        HttpSession session = request.getSession(false);
        if( session==null){
             request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        perm = (SackLunchPermission) session.getAttribute("perm");
        String group = (String) session.getAttribute("group");
        if (perm.compareTo(SackLunchPermission.FACILITATOR) == -1 ){
            session.invalidate();
            request.setAttribute("flash", "You may not access that page");
            request.getRequestDispatcher("notloggedin.jsp").forward(request, response);
        }
        pop.updatePop();
        for ( int i = 0 ; i < pop.population ; i++ ){
            guy = People.personById(i);
            if ( perm == SackLunchPermission.FACILITATOR ){
              if (guy.getPermission().compareTo(SackLunchPermission.FACILITATOR) > 0)
                  continue;
              if (! guy.isMemberOf(group))
                    continue;
            }
            rowid.add("" + i);
            name.add(guy.getName());
        }
        request.setAttribute("rowid", rowid);
        request.setAttribute("name",name);
        String[] values = (String []) request.getParameterValues("selected");
        if (! (values == null || values.length == 0) ){
            guy = People.personById(Integer.parseInt(values[0]));
            request.setAttribute("ed_rowid",values[0]);
            request.setAttribute("ed_name", guy.getName());
            request.setAttribute("ed_phone",guy.getPhone());
            request.setAttribute("ed_email",guy.getEmail());
            request.setAttribute("ed_can_deliver",guy.isCan_deliver() ? "yes" : "no");
            request.setAttribute("ed_committees",guy.getCommittees());
            if( perm == SackLunchPermission.ADMINISTRATOR) {
                request.setAttribute("ed_permission",guy.getPermission());
                request.setAttribute("ed_login",guy.getLogin());
                request.setAttribute("ed_password",guy.getPassword());
                request.setAttribute("ed_hint",guy.getHint());
                request.setAttribute("ed_success",guy.getSuccess());
                request.setAttribute("ed_failure",guy.getFailure());
                request.setAttribute("ed_fail_count",guy.getFail_count());
                request.setAttribute("ed_updated",guy.getUpdated());
            }
        }
        request.getRequestDispatcher("WEB-INF/canvas/ManageMembers.jsp").forward(request, response);
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
