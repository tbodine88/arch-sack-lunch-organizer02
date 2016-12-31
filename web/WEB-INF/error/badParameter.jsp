<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%-- 
    Document   : badParameter
    Created on : Dec 23, 2016, 9:14:41 PM
    Author     : Thomas Bodine
--%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bad Parameter Error</title>
    </head>
    <body bgcolor="red">
        <font color="white">
        menu ${menu}
        <c:if test="${empty menu}" >
            <c:set var="menu" value="index.jsp" />
        </c:if>
        <meta http-equiv="refresh" content="20;url=${menu}" />
        <c:if test="${not empty item}" >
        <h1>There are Bad Parameters</h1>
        <font color="black"><h2>${item} was empty or null</h2></font>
        </c:if>
        <c:if test="${not empty error}" >
            <c:forEach var="i" begin="0" end="${fn:length(error)}" >
                <h2>${error[i]}</h2>
            </c:forEach>
            <c:remove var="error" scope="request"/>
        </c:if>
        </font>
        <a href="${menu}">Back</a>
    </body>
</html>
