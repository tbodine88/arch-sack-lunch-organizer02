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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * menus on errors
 * @author Thomas Bodine
 */
public class Handle {
    /**
     * member menu handler
     * @param group  the committees this member is in
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    public static void memberMenu(String group, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
                HttpSession session = request.getSession();
                ServletContext ctx = request.getServletContext();
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
    } // end memberMenu
}
