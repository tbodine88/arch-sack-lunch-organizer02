/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.utility;

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
    public static boolean naull( String string, String name){
        try{
            string.getClass();
        } catch (NullPointerException e){
            String mess = e.getMessage() + ":";
            mess = mess + ((name == null ) ? "unknown" : name);
            throw( new NullPointerException(mess));
        }
        return true;
    }
    public static boolean badParamCheck( String string, String name,
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
            name = (name == null ) ? "unknown" : name;
            if ( string == null || string.isEmpty()){
                request.setAttribute("flash", name + " is null");
                request.getRequestDispatcher("WEB-INF/error/badParameter.jsp").forward(request,response);
                return true;
            }
        return false;
    }

    public static boolean nullSession(HttpSession session, String flash,
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        if( session !=null)
            return false;
        request.setAttribute("flash", flash);
        request.getRequestDispatcher("WEB-INF/error/pleaseLogin.jsp").forward(request,response);
        return true;
    }
}
