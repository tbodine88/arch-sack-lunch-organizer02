/*
 * Copyright 2016 Thomas Bodine
 * All rights Reserved
 */
package com.tommythegeek.sackLunch.controller;

import com.tommythegeek.sackLunch.dao.MeetingList;
import com.tommythegeek.sackLunch.dao.People;
import com.tommythegeek.sackLunch.dao.Person;
import com.tommythegeek.sackLunch.dao.SackLunchPermission;
import com.tommythegeek.sackLunch.dao.Status;
import com.tommythegeek.sackLunch.dao.Validator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Thomas Bodine
 */
@WebServlet(name = "joiner", urlPatterns = {"/joiner"}, initParams = {
    @WebInitParam(name = "aParam", value = "joined")})
public class joiner extends HttpServlet {

    // <editor-fold desc="HttpServlet methods. ">
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
        MeetingList committee;
        
        committee = new MeetingList();
        
        request.setAttribute("meet", committee.meeting);
        request.getRequestDispatcher("joinForm.jsp").forward(request, response);
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

      final String badParmPage="WEB-INF/error/badParameter.jsp";
      Validator victor = new Validator();
      Status result;
      String item = "";
      ArrayList<String> error = new ArrayList<>();
      String anError;
      String login = request.getParameter("login");
      if ( login == null || login.isEmpty()) {
        error.add("login is empty"); 
      }
      String password = request.getParameter("password1");
      if ( password == null || password.isEmpty()) {
        error.add("first password is empty");
      }
      String password2 = request.getParameter("password2");
      if ( password2 == null || password2.isEmpty()) {
        error.add("second password is empty"); 
      }
      String hint = request.getParameter("hint");
      if ( hint == null || hint.isEmpty()) {
        error.add("hint is empty."); 
      }
      String fullName = request.getParameter("name");
      if ( fullName == null || fullName.isEmpty()) {
        error.add("fullName is empty."); 
      }
      String phone = request.getParameter("phone");
      if ( phone == null || phone.isEmpty()) {
        error.add("phone is empty."); 
      }
      String email = request.getParameter("email");
      if ( email == null || email.isEmpty()) {
        error.add("email is empty."); 
      }
      String car = request.getParameter("car");
      if ( car == null || car.isEmpty() ){
        car = "off";
      }
      String license = request.getParameter("license");
      if ( license == null || license.isEmpty()) {
        license = "off";
      }
      String committee = "";
      for (int i=1; i<6; i++){
          String bunch = request.getParameter("committee" + i);
          if (!( bunch == null || bunch.isEmpty())) {
              committee += "" + i + ",";
          }
      }
      if ( committee == null || committee.isEmpty()) {
        item = "no committee selected";  
        error.add("Please select a committee to join.");
      }

      if ( error.size() > 0 ) {
        request.setAttribute("flash", "Please fill in form completely.");
        request.setAttribute("error", error);
        request.getRequestDispatcher("WEB-INF/error/invalid.jsp").forward(request, response);    
        return;
      }

      request.setAttribute("backPage", "joinForm.jsp");
      result = victor.validUserName(login);
      if ( ! result.ok){
        item += "Login,";
        error.add(result.message);
      }
      if ( People.isInCrowd(login)){
        item += " password in use,";
        error.add( "Login name '" + login + "' is already in use by someone else.");
      }
      result = victor.validPassword(password);
      if ( result.ok ){
        if ( ! password.equals(password2)){
            item += " passwords do not match,";
          error.add("The Passwords don't match!");
        }
      } else {
        item += " password,";
        error.add( result.message);
      }
      result = victor.validHint(hint, login, password);
      if ( !result.ok){
        item += " hint,";
        error.add(result.message);
      }
      result = victor.validPhoneNumber(phone);
      if ( !result.ok ){
        item += " phone number,";
        error.add(result.message);
      }
      result = victor.validEmail(email);
      if ( !result.ok ){
          item += " email";
          error.add(result.message);
      }
      if ( error.size() > 0 ) {
        request.setAttribute("item", item);
        request.setAttribute("flash", "Invalid input");
        request.setAttribute("error", error);
        request.getRequestDispatcher("WEB-INF/error/invalid.jsp").forward(request, response);    
        return;
      }
      Person newGuy = new Person();
      newGuy.setLogin(login);
      newGuy.setPassword(password);
      newGuy.setHint(hint);
      newGuy.setName(fullName);
      newGuy.setPhone(phone);
      newGuy.setEmail(email);
      newGuy.setCommittees(committee);
      newGuy.setCan_deliver(car.equals("on") && license.equals("on"));
      newGuy.setPermission( SackLunchPermission.MEMBER);
      People.introduce(newGuy);
      request.setAttribute("flash","Welcome " + fullName +", please log in.");
      request.getRequestDispatcher("login.jsp").forward(request, response);    
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Joins an applicant to a committee";
    }// </editor-fold>

}
