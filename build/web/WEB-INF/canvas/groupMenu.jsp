<%-- 
    Document   : groupMenu
    Created on : Dec 20, 2016, 7:41:54 PM
    Author     : Thomas Bodine
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Choose The Meeting</title>
  </head>
  <body>
    <c:set var="bureau" value="${initParam.CommitteeName}" />
    <c:choose> 
      <c:when test="${fn:contains(perm,'MEMBER')}">
        <h1>Choose Committee and meeting to volunteer for </h1>
        <c:set var="menu" scope="request" value="MemberMenu"/>
      </c:when>
      <c:otherwise>
        <h1>Choose a committee to Facilitate</h1>
        <c:set var="menu" scope="request" value="FacilitatorMenu"/>
      </c:otherwise>
    </c:choose>
    <form action="chooseGroup">
    <table border="1">
      <caption> meetings:</caption>  
      <tr>
          <c:forTokens items="${bureau}" delims="," var="label" >
          <th>${label}</th>
          </c:forTokens>
      </tr>
      <tr>
        <c:forTokens items="${group}" delims="," var="i" >
          <th><input type="radio" value="${i}" name="committee" onchange="this.form.submit();" >${meetDate[i-1]} : ${i}</th>
        </c:forTokens>
      </tr>
      <tr>
        <td colspan=5><input type=submit value="choose"/></td>
      </tr>
    </table>  
    </form>
  </body>
</html>
