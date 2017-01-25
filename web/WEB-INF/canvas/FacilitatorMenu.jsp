<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%-- 
    Document   : FacilitatorMenu
    Created on : Dec 13, 2016, 5:04:44 AM
    Author     : Thomas Bodine 
--%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Facilitator Menu</title>
  </head>
  <body bgcolor="PaleTurquoise" >
   <c:set var="bureau" value='${fn:split(initParam.CommitteeName,",")}' />

    <h2>Hello ${fullName}!</h2>
    <p>
      <table width="100%">
          <caption><b>${bureau[week-1]} Committee Facilitator Menu</b></caption>
        <tr>
          <td width="8%">
            <table>
              <tr><td><img src="http://images.clipartpanda.com/lunch-clip-art-paper-sack-clipart.jpg" height="10%" width="100%"/></tr>
              <tr><td><a href="logout" bgcolor="lightblue">Logout</a></td></tr>
            </table></td>
          <td valign="justify">
            <table height=100%  width="100%" >              
              <tr ><td   width="25%" height="50%"><a href="memberMan">Member Management</a></td></tr>
              <tr ><td><a href="itemManager">Manage Items</a></td></tr>
              <tr><td><a href="meetManager">Meeting Manager</a></td></tr>
              <tr><td><a href="scheduleDisplay.jsp">Schedule </a></td></tr>
              <tr><td><a href="menuSel?sel=MemberMenu">Member Menu</a></tr>
              <tr></tr>
            </table></td>
        </tr>
      </table>
    </p>
  </body>
</html>
