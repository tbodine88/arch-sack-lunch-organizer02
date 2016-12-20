/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.controller;

import com.tommythegeek.sackLunch.dao.SackLunchPermission;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        response.setContentType("text/html;charset=UTF-8");
        Pattern comma = Pattern.compile(",");
        Matcher comat = comma.matcher((CharSequence) request.getAttribute("committee"));
        SackLunchPermission perm = (SackLunchPermission) request.getAttribute("perm");
        String[] meetDate = new String[5];
        // Figure out todays date
        Calendar nextMeet = Calendar.getInstance();
        // figure out Next Mondays date    
        // Figure out the next meeting date for each group

        int thisWeekDay = nextMeet.get(Calendar.DAY_OF_WEEK);
        int mondayIsDaysAway;
        switch (thisWeekDay){
            case(Calendar.SUNDAY): mondayIsDaysAway =1; break;
            case(Calendar.MONDAY): mondayIsDaysAway =7; break;
            case(Calendar.TUESDAY): mondayIsDaysAway =6; break;
            case(Calendar.WEDNESDAY): mondayIsDaysAway =5; break;
            case(Calendar.THURSDAY): mondayIsDaysAway =4; break;
            case(Calendar.FRIDAY): mondayIsDaysAway =3; break;
            default: mondayIsDaysAway =2; break;
        }
        nextMeet.add(Calendar.DATE, mondayIsDaysAway);
        int dayOfMonth = nextMeet.get(Calendar.DAY_OF_MONTH);
        int slot =0;
        while (dayOfMonth > 0) {
            dayOfMonth -= 7;
            if (dayOfMonth > 0) slot++;
        }
        String[] dates = new String[5];
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        for (int ax=0; ax < 5 ; ax++){
            dates[slot] = format1.format(nextMeet.getTime());
            slot = ( slot + 1 ) % 5;
            nextMeet.add(Calendar.DATE,7);
        }
        
        request.setAttribute("meetDate", dates);
        int comcount = 0;
        while( comat.find())
               comcount++;
        if (comcount < 2){
            switch(perm) {
                case MEMBER:
                    request.getRequestDispatcher("/WEB-INF/canvas/MemberMenu.jsp").forward(request, response);    
                default:
                    request.getRequestDispatcher("/WEB-INF/canvas/FacilitatorMenu.jsp").forward(request, response); 
            }
        }
        request.getRequestDispatcher("/WEB-INF/canvas/groupMenu.jsp").forward(request, response);
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
