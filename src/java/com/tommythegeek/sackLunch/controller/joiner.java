/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.controller;

import com.tommythegeek.sackLunch.dao.People;
import com.tommythegeek.sackLunch.dao.Person;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Thomas Bodine
 */
@WebServlet(name = "joiner", urlPatterns = {"/joiner"}, initParams = {
    @WebInitParam(name = "aParam", value = "joined")})
public class joiner extends HttpServlet {

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
        request.getRequestDispatcher("joinForm.jsp").forward(request, response);
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
	    
	final String badParmPage="WEB-INF/error/badParameter.jsp";
        String login = request.getParameter("login");
	if ( login == null || login.equals("")) {
		request.setAttribute("item","login"); 
		request.getRequestDispatcher(badParmPage).forward(request,response);	
	}
        String password = request.getParameter("password1");
	if ( password == null || password.equals("")) {
		request.setAttribute("item","first password"); 
		request.getRequestDispatcher(badParmPage).forward(request,response);	
	}
        String password2 = request.getParameter("password2");
	if ( password2 == null || password2.equals("")) {
		request.setAttribute("item","second password"); 
		request.getRequestDispatcher(badParmPage).forward(request,response);	
	}
        String hint = request.getParameter("hint");
	if ( hint == null || hint.equals("")) {
		request.setAttribute("item","hint"); 
		request.getRequestDispatcher(badParmPage).forward(request,response);	
	}
        String fullName = request.getParameter("name");
	if ( fullName == null || fullName.equals("")) {
		request.setAttribute("item","fullName"); 
		request.getRequestDispatcher(badParmPage).forward(request,response);	
	}
        String phone = request.getParameter("phone");
	if ( phone == null || phone.equals("")) {
		request.setAttribute("item","phone"); 
		request.getRequestDispatcher(badParmPage).forward(request,response);	
	}
        String email = request.getParameter("email");
	if ( email == null || email.equals("")) {
		request.setAttribute("item","email"); 
		request.getRequestDispatcher(badParmPage).forward(request,response);	
	}
        String car = request.getParameter("car");
	if ( car == null || car.equals("") ){
		request.setAttribute("item","car"); 
		request.getRequestDispatcher(badParmPage).forward(request,response);	
	}
        String license = request.getParameter("license");
	if ( license == null || license.equals("")) {
		request.setAttribute("item","license"); 
		request.getRequestDispatcher(badParmPage).forward(request,response);	
	}

        request.setAttribute("backPage", "joinForm.jsp");
        if ( People.isInCrowd(login)){
           request.setAttribute("item", login);
           request.getRequestDispatcher("WEB-INF/error/inUse.jsp").forward(request, response);    
        }
        if ( ! password.equals(password2)){
            request.setAttribute("flash", "The Passwords don't match!");
            request.getRequestDispatcher("WEB-INF/error/invalidPassword.jsp").forward(request, response);    
        }
        Person newGuy = new Person();
        newGuy.setLogin(login);
        newGuy.setPassword(password);
        newGuy.setHint(hint);
        newGuy.setName(fullName);
        newGuy.setPhone(phone);
        newGuy.setEmail(email);
        request.getRequestDispatcher("loggedin.jsp").forward(request, response);    
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
