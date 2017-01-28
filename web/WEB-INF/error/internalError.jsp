<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %><%-- 
    Document   : internalError
    Created on : Jan 28, 2017, 5:19:13 AM
    Author     : Thomas Bodine
--%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Internal Error</title>
    </head>
    <body>
        menu ${menu}
        <c:if test="${empty menu}" >
            <c:set var="menu" value="index.jsp" />
        </c:if>
        
        <meta http-equiv="refresh" content="20;url=${menu}" />
        <h1>Tom Bodine made a boo boo</h1>
        <table><tr><td><a href="${menu}">try again</a></td><td><a href="logout">Log out</a></td></tr></table>
        <font color="red">
        <table>
            <tr><td><img src="http://www.carlswebgraphics.com/ant-images/ants-sandwich.jpg"></td></tr>
            <tr><td>${flash}</td></tr>
            <c:forEach var="i" begin="0" end="${fn:length(error)}">
            <tr><td>${error[i]}</font></td></tr>
            </c:forEach>
        </table>
        </font>
    </body>
</html>
