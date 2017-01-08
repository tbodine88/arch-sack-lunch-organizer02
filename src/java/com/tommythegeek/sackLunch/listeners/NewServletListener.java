/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.listeners;

import com.sun.javafx.util.Utils;
import com.tommythegeek.sackLunch.dao.Check;
import com.tommythegeek.sackLunch.dao.Committee;
import com.tommythegeek.sackLunch.dao.Council;
import com.tommythegeek.sackLunch.dao.Item;
import com.tommythegeek.sackLunch.dao.People;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.tommythegeek.sackLunch.dao.Person;
import com.tommythegeek.sackLunch.dao.SackLunchPermission;
import java.util.HashSet;
import java.util.Set;

/**
 * Web application lifecycle listener.
 *
 * @author Thomas Bodine
 */
public class NewServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
       ServletContext ctx = sce.getServletContext();
       String adminUser = ctx.getInitParameter("adminUser");
       String adminPass = ctx.getInitParameter("adminPass");
       String adminName = ctx.getInitParameter("adminName");
       String FacilitatorUser = ctx.getInitParameter("FacilitatorUser");
       String FacilitatorPass = ctx.getInitParameter("FacilitatorPass");
       String FacilitatorName = ctx.getInitParameter("FacilitatorName");
       String FacilitatorCommittee = ctx.getInitParameter("FacilitatorCommittee");
       String memberUser = ctx.getInitParameter("memberUser");
       String memberPass = ctx.getInitParameter("memberPass");
       String memberName = ctx.getInitParameter("memberName");
       String Committee = ctx.getInitParameter("Committee");
       People pop = new People();
       pop.updatePop();
       ctx.setAttribute("people", pop);
       Person someone = new Person();
       someone.setRowid(1);
       someone.setLogin(adminUser);
       someone.setPassword(adminPass);
       someone.setName(adminName);
       someone.setCommittees(Committee);
       someone.setPermission(SackLunchPermission.ADMINISTRATOR);
       People.introduce(someone);
       pop.updatePop();
       someone = new Person();
       someone.setRowid(2);
       someone.setLogin(FacilitatorUser);
       someone.setPassword(FacilitatorPass);
       someone.setName(FacilitatorName);
       someone.setPermission(SackLunchPermission.FACILITATOR);
       People.introduce(someone);
       pop.updatePop();
       someone = new Person();
       someone.setRowid(3);
       someone.setLogin(memberUser);
       someone.setPassword(memberPass); 
       someone.setName(memberName);
       someone.setPermission(SackLunchPermission.MEMBER);
       People.introduce(someone);
       pop.updatePop();
       
        //initialize the list of things to bring -- the check list
        // Add items to Check.list
       	String[] in_itemid = Utils.split("0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20"," ");
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
            theCheck.list.add(thing);
        }
        ctx.setAttribute("checklist",theCheck);
        
        //initialize the Council with the names of the subcommittees
        Council council = new Council();
        Committee committee; 
        String[] longName = Utils.split("first monday; second monday; third monday; fourth monday; fifth monday; ","; ");
        String[] shortName = Utils.split("first second third fourth fifth"," ");
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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
}
