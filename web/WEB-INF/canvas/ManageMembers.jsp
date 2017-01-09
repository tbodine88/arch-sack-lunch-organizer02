
<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%-- 
    Document   : ManageMembers
    Created on : Dec 29, 2016, 8:05:23 PM
    Author     : Thomas Bodine
--%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <style type="text/css">
        
        #divid{
            overflow-y: scroll;
            height:15em;
            width:100%;
            border-style: groove;
        }

    </style>
  <title>Manage Members ${weekTag}  </title>
  <script>
      function navigateTo( url ){
          window.location.href = url;
      }
  </script>
</head>

<body>
	<c:if test="${empty perm}">
	   <c:set var="perm" value="FACILITATOR" />
	 <!-- perm is ${perm}. -->
	</c:if>
    <c:set var="userType" value="${fn:split('MEMBER FACILITATOR ADMINISTRATOR',' ')}" />
    
	<table width="100%" border="1" summary="member management forms">
	  <caption><h1>Manage Members ${weekTag} </h1></caption>
    <tr>
      <td width="20%">
	      <table width="100%" ><tr><th>Select A member</th></tr><tr><td> 
        <form name="memberSelector" action="memberMan" method="post">
            <div id="divid" >
                <table summary="memeberList" >
					<c:set var="last" value="${fn:length(rowid) == 0 ? 1 : fn:length(rowid) -1 }" /> 
					<c:forEach var="i" begin="0" end="${last}">
				<tr>
				<td>
				<input type="checkbox" name="selected" value="${i}" onchange="this.form.submit();" />
				</td>
				<td>
				${login[i]}
				</td>
				<td>
				${name[i]}
				</td>
				</tr>
				</c:forEach>          
                </table >
             </div>
        </form>
			      </td></tr></table>
      </td>

      <td>
                    <form name="memberEditor" action="updateMember" method="post">
        <table summary="frame around member editor" 
					style="border-style:inset; 
					       border-color: blue blue blue blue ;">
          <tr>
            <td>
              <table  summary="separate member fields from buttons">
                <tr>
                  <td>
                      <table border=5 style="border-style: solid;  ">
                        <!-- Top of member form -->

                        <tr>
                          <td>Rowid: ${ed_rowid}<input type="hidden" name="ed_rowid" value="${ed_rowid}"></td>

                          <td>Name: <input type="text" name="ed_name" value=
                          "${ed_name}" /></td>

                          <td>Phone: <input type="text" name="ed_phone" value=
                          "${ed_phone}" /></td>
			  <td>&nbsp;
			  </td>
                        </tr>

                        <tr>
                          <td>Email: <input type="text" name="ed_email" value=
                          "${ed_email}" /></td>

                          <td>Can_deliver: 
                              <c:choose>
                                  <c:when test="${fn:contains(ed_can_deliver,'yes')}" >
                                  <input type="checkbox" name="ed_can_deliver" checked />
                                  </c:when>
                                  <c:otherwise>
                                  <input type="checkbox" name="ed_can_deliver"  /> 
                                  </c:otherwise>
                              </c:choose>
                              
                          </td>
                            <c:choose>
				<c:when test="${perm == 'ADMINISTRATOR'}"  >
					<td> Committees: <input type="text" name="ed_committees" value="${ed_committees}" /> </td>
					<td> Permission: 
                                            <select name="ed_permission">
                                                <c:forEach items="${userType}" var="ut" > 
                                                    <c:if test="${fn:contains(ed_permission,ut)}" >
                                                    <option value="${ut}" selected >
                                                       ${ut}
                                                    </option>
                                                    </c:if>
                                                </c:forEach>
                                                <c:forEach items="${userType}" var="ut" > 
                                                    <c:if test="${not fn:contains(ed_permission,ut)}" >
                                                    <option value="${ut}"  >
                                                        ${ut}
                                                    </option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                        </td>
					</tr><tr>
					<td> Login: <input type="text" name="ed_login" value="${ed_login}" /> </td>
					<td> Password: <input type="text" name="ed_password" value="${ed_password}" /> </td>
					<td> Hint: <input type="text" name="ed_hint" value="${ed_hint}" /> </td>
					<td> Success: <input type="text" name="ed_Success" value="${ed_Success}" /> </td>
					</tr><tr>
					<td> Failure: <input type="text" name="ed_failure" value="${ed_failure}" /> </td>
					<td> Fail_count: <input type="text" name="ed_fail_count" value="${ed_fail_count}" /> </td>
					<td> Updated: <input type="text" name="ed_updated" value="${ed_updated}" /> </td>
				</c:when>
				<c:otherwise>
					<td> Committees: ${ed_committees}  </td>
				</c:otherwise>
                            </c:choose>                        
			</tr>
                      </table>
                  </td>
                </tr>
              </table>
	    </td>
	  </tr>
	  <tr>
		<td align=center:w>
			<table 
			  style="
				padding-top:    1px;
        padding-right:  1px;
        padding-bottom: 1px;
        padding-left:   1px;
				width: 100%;
				"
				><tr>
                                <td align=center><input type="submit" name="activity" value="add"/></td>
                                <td align=center><input type="submit" name="activity" value="edit"/></td>
                                <td align=center><input type="submit" name="activity" value="delete"/></td></tr></table>
		</td>
	  </tr>
        </table>
                    </form><!-- end of member editor -->

        <table width="100%">
          <tr>
	    <td width="40%"><a href="memberMan">Clear Form</a></td>
            <td><a href="${menu}"> Main Menu </a></td>
            <td><a href="logout">Log Out</a></td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
</body>
</html>
