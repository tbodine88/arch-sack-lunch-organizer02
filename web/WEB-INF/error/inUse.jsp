<%-- 
    Document   : inUse
    Created on : Dec 23, 2016, 7:50:09 PM
    Author     : Thomas Bodine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Item in Use Error</title>
    </head>
    <body>
        <font color="red">
        <h1>That ${item} is in use by some one else</h1>
        </red>
        
        <p>Please use another.</p>
        <p><a href="${backPage}">Try again</a></p>
        <p><a href="index">Cancel</a></p>
    </body>
</html>
