/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.controller;
import com.tommythegeek.sackLunch.dao.DataConn;
import com.tommythegeek.sackLunch.dao.People;
import com.tommythegeek.sackLunch.dao.Witness;
import com.tommythegeek.sackLunch.dao.Person;
import com.tommythegeek.sackLunch.dao.Status;
import com.tommythegeek.sackLunch.dao.WitnessType;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Thomas Bodine
 */
public class updateMember extends HttpServlet {
    private final ArrayList<String> error;
    public final HashMap<String,Object> parmap;
    public updateMember(){
        super();
        error = new ArrayList<>();
        parmap = new HashMap();
    }
 /**
  * boolean goodParm( String parm, String errmsg,
  *         HttpServletRequest request, HashMap parmap)
  * @param parm - parameter from MemberMenu
  * @param errmsg - Bad Parm Error Message to add to the error list 
  * @param request - http servlet request
  * @return true for good parameters
  */
    protected boolean goodParm( String parm, String errmsg,
            HttpServletRequest request)
    {
       String value = request.getParameter(parm);
        if ( value == null || value.isEmpty()){
            error.add(errmsg);
            parmap.put(parm,"nothing");
            return false;
        } else {
            parmap.put(parm,value);
        }
        return true;
    }
    /**
     * show an error page when unexpected nulls encountered
     * @param tag - the name of the value that is null
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void nullExit(String tag, HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.setAttribute("flash", tag + " was null");
        request.getRequestDispatcher("WEB-INF/error/badParameter.jsp").forward(request,response);
    }
/**
 * vouch - check validity of parameter
 * @param parmname - name of parameter to test
 * @param map  - a hash map of parameters
 * @param type  - the kind of check to do
 * @param extra - stuff to add at end of the error
 */
    public void vouch( String parmname, WitnessType type, String extra ){
        Witness jerry = new Witness();
        String itshere = new String(parmname);
        Object thing = parmap.get(itshere);
        Status stat = new Status();
        if ( thing == null ){
            stat.ok = false;
            stat.message = "Internal Error (vouch): no value for parmname";   
        }else if ( thing.getClass() == String.class ){
	    switch (type) {
	        case WTUSER :
                    stat = jerry.validUserName((String) thing);
	    	 break;
                case WTEMAIL :
                    stat = jerry.validEmail((String)thing);
                    break;
                case WTPASSWORD :
                    stat = jerry.validPassword((String)thing);
                    break;
                case WTPHONE :
                    stat = jerry.validPhoneNumber((String)thing);
                    break;
                default:
                    stat.ok = false;
                    stat.message = "vouch: Internal error(invalid type: " + type + ")";
                    break;     
	    } // end switch
        } else {
            stat.ok = false;
            stat.message = parmname + " is not a String ";
        }// end if thing
        if( stat.fail()){
           error.add( stat.message + extra);
         } // end if
    }// end vouch

    /**
     * Processes requests 
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Person guy = new Person();
        String accum ;
        int rowId = 0;
        Witness victor = new Witness();
        Status result;
        error.clear();
        parmap.clear();
        ServletContext sce = request.getServletContext();
        if ( sce==null){  nullExit("ServletContext", request,response); return;}
        DataConn dc = (DataConn) sce.getAttribute("dbconnection");
        if (dc==null){ nullExit("dataConn", request,response); return;}
        goodParm("ed_name","no name given",request);
        goodParm("ed_phone","no phone given",request);
        vouch("ed_phone",WitnessType.WTPHONE,"");
        goodParm("ed_email","no mail given",request);
        vouch("ed_email",WitnessType.WTEMAIL,"");
        goodParm("ed_can_deliver","no delivery status",request);
        goodParm("ed_committees","no committees",request);
        goodParm("ed_permission","no permission (member assumed)",request);
        goodParm("ed_login","no login",request);
        vouch("ed_login", WitnessType.WTUSER, "");
        goodParm("ed_password","no password",request);
        goodParm("ed_hint","no hint",request);
        accum = (String) parmap.get("ed_hint");
        String login = (String) parmap.get("ed_login");
        String password = (String) parmap.get("ed_password");
        result = victor.validHint(accum,login,password);
        if (result.fail() ){
            error.add(result.message);
        }
        goodParm("activity","Internal Error: no activity specified ",request);
        String activity = (String) parmap.get("activity");
        accum = (String) parmap.get("ed_name");
        guy.setName(accum);
        accum = (String) parmap.get("ed_phone");
        guy.setPhone(accum);
        accum = (String) parmap.get("ed_email");
        guy.setEmail(accum);
        accum = (String) parmap.get("ed_can_deliver");
        guy.setCan_deliver(accum.equals("on"));
        accum = (String) parmap.get("ed_committees");
        guy.setCommittees(accum);
        accum = (String) parmap.get("ed_permission");
        guy.setPermission(accum);
        accum = (String) parmap.get("ed_login");
        guy.setLogin(accum);
        accum = (String) parmap.get("ed_password");
        guy.setPassword(accum);
        accum = (String) parmap.get("ed_hint");
        guy.setHint(accum);
        accum = request.getParameter("ed_rowid");
        Date now = new Date();
        guy.setUpdated(now);
        guy.setFailure(now);
        guy.setSuccess(now);
        guy.setFail_count(0);
        
        if ( accum == null || accum.isEmpty()){
            rowId = People.getNextRowId(dc);
        } else {
            try {
                int nextId =People.getNextRowId(dc);
                rowId = Integer.parseInt((String)accum);
                if (rowId < 0 || rowId >  (nextId - 1) ){
                    rowId = nextId;
                }
            } catch(NumberFormatException e){
                error.add("Tnternal error updateMember: rowId not an integer");
            }
        }
        
        request.setAttribute("menu", "memberMan");
        if ( error.isEmpty()) {
            switch (activity) {
                case "add":
                    People.introduce(guy,dc);
                    break;
                case "edit":
                    guy.setRowid(rowId);
                    People.updateById(rowId, guy,dc);
                    break;
                case "delete":
                    People.deleteById(rowId);
                    break;
                default:
                    break;
            }
            request.getRequestDispatcher("WEB-INF/canvas/success.jsp").forward(request, response);
        } else {
            request.setAttribute("err-ed_phone",parmap.get("ed_phone"));
            request.setAttribute("flash", "There are problems with the data eneterd.");
            request.setAttribute("error", error);
            request.getRequestDispatcher("WEB-INF/error/badParameter.jsp").forward(request, response);
        }
    }

    // <editor-fold  desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
                try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta http-equiv=\"refresh\" content=\"3;url=index.jsp\" />");
            out.println("<title>Servlet doGet updateMember</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet doGet updateMember at " + request.getContextPath() + "</h1>");
            out.println("<p><font color=red>This is an error</font></p>");
            out.println("</body>");
            out.println("</html>");
        }
        // processRequest(request, response);
    }

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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(updateMember.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "update Member data base as directed from ManageMembers Form";
    }// </editor-fold>

}
