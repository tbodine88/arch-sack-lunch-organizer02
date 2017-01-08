/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.controller;

import com.sun.javafx.util.Utils;
import com.tommythegeek.sackLunch.dao.Check;
import com.tommythegeek.sackLunch.dao.Council;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tommythegeek.sackLunch.dao.Item;
import javax.servlet.ServletContext;
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

        ServletContext ctx = request.getServletContext();
        Check theCheck = (Check) ctx.getAttribute("checklist");
        Council theCouncil = (Council) ctx.getAttribute("council");
        ArrayList<String> itemid = new ArrayList<>();
	ArrayList<String> itemGroupName= new ArrayList<>();
	ArrayList<String> item= new ArrayList<>();
        ArrayList<String>itemGroup= new ArrayList<>();
	String elected;
        
        String committee;
        String name;
        for( int i = theCheck.firstIndex(); i < theCheck.lastIndex() ; i++){
            Item thing = theCheck.findByIndex(i);
            if( thing == null ){
                continue;
            }
            itemid.add("" + thing.getIndex());
            name = thing.getName();
            committee = thing.getCommittee();
            itemGroup.add(committee);
            item.add(name);
        }
        String[] in_itemGroupName = Utils.split("none first second third fourth fifth"," ");
        for( int i = 0 ; i < theCouncil.subcommittee.size(); i++){
            itemGroupName.add(theCouncil.subcommittee.get(i).getName());
        }
        elected = request.getParameter("elect");

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
