/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.controller;

import com.tommythegeek.sackLunch.dao.Check;
import com.tommythegeek.sackLunch.dao.MeetingList;
import com.tommythegeek.sackLunch.dao.SackLunchPermission;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
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
public class chooseGroup extends HttpServlet {

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
        int groupMemberShip = 0;
        HttpSession session = request.getSession();
        if( session==null){
             request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        ServletContext ctx = request.getServletContext();

        Pattern comma = Pattern.compile(",");
        Pattern num = Pattern.compile("^(\\d)");
        CharSequence group = (CharSequence) session.getAttribute("group");
        SackLunchPermission perm = (SackLunchPermission) session.getAttribute("perm");
        String committee = request.getParameter("committee");
        if ( ! (committee == null || committee.isEmpty())){
            request.setAttribute("week", committee);
            if ( perm == SackLunchPermission.FACILITATOR){
                request.getRequestDispatcher("/WEB-INF/canvas/FacilitatorMenu.jsp").forward(request, response);
            }
            request.getRequestDispatcher("/WEB-INF/canvas/MemberMenu.jsp").forward(request, response);
        }
        Matcher comat = comma.matcher(group);
        Matcher nummat = num.matcher(group);
        MeetingList bunch= (MeetingList) session.getAttribute("meetings");
        request.setAttribute("meetDate", bunch.meeting);
        while( comat.find()) groupMemberShip++;
        if ( groupMemberShip < 2){
            nummat.find();
            request.setAttribute("week", nummat.group(1));
            if ( perm == SackLunchPermission.FACILITATOR){
                request.getRequestDispatcher("/WEB-INF/canvas/FacilitatorMenu.jsp").forward(request, response);
            }
            request.getRequestDispatcher("/WEB-INF/canvas/MemberMenu.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/canvas/groupMenu.jsp").forward(request, response);
        }
    }

    // 
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
