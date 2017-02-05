<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%-- 
    Document   : success
    Created on : Dec 29, 2016, 5:18:30 PM
    Author     : Owner
--%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Success</title>
    </head>
    <body bgcolor="forestgreen">
        menu ${menu}
        <c:if test="${empty menu}" >
            <c:set var="menu" value="index.jsp" />
        </c:if>
        <meta http-equiv="refresh" content="2;url=${menu}" />
        <h1>Success !</h1>
    <blink><h2><font color="white">${flash}</font></h2></blink>
    </body>
</html>
