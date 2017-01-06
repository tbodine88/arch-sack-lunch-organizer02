<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%-- 
    Document   : invalid anything
    Created on : Dec 11, 2016, 7:36:44 PM
    Author     : Thomas Bodine
--%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Invalid ${item} Error</title>
    </head>
    <body>
        <font color="red">
        <h1>${flash}</h1>
    <c:forEach var="i" begin="0" end="${fn:length(error)}">
        <p>${error[i]} </p> 
    </c:forEach>
        </font>
        <p>Please try again: <a href="index.jsp">Sack Lunch Home</a></p>
    </body>
</html>
