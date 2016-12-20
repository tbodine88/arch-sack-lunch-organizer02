<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
    Document   : groupChooser
    Created on : Dec 18, 2016, 7:26:50 AM
    Author     : Thomas Bodine
--%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Group Chooser Menu</title>
    </head>
    <body>
        <h1>Which week are you volunteering for?</h1>
        <form action="/groupChosen" >
            <table>
                <tr>
                    <c:forEach items="committee" var="comname" >    
                        <th> "${comname}"</th>
                    </c:forEach>
                </tr>
                <tr>
                    <c:forEach var="i" begin="0" end="4" >
                        <td>
                            <input type="radio" name="selectedGroup" value="$i" >"${meetday[i]}"
                        </td>
                    </c:forEach> 
                </tr>
                <tr><td colspan="5"><input type="submit" name="submit"></tr>
            </table>
        </form>
    </body>
</html>
