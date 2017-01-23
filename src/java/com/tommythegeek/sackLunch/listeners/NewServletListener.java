/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.listeners;

import com.sun.javafx.util.Utils;
import com.tommythegeek.sackLunch.dao.BodineNeedsToImplement;
import com.tommythegeek.sackLunch.dao.Check;
import com.tommythegeek.sackLunch.dao.Committee;
import com.tommythegeek.sackLunch.dao.Council;
import com.tommythegeek.sackLunch.dao.DataConn;
import com.tommythegeek.sackLunch.dao.Item;
import com.tommythegeek.sackLunch.dao.People;
import com.tommythegeek.sackLunch.dao.MeetingList;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.tommythegeek.sackLunch.dao.Schedule;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Web application lifecycle listener.
 *
 * @author Thomas Bodine
 */
public class NewServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
       DataConn dc;
       ServletContext ctx = sce.getServletContext();
       String conn = ctx.getInitParameter("dbconnection");
       String user = ctx.getInitParameter("dbuser");
       String password = ctx.getInitParameter("dbpassword");
       conn = String.format("%s;user=%s;password=%s", conn,user,password);
       try {
        dc = new DataConn(conn);
         ctx.setAttribute("dbconnection", dc);
       } catch (SQLException sqle){
           throw new RuntimeException(sqle);
       }
       People pop = new People();
       if ( ! pop.loadFromDB(dc)){
           try {
               throw new BodineNeedsToImplement( "Data base load failed");
           } catch (BodineNeedsToImplement ex) {
               Logger.getLogger(NewServletListener.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
       pop.updatePop();
       ctx.setAttribute("people", pop);
       Schedule sked = new Schedule();
       ctx.setAttribute("sked",sked);
       sked.nextFirst();
       sked.nextSecond();
       sked.nextThird();
       sked.nextFourth();
       sked.nextFifth();
        //initialize the list of things to bring -- the check list
        // Add items to Check.list
       	String[] in_itemid = Utils.split("0 1 2 3 4 5 "," "); //6 7 8 9 10 11 12 13 14 15 16 17 18 19 20"," ");
	String[] in_itemGroup = Utils.split("1, 2,4 3, 4, 1,5, 1, 2, 3, 4, 5, 1,2,3, 2, 3, 4, 5, " +
			"1, 2, 3, 4, 5, 1,2,3,4,5", " ");
	String[] in_item = Utils.split("Blackcurrant Blueberry Chili pepper Cranberry Eggplant " +
			"Gooseberry Grape Guava Kiwifruit Lucuma Pomegranate Redcurrant " +
			"Tomato Cucumber Gourd Melon Pumpkin Grapefruit Lemon Lime Orange"," ");
        Check theCheck;
        Item thing;
        
        theCheck = new Check();
        for( int i = 0; i < in_itemid.length ; i++){
            thing = new Item();
            thing.setIndex( Integer.parseInt(in_itemid[i]));
            thing.setCommittee(in_itemGroup[i]);
            thing.setName(in_item[i]);
            theCheck.add(thing);
        }
        ctx.setAttribute("checklist",theCheck);
        
        //initialize the Council with the names of the subcommittees
        Council council = new Council();
        Committee committee; 
        String[] longName = Utils.split("no day; first monday; second monday; third monday; fourth monday; fifth monday; ","; ");
        String[] shortName = Utils.split("none first second third fourth fifth"," ");
        for( int i = council.subcommittee.size() ; i < shortName.length; i++){
            committee = new Committee();
            committee.setIndex(i);
            committee.setFullname(longName[i]);
            committee.setName(shortName[i]);
            council.subcommittee.add(committee);
        }
        ctx.setAttribute("council",council);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();
        People pop = (People) ctx.getAttribute("people");
        Schedule sked =(Schedule) ctx.getAttribute("sked");
        Check theCheck =(Check) ctx.getAttribute("checklist");
        Committee council  =(Committee) ctx.getAttribute("council");
    }
  
}
