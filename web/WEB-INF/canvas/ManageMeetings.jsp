<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%-- 
    Document   : ManageMeetings
    Created on : Jan 9, 2017, 7:08:55 AM
    Author     : Owner
--%>
<c:set var="ed_month" value="1" />
<c:set var="ed_day"  value="16" />
<c:set var="ed_year" value="2017" />
<c:set var="selectableMonth" value="${fn:split('&nbsp; Jan Feb Mar Apr May Jun Jul Aug Sep Oct Nov Dec',' ')}" />
<c:set var="groupName" value="${fn:split('First Second Third Fourth Fifth',' ')}" />

        
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Meetings</title>
    </head>
    <body>
        <h1>Manage Meetings</h1>
        <h2>for 
            <c:choose>
                <c:when test="${fn:contains(perm,'ADM')}" >
                    All
                </c:when>
                <c:otherwise>
                    the 
                    ${fn:contains(group,'1') ? 'First ' : ''} 
                    ${fn:contains(group,'2') ? 'Second ' : ''} 
                    ${fn:contains(group,'3') ? 'Third ' : ''} 
                    ${fn:contains(group,'4') ? 'Fourth ' : ''} 
                    ${fn:contains(group,'5') ? 'Fifth ' : ''}
                </c:otherwise>
            </c:choose>
            Monday Sack Lunch Committee${fn:contains(perm,'ADM') ? 's' : ''}
        </h2>
        
        <table width="100%">
            <tr>
                <td class="leftColumn" bgcolor="lightblue" width="20%">
                    <table>
                        <tr><td><a href="${menu}">Back</a></td></tr>
                        <tr><td>&nbsp;</td></tr>
                        <tr><td>&nbsp;</td></tr>
                        <tr><td>&nbsp;</td></tr>
                        <tr><td><a href="logout">Logout</a></td></tr>
                    </table>
                </td>
                <td class="centerColumn" bgcolor="pink" width="60%">
                    <form action="meetManager" method="POST" >
                    <table width="100%">
                        <tr><td><table  width="100%">
                                    <tr><th width="10%">Meeting ID</th>
                                        <th width="30%" align="center">Group</th>
                                        <th align="left">Date</th></tr></table></td></tr>
                                <c:forEach var="i" begin="0" end="${fn:length(meetings)-1}" >
                                    <tr>
                                        <td>
                                            <table width="100%">
                                                <tr>
                                                    <td width="10%"><input type="checkbox" name="selected" value="${i}" onchange="this.form.submit();" >${i}</td>
                                                    <td width="30%" align="center" >${groupName[i]}</td>
                                                    <td>${meetings[i]}</td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                </c:forEach>
                        </table>
                        </form>
                </td>
                <td class="rightColumn" bgcolor="LightYellow">
                    <form action="updateMeeting" method="POST" >
                    <table size="100%" border="1">
                        <tr><th width="40%">Month</th><th width="10%">Day</th><th>Year</th></tr>
                        <tr>
                            <td>
                                <select name="ed_month">
                                    <c:set var="monthNumber" value="1" />
                                    <c:forEach var="month" items="${selectableMonth}">
                                        <option value="${monthNumber +1}" 
                                            ${fn:contains(ed_month,monthNumber) ? "selected='selected'" : ""}> ${month}
                                        </option>
                                        <c:set var="monthNumber" value="${monthNumber + 1}" />
                                    </c:forEach>        
                                </select>
                            </td>
                            <td><input type="text" name="ed_day" value="${ed_day}" width="2px" /></td>
                            <td>
                                <select name="ed_year">
                                    <option  value="${ed_year}">${ed_year}</option>
                                    <option  value="${ed_year + 1}">${ed_year + 1}</option>
                                </select>    
                            </td>
                    </table>
                    </form>
                </td>
            </tr>
        </table>
        <p>perm ${perm}</p>
 
    </body>
</html>
