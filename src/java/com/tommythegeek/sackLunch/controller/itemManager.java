/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.controller;

import com.sun.javafx.util.Utils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Owner
 */
public class itemManager extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @SuppressWarnings("empty-statement")
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
	String[] itemid;
	String[] itemGroupName;
	String[] item;
        String[] itemGroup;
	String elected;
        
	itemid = Utils.split("0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20"," ");
	itemGroup = Utils.split("1, 2,4 3, 4, 1,5, 1, 2, 3, 4, 5, 1,2,3, 2, 3, 4, 5, " +
			"1, 2, 3, 4, 5, 1,2,3,4,5", " ");
	item = Utils.split("Blackcurrant Blueberry Chili pepper Cranberry Eggplant " +
			"Gooseberry Grape Guava Kiwifruit Lucuma Pomegranate Redcurrant " +
			"Tomato Cucumber Gourd Melon Pumpkin Grapefruit Lemon Lime Orange"," ");
	itemGroupName = Utils.split("none first second third fourth fifth"," ");

	elected = "14";

	request.setAttribute("itemid",itemid);
	request.setAttribute("itemGroupName",itemGroupName);
	request.setAttribute("itemGroup",itemGroup);
	request.setAttribute("item",item);
	request.setAttribute("elected",elected);
	
        request.getRequestDispatcher("WEB-INF/canvas/itemMenu.jsp").forward(request, response);
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
