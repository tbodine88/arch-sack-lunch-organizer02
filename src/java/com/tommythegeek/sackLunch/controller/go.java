/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.controller;

import com.tommythegeek.sackLunch.dao.Check;
import com.tommythegeek.sackLunch.dao.Item;
import com.tommythegeek.sackLunch.dao.ItemVolunteered;
import com.tommythegeek.sackLunch.dao.Meeting;
import com.tommythegeek.sackLunch.dao.MeetingList;
import com.tommythegeek.sackLunch.dao.People;
import com.tommythegeek.sackLunch.dao.Person;
import com.tommythegeek.sackLunch.dao.Status;
import com.tommythegeek.sackLunch.dao.SackLunchPermission;
import com.tommythegeek.sackLunch.dao.Schedule;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
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
public class go extends HttpServlet {

 
    
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
        request.getRequestDispatcher("login.jsp").forward(request, response);
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
        SackLunchPermission perm;
        Integer userId;
        String username;
        String fullName;
        String password;
        String group;
        Pattern compat = Pattern.compile(",");

        Person applicant = new Person();
        username =request.getParameter("username");
        if ( username == null || username.isEmpty()){
            request.setAttribute("flash", "enter a Login");            
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        applicant.setLogin(username);
        password = request.getParameter("password");
        if ( password == null || password.isEmpty()){     
            request.setAttribute("flash", "enter a Login and password");            
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        applicant.setPassword(password);
        Status stat = People.validates(applicant);
        if (stat.ok){
            HttpSession session = request.getSession();
            ServletContext ctx = request.getServletContext();
            Schedule sked = (Schedule) ctx.getAttribute("sked");
            MeetingList ml = new MeetingList(sked);
            session.setAttribute("meetings", ml);
            userId = applicant.getRowid();
	    username = applicant.getLogin();
	    fullName = applicant.getName();
	    group = applicant.getCommittees();
            Matcher comat = compat.matcher(group);
            int from =0;
            int count = 0;
            while(comat.find(from)){
                count ++;
                from = comat.start()+1;
            }
	    perm  = applicant.getPermission();
            session.setAttribute("userId", userId.toString());
            session.setAttribute("username", username);
            session.setAttribute("fullName",fullName);
            session.setAttribute("group",group);
            session.setAttribute("perm", perm);
            switch(perm) {
                case FACILITATOR:
                    if ( count < 2 ) {
                      //request.setAttribute("week", group);
                      request.getRequestDispatcher("WEB-INF/canvas/FacilitatorMenu.jsp").forward(request, response);    
                    } else {
                      request.getRequestDispatcher("chooseGroup").forward(request, response);    
                    }
                    break;
                case ADMINISTRATOR:
                    request.getRequestDispatcher("/WEB-INF/canvas/administratorMenu.jsp").forward(request, response);    
                    break;
                default:
                    if ( count < 2){
                        group = group.substring(0,1);
                        request.setAttribute("week", group);
                        int dsel = Integer.parseInt(group);
                        MeetingList bunch= (MeetingList) session.getAttribute("meetings");
                        if ( is.naull(bunch, "meetings", request, response)) return ;
                        Calendar cal  = new GregorianCalendar();
                        Meeting bee =bunch.meeting.get(dsel -1);
                        cal.setTime(bee.getDate());
                        String sdate = String.format("%02d/%02d/%04d", 
                                cal.get(Calendar.MONTH )+ 1,
                                cal.get(Calendar.DAY_OF_MONTH),
                                cal.get(Calendar.YEAR));
                        request.setAttribute("date", sdate);
                        Check checkList = (Check) ctx.getAttribute("checklist");
                        if ( is.naull(checkList,"checkList", request, response)) return ;
                        ArrayList<Item> things =  checkList.forCommittee(dsel);
                        ArrayList<String> tname = new ArrayList<>();
                        ArrayList<String> donator = new ArrayList<>();
                        for( int i = 0 ; i < things.size() ; i++){
                            String butang = things.get(i).getName(); 
                            tname.add(butang );
                            donator.add( ItemVolunteered.donor(bee.getDate(),butang).getName());
                        }
                        Person facilitator = People.facilitator(dsel);
                        request.setAttribute("facilitator",facilitator.getName());
                        request.setAttribute("thing",tname);
                        request.setAttribute("donator", donator);
                        request.getRequestDispatcher("/WEB-INF/canvas/MemberMenu.jsp").forward(request, response);    
                    } else {
                        request.getRequestDispatcher("chooseGroup?menu=MemberMenu").forward(request, response);    
                    }
                    break;
            }
        } else {
            request.setAttribute("flash", stat.message);            
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
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
