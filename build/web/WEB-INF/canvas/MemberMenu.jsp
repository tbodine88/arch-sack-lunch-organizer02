<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%-- 
   Document   : FacilitatorMenu
   Created on : Dec 13, 2016, 5:04:44 AM
   Author     : Thomas Bodine
   --%>
<!DOCTYPE html>
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Committee Member Menu</title>
      <script type="text/javascript">
          document.getElementById("donation").addEventListener("keyup", function(event) {
            event.preventDefault();
            if (event.keyCode === 13) {
                document.getElementById("rsvp").click();
            }
          });
      </script>
   </head>
   <body>
      <c:set var="bureau" value='${fn:split(initParam.CommitteeName,",")}' />
      <h2>Hello ${fullName}!</h2>
      <p>
      <table width="100%" >
         <caption>
            <font size="+2"><b>${bureau[week-1]} Committee Member Menu</b></font>
         </caption>
         <tr>
            <td width="50%">
               <table   width="100%">
                  <tr>
                     <td width="16%">
                        <table>
                           <tr>
                              <td width="25%"><img src="http://images.clipartpanda.com/lunch-clip-art-paper-sack-clipart.jpg" height="10%" width="100%"/></td>
                              <td>&nbsp;</td>
                           </tr>
                           </td>
                           <td>
                              &nbsp; 
                           </td>
                           </tr>
                        </table>
                  <tr>
                     <td>
                        <table width="100%">
                           <tr>
                              <td >
                                 <table border="4" bordercolor="black">
                                    <tr>
                                       <td bgcolor="lightblue">
                                          <a href="logout" >Logout</a>
                                       </td>
                                    <tr>
                                 </table>
                              </td>
                              <td width="40%">&nbsp;</td>
                              <td >
                                 <table bordercolor="black" border="4" >
                                    <tr>
                                       <td bgcolor="grey">
                                          <a href="/leave" >Leave Committee</a> 
                                       </td>
                                    </tr>
                                 </table>
                              </td>
                           </tr>
                        </table>
                     </td>
                  </tr>
                  <tr>
                     <td align="left">
                        <table  width="100%">
                           <tr>
                              <th align="right">Message for</th>
                              <td>
                                 <b>
                                    <table  width="100%">
                                       <tr>
                                           <td><input type="radio" name="mailto" value="Facilitator" checked="true"/>Facilitator (<i>name here</i>)</td>
                                       </tr>
                                       <tr>
                                          <td><input type="radio" name="mailto" value="Committee"/>Committee</td>
                                       </tr>
                                    </table>
                                 </b>
                              </td>
                        </table>
                     </td>
                  </tr>
                  <tr>
                     <td><textarea name="message" rows=10 cols=50 maxlength="2000"></textarea></td>
                  </tr>
                  <tr>
                     <td align="center"><input id="sndMess" type="submit" value="Send Message"/></td>
                  </tr>
               </table>
            </td>
            <td valign="middle">
               <form id="donation" action="memberChoice" >
                  <table border="1">
                     <caption>
                        <b>Choose something to bring the Week of ${date}</b> 
                     </caption>
                     <tr>
                        <th>Item Needed</th>
                        <th>Contributor</th>
                     </tr>
                     <!-- c:forEach var="thx" begin="1" end="${donation_size}" > -->
                     <tr>
                        <td><input type="checkbox" name="donation" value="${thing[i]}" ></td>
                        <td>${donator[i]}</td>
                     <tr>
                        <td><input type="checkbox" name="donation" value="${thing[i]}" ></td>
                        <td>${donator[i]}</td>
                     <tr>
                        <td><input type="checkbox" name="donation" value="${thing[i]}" ></td>
                        <td>${donator[i]}</td>
                     <tr>
                        <td><input type="checkbox" name="donation" value="${thing[i]}" ></td>
                        <td>${donator[i]}</td>
                     <tr>
                        <td><input type="checkbox" name="donation" value="${thing[i]}" ></td>
                        <td>${donator[i]}</td>
                     <tr>
                        <td><input type="checkbox" name="donation" value="${thing[i]}" ></td>
                        <td>${donator[i]}</td>
                     <tr>
                        <td><input type="checkbox" name="donation" value="${thing[i]}" ></td>
                        <td>${donator[i]}</td>
                     <tr>
                        <td><input type="checkbox" name="donation" value="${thing[i]}" ></td>
                        <td>${donator[i]}</td>
                     <tr>
                        <td><input type="checkbox" name="donation" value="${thing[i]}" ></td>
                        <td>${donator[i]}</td>
                     </tr>
                     <!-- /c:forEach> -->
                     <tr>
                        <td colspan="2" align="center"><input id="rsvp" type="submit" value="RSVP &amp; bring selected item(s)"></td>
                     </tr>
                  </table>
      </table>
      </td>
      </tr>
      </form>
      </p>
   </body>
</html>
