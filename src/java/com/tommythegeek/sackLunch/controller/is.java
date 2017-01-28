/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Thomas Bodine
 */
public class is {
    /**
     *   show internal error page when null is encountered
     * @param thing check for null
     * @param badName  name of null object
     * @param request  HttpservletRequest
     * @param response HttpservletResponse
     * @return nothing
     * @throws ServletException
     * @throws IOException 
     */
    public static boolean naull( Object  thing, String badName , 
            HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException{
        if( thing != null)
            return false;
        request.setAttribute("flash", badName + " was null");
        request.getRequestDispatcher("WEB-INF/error/internalError.jsp").forward(request,response);
        return true;    
    } 

    static boolean nullSession(HttpSession session, String flash,
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        if( session !=null)
            return false;
        request.setAttribute("flash", flash);
        request.getRequestDispatcher("WEB-INF/error/pleaseLogin.jsp").forward(request,response);
        return true;
    }
}
