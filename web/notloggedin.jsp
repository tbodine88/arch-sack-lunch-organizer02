<%-- 
    Document   : notloggedin
    Created on : Dec 6, 2016, 9:11:56 PM
    Author     : Owner
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" import="java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Not Logged In</title>
    </head>
    <body>
        <h1>You are not logged in!</h1>
        <font color="red"><h2 class="flash">${flash}</h2></font>
    </body>
  
</html>
