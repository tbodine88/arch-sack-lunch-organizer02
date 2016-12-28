<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%-- 
    Document   : ManageMembers
    Created on : Dec 27, 2016, 8:05:23 PM
    Author     : Thomas Bodine
--%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Members ${weekTag}</title>
        <style>
            div.scroller {
                width: 100%;
                height: 20px;
                overflow-y: scroll;
                overflow-x: scroll;
            }
        </style>
    </head>
    <body>
        <h1>Manage Members ${weekTag}</h1>
        <table width="100%" border="1">
            <tr>
                <td width="40%">
            <div.scroller>
                <table>
                    <c:forEach var="i" begin="0" end="${fn:length(rowid)}">
                        <tr>
                            <td>
                                <input type="checkbox" name="sel${rowid[i]}" />
                            </td>
                            <td>
                                ${login[i]}
                            </td>
                            <td>
                                ${name[i]}
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div.scroller>
                </td>
                <td>
 <table >
<tr>
<td> Rowid: <input type="text" name="ed_rowid" value="${ed_rowid}" /> </td>
<td> Name: <input type="text" name="ed_name" value="${ed_name}" /> </td>
<td> Phone: <input type="text" name="ed_phone" value="${ed_phone}" /> </td>
<tr>
<td> Email: <input type="text" name="ed_email" value="${ed_email}" /> </td>
<td> Can_deliver: <input type="text" name="ed_can_deliver" value="${ed_can_deliver}" /> </td>
<td> Committees: <input type="text" name="ed_committees" value="${ed_committees}" /> </td>
<td> Permission: <input type="text" name="ed_permission" value="${ed_permission}" /> </td>
<tr>
<td> Login: <input type="text" name="ed_login" value="${ed_login}" /> </td>
<td> Password: <input type="text" name="ed_password" value="${ed_password}" /> </td>
<td> Hint: <input type="text" name="ed_hint" value="${ed_hint}" /> </td>
<td> Success: <input type="text" name="ed_Success" value="${ed_Success}" /> </td>
<tr>
<td> Failure: <input type="text" name="ed_failure" value="${ed_failure}" /> </td>
<td> Fail_count: <input type="text" name="ed_fail_count" value="${ed_fail_count}" /> </td>
<td> Updated: <input type="text" name="ed_updated" value="${ed_updated}" /> </td>
</table >
                </td>
            </tr>
        </table>
    </body>
</html>
