<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%-- 
    Document   : ManageMeetings
    Created on : Jan 9, 2017, 7:08:55 AM
    Author     : Owner
--%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Meetings</title>
    </head>
    <body>
        <h1>Manage Meetings</h1>
        <h2>${user}</h2>
        <p>perm ${perm}</p>
        <c:forEach var="i" begin="0" end="${fn:length(meetings)}" >
            ${meetings[i]} <br/>
        </c:forEach>
        
    </body>
</html>
