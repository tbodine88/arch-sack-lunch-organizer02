<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%-- 
    Document   : itemManage
    Created on : Jan 3, 2017, 6:14:22 AM
    Author     : Thomas Bodine
--%>


<!DOCTYPE html>
<html>
    <head>
        <style type="text/css">
            
            #divid{
                overflow-y: scroll;
                height:15em;
                width:100%;
		border-style: double double double double; 
		border-color: green;
            }

        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Item Manage</title>
    </head>
    <body>
        <c:if test="${empty perm}">
	   <c:set var="perm" value="FACILITATOR" />
	 <!-- perm is ${perm}. -->
	</c:if>
	<c:set var="band1" value="${itemGroupName[1]}" />
	<c:set var="band2" value="${itemGroupName[2]}" />
	<c:set var="band3" value="${itemGroupName[3]}" />
	<c:set var="band4" value="${itemGroupName[4]}" />
	<c:set var="band5" value="${itemGroupName[5]}" />
        <h1>Manage Items</h1>
        <h2>${week}</h2>
        <table width="100%" summary="Item management forms" border=1>
            <tr>
                <td align="left" width="10%">
                    <table summary="left hand pane">
                        <tr><td>&nbsp;</td></tr>
                        <tr><td><a href="${menu}">Back</a></td></tr>
                        <tr><td><a href="logout">Log out</a></td></tr>
                        <tr><td></td></tr>
                    </table>
                </td>
                <td align="left" width="60%">
                    <table width="100%" summary="center pane">
                        <tr>
                            <td>
                                <table width="100%"><tr><th width=10%>Item ID</th><th width=40% align=center>Committee</th><th align=left>Item</th></table> 
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <form method="upDateItem" >
				  <div id="divid" >
                                    <table summary="item list" width=100% border=1>
                                    <c:set var="last" value="${fn:length(itemid) == 0 ? 1 : fn:length(itemid) -1 }" /> 
                                    <c:forEach var="i" begin="0" end="${last}">
                                        <tr>
						<td width=10%> <input type="checkbox" name="elect" value="${itemid[i]}" onchange="this.form.submit();" /> ${itemid[i]} </td> <td width=40% align=center> 
							<c:set var="gindex" value="${itemGroup[i]}" />
							<c:if test="${fn:contains(gindex,'1')}" >
							  ${band1} &nbsp;
							</c:if>
							<c:if test="${fn:contains(gindex,'2')}" >
							  ${band2} &nbsp;
							</c:if>
							<c:if test="${fn:contains(gindex,'3')}" >
							  ${band3} &nbsp;
							</c:if>
							<c:if test="${fn:contains(gindex,'4')}" >
							  ${band4} &nbsp;
							</c:if>
							<c:if test="${fn:contains(gindex,'5')}" >
							  ${band5} &nbsp;
							</c:if>
				            </td>
				            <td  align=left>
				            ${item[i]}
				            </td>
				        </tr>
                                    </c:forEach>
				  </div >
                            </form>
                            </td>
                        </tr></table>
                        <tr></tr>
                    </table>
                </td>
                <td align="left">
                    <table summary="right hand pane">
                        <tr>
                        <form method="upDateItem">
				<c:set var="chosen" value="${itemGroup[elected]}" />
                            <table summary="item input" >
                                <tr>
                                    <td>
                                        <input type="hidden" name="rowid" value="${rowid}" />
                                        <c:choose > 
                                        <c:when test="${fn:contains(chosen,'1')}">
                                            <input type="checkbox" name="itemGroup" value="1" checked />
                                        </c:when>
                                        <c:otherwise>
                                            <input type="checkbox" name="itemGroup" value="1"/>
                                        </c:otherwise>
                                        </c:choose>
                                        first
                                    </td>
                                    <td>
                                        <c:choose > 
                                        <c:when test="${fn:contains(chosen,'4')}">
                                            <input type="checkbox" name="itemGroup" value="4" checked />
                                        </c:when>
                                        <c:otherwise>
                                            <input type="checkbox" name="itemGroup" value="4"/>
                                        </c:otherwise>
                                        </c:choose>
                                        fourth
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <c:choose > 
                                        <c:when test="${fn:contains(chosen,'2')}">
                                            <input type="checkbox" name="itemGroup" value="2" checked />
                                        </c:when>
                                        <c:otherwise>
                                            <input type="checkbox" name="itemGroup" value="2"/>
                                        </c:otherwise>
                                        </c:choose>
                                        second
                                    </td>
                                    <td>
                                        <c:choose > 
                                        <c:when test="${fn:contains(chosen,'5')}">
                                            <input type="checkbox" name="itemGroup" value="5" checked />
                                        </c:when>
                                        <c:otherwise>
                                            <input type="checkbox" name="itemGroup" value="5"/>
                                        </c:otherwise>
                                        </c:choose>
                                        fifth
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <c:choose > 
                                        <c:when test="${fn:contains(chosen,'3')}">
                                            <input type="checkbox" name="itemGroup" value="3" checked />
                                        </c:when>
                                        <c:otherwise>
                                            <input type="checkbox" name="itemGroup" value="3"/>
                                        </c:otherwise>
                                        </c:choose>
                                        third
                                    </td>
                                    <td>
                                        &nbsp;
                                    </td>
                                </tr>
                                <tr><td colspan="2"><input type="text" name="item" value="${item[elected]}"/></td></tr>
				<tr><td colspan=2>
				     <table><tr>
                                    <td><input type="submit" name="activity" value="add"/></td>
				    <td><input type="submit" name="activity" value="delete" /></td>    
                                    <td><input type="submit" name="activity" value="update"/></td>
				     </tr></table>
				</td></tr>
                            </table>
                        </table>
                        </form>
                        </tr>
                        <tr></tr>
                        <tr></tr>
                </td>
            </tr>
        </table>
    </body>
</html>
