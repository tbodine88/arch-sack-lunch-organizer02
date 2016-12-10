/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.listeners;

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
       String FacilitatorUser = ctx.getInitParameter("FacilitatorUser");
       String FacilitatorPass = ctx.getInitParameter("FacilitatorPass");
       String FacilitatorCommittee = ctx.getInitParameter("FacilitatorCommittee");
       String memberUser = ctx.getInitParameter("memberUser");
       String memberPass = ctx.getInitParameter("memberPass");
       String Committee = ctx.getInitParameter("Committee");
       Person someone = new Person();
       someone.setLogin(adminUser);
       someone.setPassword(adminPass);
       someone.setCommittees(Committee);
       someone.setPermission(SackLunchPermission.ADMINISTRATOR);
       People.introduce(someone);
       someone = new Person();
       someone.setLogin(FacilitatorUser);
       someone.setPassword(FacilitatorPass);
       someone.setPermission(SackLunchPermission.FACILITATOR);
       People.introduce(someone);
       someone = new Person();
       someone.setLogin(memberUser);
       someone.setPassword(memberPass); 
       someone.setPermission(SackLunchPermission.MEMBER);
       People.introduce(someone);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
}
