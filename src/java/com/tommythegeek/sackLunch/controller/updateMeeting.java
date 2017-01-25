package com.tommythegeek.sackLunch.controller;

/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */

import com.tommythegeek.sackLunch.dao.MeetingList;
import com.tommythegeek.sackLunch.dao.People;
import com.tommythegeek.sackLunch.dao.SackLunchPermission;
import static com.tommythegeek.sackLunch.dao.SackLunchPermission.ADMINISTRATOR;
import com.tommythegeek.sackLunch.dao.Schedule;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
 * @author Owner
 */
public class updateMeeting extends HttpServlet {
    private void nullExit(String tag, HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.setAttribute("flash", tag + " was null");
        request.getRequestDispatcher("WEB-INF/error/badParameter.jsp").forward(request,response);
    }
    private void notInteger(String value, ArrayList<String> error){
        String pattern = "(\\d+)";

      // Create a Pattern object
      Pattern r = Pattern.compile(pattern);

      // Now create matcher object.
      Matcher m = r.matcher(value);
      
      if ( m.matches()){
          return;
      }
      error.add( value + " is not an integer");
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
        ArrayList<String> error = new ArrayList<>();
        ServletContext sce = request.getServletContext();
        if ( sce==null){  nullExit("ServletContext", request,response); return;}
        HttpSession session = request.getSession(false);
        SackLunchPermission perm = (SackLunchPermission) session.getAttribute("perm");
        if (perm == null){  nullExit("SackLunchPermission",request,response); return;}
        String group = (String) session.getAttribute("group");
        if (group == null){  nullExit("group",request,response); return;}
        Schedule sked = (Schedule) sce.getAttribute("sked");
        if(sked == null){ nullExit("sked",request,response); return;}
        MeetingList mlist = (MeetingList) session.getAttribute("meetings");

        String ed_meetingID = request.getParameter("ed_meetingID");
        notInteger( ed_meetingID, error);
        String ed_month = request.getParameter("ed_month");
        notInteger(ed_month, error);
        String ed_day = request.getParameter("ed_day");
        notInteger(ed_day, error);
        String ed_year = request.getParameter("ed_year");
        notInteger(ed_year,error);
        String activity = request.getParameter("activity");
        String flash;  
        if (perm == ADMINISTRATOR )
            request.setAttribute("menu","menuSel?sel=administratorMenu");
        else
            request.setAttribute("menu","menuSel?sel=FacilitatorMenu");
        if ( ! error.isEmpty()) {
            request.setAttribute("flash", "There are problems with the data enterd.");
            request.setAttribute("error", error);
            request.getRequestDispatcher("WEB-INF/error/badParameter.jsp").forward(request, response);
        }
        flash = "New date for meeting " +ed_meetingID + " " + ed_month + " "+ ed_day +" " + ed_year;
        switch (activity) {
            case "add":
                flash = "Adding meeting " +
                        ed_meetingID + " " + ed_month + " "+ ed_day +
                        " " + ed_year;
                    break;
            case "change date":
                flash = "Change meeting " +ed_meetingID + " " + ed_month +
                        " "+ ed_day +" " + ed_year;
                sked.update(ed_meetingID,ed_month,ed_day,ed_year, error);
                break;
            case "delete":
                flash = "Delete meeting " +ed_meetingID + " " + ed_month + " "+ ed_day +" " + ed_year;
                break;
            default:
                break;
        }// end switch
        if(  error.isEmpty()) {
            request.setAttribute("menu","meetManager");
            request.setAttribute("flash",flash);
            request.getRequestDispatcher("WEB-INF/canvas/success.jsp").forward(request, response);
        } else {
            request.setAttribute("flash", "There are problems with the data enterd.");
            request.setAttribute("error", error);
            request.getRequestDispatcher("WEB-INF/error/badParameter.jsp").forward(request, response);
        } //end if
    } //end processRequest
          


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
    }// end doGet

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
    } // end doPost

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "update Meeting Schedule from ItemManager form input";
    }// end getServletInfo

}
