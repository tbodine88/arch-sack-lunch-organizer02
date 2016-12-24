<%-- 
    Document   : invalidPassword
    Created on : Dec 23, 2016, 7:13:40 PM
    Author     : Owner
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Invalid Password Error</title>
    </head>
    <body>
        <h1>Bad Password!</h1>
        <font color="red"><h2>${flash}</h2></font>
        <a href="${backPage}">Try again</a><br/>
        <a href="index">Cancel</a><br/>
    </body>
</html>
