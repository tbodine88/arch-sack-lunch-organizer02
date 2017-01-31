<%@page import="java.util.Enumeration"%>
<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%-- 
    Document   : xprepareToLeave
    Created on : Jan 30, 2017, 2:05:46 PM
    Author     : Owner
--%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Please stay</title>
    </head>
    <body>
    <center>
        <h1>${fullName}, you will be missed.</h1>
        
        <form method="post" action="leaveUpdate" >
        <table width="80%">
            <tr><td colspan="2" align="center"> You can rejoin any time, should you feel 
                    inclined to help out.  
                    We value and enjoy your presence and help. 
                    Thank you for all you have done</td> </tr>
            <tr><td colspan="2" align="center"><image src="images/Leaving.jpg"></td></tr>
            <tr><td align="center" colspan="2"><p>Many hands make light work.</td></tr>
            <tr>
                <td bgcolor="pink" align="center">
                    <input type="submit" name="action" value="Leave"/>
                </td>
                <td bgcolor="lime" align="center">
                    <input type="submit" name="action" value="stay"/>
                </td>
        </table>
            </form> 
        </center>
    </body>
</html>
