/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.controller;

import com.tommythegeek.sackLunch.dao.MeetingList;
import com.tommythegeek.sackLunch.dao.SackLunchPermission;
import com.tommythegeek.sackLunch.dao.Schedule;
import java.io.IOException;
import java.util.regex.Pattern;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Thomas Bodine
 */
public class meetManager extends HttpServlet {

    private void nullExit(String tag, HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.setAttribute("flash", tag + " was null");
        request.getRequestDispatcher("WEB-INF/error/badParameter.jsp").forward(request,response);
    }
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
        if ( sce==null){  nullExit("ServletContext", request,response); return;}
        HttpSession session = request.getSession(false);
        SackLunchPermission perm = (SackLunchPermission) session.getAttribute("perm");
        if (perm == null){  nullExit("SackLunchPermission",request,response); return;}
        String group = (String) session.getAttribute("group");
        if (group == null){  nullExit("group",request,response); return;}
        MeetingList mlist = (MeetingList) session.getAttribute("meetings");
        if ( mlist == null){ nullExit("meetingList",request,response); return;}
        Schedule sked = (Schedule) sce.getAttribute("sked");
        if ( sked ==null ){ nullExit("sked",request,response); return;}
        request.setAttribute("meetings", mlist.getDates());
        request.setAttribute("indices",mlist.getIndices());
        String elected = request.getParameter("selected");
        if( !(elected == null || elected.isEmpty()) ){
            int selected = Integer.parseInt(elected);
            String ed_date = sked.getMeetingByIndex(selected).getDateString();
            if (ed_date != null && ! ed_date.isEmpty() ){
                String[] part = ed_date.split(Pattern.quote("/"));
                String ed_year;
                String ed_month;
                String ed_day;
                if (part.length == 3) {
                    ed_year = part[2];
                    ed_month = part[0];
                    ed_day = part[1];
                } else {
                    ed_year = "-1";
                    ed_month = "-1";
                    ed_day = "-1";
                    
                }
                request.setAttribute("ed_meetingID",elected);
                request.setAttribute("ed_year",ed_year);
                request.setAttribute("ed_month", ed_month);
                request.setAttribute("ed_day", ed_day);
            }// end if ed_date
        } // end if ! elected
        if( perm == SackLunchPermission.ADMINISTRATOR ){
            request.setAttribute("menu","menuSel?sel=administratorMenu");
        }else{
            request.setAttribute("menu","menuSel?sel=FacilitatorMenu");
        }// end if perm
        request.getRequestDispatcher("WEB-INF/canvas/ManageMeetings.jsp").forward(request,response);
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
