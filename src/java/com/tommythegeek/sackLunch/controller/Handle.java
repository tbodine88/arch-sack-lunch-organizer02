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
import com.tommythegeek.sackLunch.dao.Schedule;
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
                if(is.naull(session,"session", request, response)) return;
                ServletContext ctx = request.getServletContext();
                if(is.naull(ctx, "Servlet context", request, response)) return;
                Schedule sked = (Schedule) ctx.getAttribute("sked");
                if(is.naull(sked, "schedule", request, response)) return;
                group = group.substring(0,1);
                session.setAttribute("week", group);
                int dsel = Integer.parseInt(group);
                MeetingList ml = new MeetingList(sked);
                Calendar cal  = new GregorianCalendar();
                Meeting bee =ml.meeting.get(dsel -1);
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
