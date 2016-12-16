<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  <body>
    
    <h2>Hello ${name}!</h2>
    <p>
      <table width="100%">
          <caption><b>${week} Committee Facilitator Menu</b></caption>
        <tr>
          <td width="8%">
            <table>
              <tr><td><img src="http://images.clipartpanda.com/lunch-clip-art-paper-sack-clipart.jpg" height="10%" width="100%"/></tr>
              <tr><td><a href="/logout" bgcolor="lightblue">Logout</a></td></tr>
            </table></td>
          <td valign="justify">
            <table height=100%  width="100%" >              
              <tr ><td   width="25%" height="50%"><a href="one">one</a></td><td width="25%"><a href="two">two</a></td><td width="25%"><a href="three">three</a></td><td><a href="four">four</a></td></tr>
              <tr ><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
              <tr><td><a href="one">one</a></td><td><a href="two">two</a></td><td><a href="three">three</a></td><td><a href="four">four</a></td></tr>
              <tr></tr>
            </table></td>
        </tr>
      </table>
    </p>
  </body>
</html>
