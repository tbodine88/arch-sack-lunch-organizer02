<%-- 
    Document   : join
    Created on : Dec 7, 2016, 4:51:23 AM
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
        <h1>Join a Committee</h1>
        <form method="POST" >
        <table border="1">
   <tr><th align="left">login</th><td> <input type=text name=login></input>     </td></tr>
   <tr><th align="left">password &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th><td> <input type=text name=password1></input> </td></tr>
   <tr><th>password (again)</th><td> <input type=text name=password2></input> </td></tr>
   <tr><td colspan="2"></td></tr>
   <tr><th colspan="2">Pick a week to make <br> lunches for the ARCH</th></tr>
   <tr><td colspan="2"> <input type=checkbox name=committee value="First">First Monday</input></td></tr>
   <tr><td colspan="2"> <input type=checkbox name=committee value="Second">Second Monday</input></td></tr>
   <tr><td colspan="2"> <input type=checkbox name=committee value="Third"></input>Third Monday</td></tr>
   <tr><td colspan="2"> <input type=checkbox name=committee value="Fourth"></input>Fourth Monday</td></tr>
   <tr><td colspan="2"> <input type=checkbox name=committee value="Fifth"></input>Fifth Monday</td></tr>
   <tr><td colspan="2"><br></td></tr>
   <tr><th>name</th><td> <input type=text name=name></input> </td></tr>
   <tr><th>phone</th><td> <input type=text name=phone></input> </td></tr>
   <tr><th>email</th><td> <input type=text name=email></input> </td></tr>
   <tr><td> <input type="checkbox" name="car">Have car?</input></td>
       <td><input type="checkbox" name="license">Have driver's license?</input> </td>
   </tr>
   <tr><th>updated</th><td> <input type=text name=updated></input>           </td></tr>
   <tr><th>hint</th><td> <input type=text name=hint></input> </td></tr>
   <tr><th>Success</th><td> <input type=text name=Success></input> </td></tr>
   <tr><th>failure</th><td> <input type=text name=failure></input> </td></tr>
   <tr><th>fail_count</th><td> <input type=text name=fail_count></input> </td></tr>
   <tr><th>permission</th><td> <input type=text name=permission></input> </td></tr>
   <tr><td colspan="2" align="center"><input type="submit" value="join now"></input></td></tr>
</table>
        </form>

    </body>
</html>
