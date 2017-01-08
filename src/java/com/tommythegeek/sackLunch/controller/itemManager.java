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
import com.tommythegeek.sackLunch.dao.SackLunchPermission;
import static com.tommythegeek.sackLunch.dao.SackLunchPermission.ADMINISTRATOR;
import com.tommythegeek.sackLunch.dao.groupMemberChecker;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
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
        HttpSession session = request.getSession(false);
        if( session==null){
             request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        Council theCouncil = (Council) ctx.getAttribute("council");
        SackLunchPermission perm = (SackLunchPermission) session.getAttribute("perm");
        String usersGroup = (String) session.getAttribute("group");
        groupMemberChecker bouncer = new groupMemberChecker(usersGroup);
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
            name = thing.getName();
            committee = thing.getCommittee();
            if (perm.compareTo( ADMINISTRATOR ) < 0){
                if ( bouncer.reject(committee))
                    continue;
            }
            itemid.add("" + thing.getIndex());
            itemGroup.add(committee);
            item.add(name);
        }
        String[] in_itemGroupName = Utils.split("none first second third fourth fifth"," ");
        for( int i = 0 ; i < theCouncil.subcommittee.size(); i++){
            itemGroupName.add(theCouncil.subcommittee.get(i).getName());
        }
        
        // adjust for the actual size of the displayed array 
        // rowId 4 may be in row 2
        String selected = request.getParameter("elect");
        elected="-1";
        for(int i = 0; i < itemid.size(); i++){
            if ( itemid.get(i).equals(selected)){
                elected= new Integer(i).toString();
            }
        }

	request.setAttribute("itemid",itemid);
	request.setAttribute("itemGroupName",itemGroupName);
	request.setAttribute("itemGroup",itemGroup);
	request.setAttribute("item",item);
	request.setAttribute("elected",elected);
        request.setAttribute("menu","/mainMenuSelector");
	
        request.getRequestDispatcher("WEB-INF/canvas/itemMenu.jsp").forward(request, response);
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
