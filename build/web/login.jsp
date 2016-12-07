<%-- 
    Document   : login
    Created on : Dec 6, 2016, 8:54:21 PM
    Author     : Owner
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login</h1>
        <form method="POST" action="/arch-sack-lunch-organizer02/go">
            UserName: <input type="text" name="username"/><br/>
            Password: <input type="text" name="password"/><br/>
            <input type="submit" value="login"/><br/> 
        </form>
    </body>
</html>
