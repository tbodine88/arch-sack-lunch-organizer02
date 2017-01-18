<%@page import="com.tommythegeek.sackLunch.dao.MeetingList"%>
<%@page import="com.tommythegeek.sackLunch.dao.Schedule"%>
<%@page contentType="text/html" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:if test="${username == null}">
    <c:redirect url="index.jsp"/>
</c:if>
<c:if test="${permission < 15}">
    <% request.setAttribute("flash","You may not access the scheduleDisplay page"); %>
    <c:redirect url="/invalid"/>
</c:if>
<%-- 
    Document   : scheduleDisplay
    Created on : Jan 16, 2017, 6:33:11 AM
    Author     : Thomas Bodine
--%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Schedule Display Page</title>
    </head>
    <body>
        <h1>Schedule</h1>
        
        <%
            Schedule sked = (Schedule) application.getAttribute("sked");
            if (sked != null){
                for( int i = 0 ; i < sked.size() ; i++){
                    out.print(sked.getMeetingByIndex(i).toString() + "<br/>");
                }
            } else {
                out.print("sked is null");
            }
            %>
        <hr/>
        <h2>Meeting List</h2>
        <%
           MeetingList ml = (MeetingList) session.getAttribute("meetings");
           if ( ml != null){
               out.print(" first " + ml.first.getDateString() + "<br/>");
               out.print("second " + ml.second.getDateString() + "<br/>");
               out.print(" third " + ml.third.getDateString() + "<br/>");
               out.print("fourth " + ml.fourth.getDateString() + "<br/>");
               out.print(" fifth " + ml.fifth.getDateString() + "<br/>");
               
           } else {
              out.print("MeetingList is null");
           }
        %>
    </body>
</html>
