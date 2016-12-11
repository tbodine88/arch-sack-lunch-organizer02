<%@page contentType="text/html" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${username == null}">
    <c:redirect url="index.jsp"/>
</c:if>

<%-- 
    Document   : loggedin
    Created on : Dec 6, 2016, 9:04:54 PM
    Author     : Owner
--%>
<!DOCTYPE html>
<%@ page language="java" import="java.util.*" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logged In</title>
    </head>
    <body>
        <h1>You are Logged in</h1>
        <p><a href="logout">Logout</a></p>
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
