/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Owner
 */
public class is {
    public static boolean naull( Object  thing, String badName , 
            HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException{
        if( thing != null)
            return false;
        request.setAttribute("flash", badName + " was null");
        request.getRequestDispatcher("WEB-INF/error/badParameter.jsp").forward(request,response);
        return true;    
    } 
}
