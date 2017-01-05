/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.listeners;

import com.tommythegeek.sackLunch.dao.Inventory;
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
       
       Inventory ivan = new Inventory();
       // favs Format:  name1;g,r,o,u,p1;name2:g,r,o,u,p2;
       String favs = ctx.getInitParameter("thing");
       if ( favs != null && !favs.isEmpty()){
        String [] favThing = favs.split("[;]");
        for( String fob : favThing ){
            String [] part = fob.split(":");     
            Inventory.addThing(part[0], part[1]);
        }
       } 
        ivan.reCount();
        ctx.setAttribute("inventory", ivan);
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
}
