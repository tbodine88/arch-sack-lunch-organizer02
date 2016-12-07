<%-- 
    Document   : loggedin
    Created on : Dec 6, 2016, 9:04:54 PM
    Author     : Owner
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<%@ page language="java" import="java.util.*" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logged In</title>
    </head>
    <body>
        <h1>You are Logged in</h1>
Session attributes:
<%
    for (Enumeration e = session.getAttributeNames(); e.hasMoreElements(); ) {     
    String attribName = (String) e.nextElement();
    Object attribValue = session.getAttribute(attribName);
%>
<BR><%= attribName %> - <%= attribValue %>

<%
}
%>    </body>
</html>
