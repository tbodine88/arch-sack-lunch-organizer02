<%@page contentType="text/html" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${username == null}">
    <c:redirect url="index.jsp"/>
</c:if>
<c:if test="${permission < 15}">
    <% request.setAttribute("flash","You may not access the administration page"); %>
    <c:redirect url="/invalid"/>
</c:if>
<%-- 
    Document   : siteAdministrator
    Created on : Dec 12, 2016, 6:54:56 PM
    Author     : Thomas Bodine
    Purpose    : Site administrator function selection menu
--%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sack Lunch Site Administrator</title>
    </head>
    <body>
        <h1>Hello ${fullName} </h1>
        <h2>Choose a Task</h2>
        <p>
            <table>
            <tr>
                <td><a href="memberMan">Member Manager</a></td>
            </tr>
            <tr>
                <td><a href="itemManager">Item Manager</a></td>
            </tr>
            <tr>
                <td><a href="meetManager">Meeting Manager</a></td>
            </tr>
            <tr>
                <td><a href="scheduleDisplay.jsp">Schedule</a></td>
            </tr>
            <tr>
                <td><a href="logout">Log out</a></td>
            </tr>
        </table>
        </p>
    </body>
</html>
