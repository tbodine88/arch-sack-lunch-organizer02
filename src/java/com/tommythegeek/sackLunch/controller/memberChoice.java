/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.controller;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import com.tommythegeek.sackLunch.dao.Check;
import com.tommythegeek.sackLunch.dao.Item;
import com.tommythegeek.sackLunch.dao.ItemVolunteered;
import com.tommythegeek.sackLunch.dao.Meeting;
import com.tommythegeek.sackLunch.dao.MeetingList;
import com.tommythegeek.sackLunch.dao.People;
import com.tommythegeek.sackLunch.dao.Person;
import com.tommythegeek.sackLunch.dao.SackLunchPermission;
import com.tommythegeek.sackLunch.dao.Schedule;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Owner
 */
public class memberChoice extends HttpServlet {

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
            HttpSession session = request.getSession();
            if(is.naull(session,"session", request, response)) return;
            ServletContext ctx = request.getServletContext();
            if(is.naull(ctx, "Servlet context", request, response)) return;
            Check list = (Check) ctx.getAttribute("checklist");
            if(is.naull(list,"checklist", request, response)) return;
            String userId= (String)session.getAttribute("userId");
            if(is.naull(userId,"userId", request, response)) return;
            String username = (String) session.getAttribute("username");
            if(is.naull(username,"username", request, response)) return;
            String fullName = (String) session.getAttribute("fullName");
            if(is.naull(fullName,"fullName", request, response)) return;
            String group = (String) session.getAttribute("group");
            if(is.naull(group,"group", request, response)) return;
            String week = (String) session.getAttribute("week");
            if(is.naull(week,"week", request, response)) return;
            SackLunchPermission perm = (SackLunchPermission) session.getAttribute("perm");
            if(is.naull(perm,"perm", request, response)) return;
            Schedule sked = (Schedule) ctx.getAttribute("sked");
            if(is.naull(sked,"sked", request, response)) return;

            String donationId = request.getParameter("donation");
            String helper = request.getParameter("helper");
            if ( helper == null ){
                request.getRequestDispatcher("WEB-INF\\canvas\\WellMissYou.jsp").forward(request, response);
            } else {
                int dsel = Integer.parseInt(week);
                MeetingList ml = new MeetingList(sked);
                Meeting bee =ml.meeting.get(dsel -1);
                Date beeDay = bee.getDate();
                Person volunteer = People.personById(userId);
                Item butang;
                if (donationId != null){
                    butang = list.findByIndex(Integer.parseInt(donationId));
                } else {
                    butang = new Item(); // Make entry to show RSVP, but bringing nothing
                    butang.setCommittee(group);
                }
                ItemVolunteered.donate(butang, beeDay,volunteer );
                request.getRequestDispatcher("WEB-INF\\canvas\\SeeYouThere.jsp").forward(request, response);
            }
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
