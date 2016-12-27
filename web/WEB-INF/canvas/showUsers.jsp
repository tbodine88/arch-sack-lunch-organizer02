<%-- 
    Document   : showUsers
    Created on : Dec 27, 2016, 10:32:10 AM
    Author     : Owner
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Showing Users</title>
    </head>
    <body>
        
        ${fn:length(xavier)}
        <h1>The User Table</h1>
        <table border="1">
            <tr>
                <th>rowid</th>
                <th>name</th>
                <th>phone</th>
                <th>email</th>
                <th>can_deliver</th>
                <th>updated</th>
                <th>comittees</th>
                <th>login</th>
                <th>password</th>
                <th>hint</th>
                <th>Success</th>
                <th>failure</th>
                <th>failure_count</th>
                <th>permission</th>
            </tr>
            <c:set var="k" value="0" />
            <c:forEach var="i" begin="0" end="${fn:length(name)}" >
            <tr>
                <td>${rowid[i]}</td>
                <td>${name[i]}</td>
                <td>${phone[i]}</td>
		<td>${email[i]}</td>
		<td>${can_deliver[i]}</td>
		<td>${updated[i]}</td>
		<td>        
                    ${committees[k]}
                    <c:set var="k" value="${i}" />
                </td>
		<td>${login[i]}</td>
		<td>${password[i]}</td>
		<td>${hint[i]}</td>
		<td>${Success[i]}</td>
		<td>${failure[i]}</td>
		<td>${xavier[i]}</td>
		<td>${permission[i]}</td>
            </tr>
            <c:set var="k" value="${k+1}" />
            </c:forEach>
        </table>
    </body>
</html>
