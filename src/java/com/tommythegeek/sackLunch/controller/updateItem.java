/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.controller;

import com.tommythegeek.sackLunch.utility.is;
import com.sun.javafx.util.Utils;
import com.tommythegeek.sackLunch.dao.Check;
import com.tommythegeek.sackLunch.dao.Council;
import com.tommythegeek.sackLunch.dao.Item;
import com.tommythegeek.sackLunch.dao.People;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Thomas Bodine
 */
public class updateItem extends HttpServlet {

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
        if(is.naull(ctx, "servlet Context", request, response)) return;
        Check theCheck = (Check) ctx.getAttribute("checklist");
        if(is.naull(theCheck, "checklist", request, response)) return;
        Council theCouncil = (Council) ctx.getAttribute("council");
        if(is.naull(theCouncil,"council", request, response)) return;
        ArrayList<String> error = new ArrayList<>();
        Item jewel = new Item();
        String activity = request.getParameter("activity");
        if( activity == null || activity.isEmpty()){
            activity = "none";
            error.add("Internal Error(updateItem): no activity specified ");
        } // end if
        StringBuilder itemGroup = new StringBuilder("");
        for ( String i : Utils.split("1 2 3 4 5"," ")){
            String parm = request.getParameter("itemGroup"+i);
            if ( parm != null && ! parm.isEmpty()){
                itemGroup.append(parm).append(",");
            }
        }// end for
        if( itemGroup.toString().isEmpty()){
            error.add("Please assign this item to a Committee.");
        } // end if
        String itemName = request.getParameter("item");
        if ( itemName == null || itemName.isEmpty()){
            error.add("Please enter an Item name.");
        } // end if
        String itemIndex = request.getParameter("rowid");
        boolean replace = ! (itemIndex == null || itemIndex.isEmpty()); 
        if ( replace ){
            jewel.setIndex(Integer.parseInt(itemIndex));
        } // end if
        jewel.setName(itemName);
        jewel.setCommittee(itemGroup.toString());
        
        request.setAttribute("menu", "itemManager");
        boolean result = true;
        if ( error.isEmpty()) {
            switch (activity) {
                case "add":
                    result = theCheck.add(jewel);
                    break;
                case "update":
                    result = theCheck.update(jewel);
                    break;
                case "delete":
                    result = theCheck.delete(jewel);
                    break;
                default:
                    break;
            } // end switch
            if ( ! result ){
                error.add("The " + activity + " action failed");
            }
        } // end if error isEmpty
        
        if( error.isEmpty()) { // 2
           request.getRequestDispatcher("WEB-INF/canvas/success.jsp").forward(request, response);
        } else {
            request.setAttribute("flash", "There are problems with the data eneterd.");
            request.setAttribute("error", error);
            request.getRequestDispatcher("WEB-INF/error/badParameter.jsp").forward(request, response);
        }// end if error isEmpty 2
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
        return "Short description";
    }// </editor-fold>

}
