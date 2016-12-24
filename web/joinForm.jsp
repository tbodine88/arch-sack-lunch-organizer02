<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%-- 
    Document   : join
    Created on : Dec 7, 2016, 4:51:23 AM
    Author     : Thomas Bodine 
--%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Join ARCH Sack Lunch Comittee Menu</title>
    </head>
    <body>
        <c:set var="bureau" value='${fn:split(initParam.CommitteeName,",")}' />
        <c:set var="last" value='${fn:length(bureau)}' />
        <table  width="100%">
            <tr>
                <td width="10%" valign="top">
                    <table height="100%">
                        <tr><td height="20%"><img src="http://images.clipartpanda.com/lunch-clip-art-paper-sack-clipart.jpg" height="10%" width="100%"/></td></tr>
                        <tr><td><a href="index.jsp" >Cancel</a></td></tr>
                        <tr><td></td></tr>
                    </table>
                </td><td>
        <h1>Join a Committee</h1>
        <form method="POST" action="joiner">
        <table border="1">
   <tr><th align="left">login</th><td> <input type=text name=login></input>     </td></tr>
   <tr><th align="left">password &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th><td> <input type=text name=password1></input> </td></tr>
   <tr><th>password (again)</th><td> <input type=text name=password2></input> </td></tr>
   <tr><td colspan="2"></td></tr>
   <tr><th colspan="2">Pick a week to make <br> lunches for the ARCH</th></tr>
   <c:set var="i" value="1" />
   <c:forEach items="${bureau}" var="day"  >
       <tr><th>${day}</th><td><input type="checkbox" name="committee" value="${i}" /></td></tr>
       <c:set var="i" value="${i + 1}" />
   </c:forEach>
   
   <tr><td colspan="2"><br></td></tr>
   <tr><th>name</th><td> <input type=text name=name></input> </td></tr>
   <tr><th>phone</th><td> <input type=text name=phone></input> </td></tr>
   <tr><th>email</th><td> <input type=text name=email></input> </td></tr>
   <tr><td> <input type="checkbox" name="car">Have car?</input></td>
       <td><input type="checkbox" name="license">Have driver's license?</input> </td>
   </tr>
   <tr><th>hint</th><td> <input type=text name=hint></input> </td></tr>
   <tr><td colspan="2" align="center"><input type="submit" value="join now"></input></td></tr>
</table>
        </form>
                </td></tr></table>
    </body>
</html>
