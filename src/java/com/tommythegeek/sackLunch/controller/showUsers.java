/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.controller;

import com.tommythegeek.sackLunch.dao.People;
import com.tommythegeek.sackLunch.dao.Person;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Thomas Bodine
 */
@WebServlet(name = "showUsers", urlPatterns = {"/showUsers"})
public class showUsers extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        ArrayList<String> rowid = new ArrayList<>();
        ArrayList<String> name = new ArrayList<>();
        ArrayList<String> phone = new ArrayList<>();
        ArrayList<String> email = new ArrayList<>();
        ArrayList<String> can_deliver = new ArrayList<>();
        ArrayList<String> updated = new ArrayList<>();
        ArrayList<String> committees = new ArrayList<>();
        ArrayList<String> login = new ArrayList<>();
        ArrayList<String> password = new ArrayList<>();
        ArrayList<String> hint = new ArrayList<>();
        ArrayList<String> Success= new ArrayList<>();
        ArrayList<String> failure= new ArrayList<>();
        ArrayList<String> fail_count = new ArrayList<>();
        ArrayList<String> permission = new ArrayList<>();
        ServletContext sce = request.getServletContext();
        People pop = (People) sce.getAttribute("people");
        Person guy;
        Boolean box;
        Date dox;
        pop.updatePop();
        for ( int i = 0 ; i < pop.population ; i++ ){
            guy = People.personById(i);
            rowid.add("" + i);
            name.add(guy.getName());
	    phone.add(guy.getPhone());
	    email.add(guy.getEmail());
            box = guy.isCan_deliver();
            can_deliver.add(box.toString());
            dox = guy.getUpdated();
            updated.add( dox.toString());
            String group = guy.getCommittees();
            committees.add(group);
            login.add(guy.getLogin());
            password.add(guy.getPassword());
            hint.add(guy.getHint());
            dox = guy.getSuccess();
            Success.add(dox.toString());
            dox = guy.getFailure();
            failure.add(dox.toString());
            int fc = guy.getFail_count();
            fail_count.add("" + fc );
            permission.add(guy.getPermission().toString());
        }
        request.setAttribute("rowid",rowid);
        request.setAttribute("name",name);
        request.setAttribute("phone",phone);
        request.setAttribute("email",email);
        request.setAttribute("can_deliver",can_deliver);
        request.setAttribute("updated",updated);
        request.setAttribute("committees",committees);
        request.setAttribute("login",login);
        request.setAttribute("password",password);
        request.setAttribute("hint",hint);
        request.setAttribute("Success",Success);
        request.setAttribute("failure",failure);
        request.setAttribute("xavier",fail_count);
        request.setAttribute("permission",permission);

        request.getRequestDispatcher("/WEB-INF/canvas/showUsers.jsp").forward(request, response);
   
    }
    // <editor-fold desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
