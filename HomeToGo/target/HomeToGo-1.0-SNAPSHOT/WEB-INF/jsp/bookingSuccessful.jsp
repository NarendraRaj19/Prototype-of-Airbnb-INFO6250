<%-- 
    Document   : bookingSuccessful
    Created on : Dec 16, 2021, 4:41:09 PM
    Author     : naren
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div align="center">
        <h1>Booking Successful! Please Check your email for confirmation!</h1>
        <a class = "btn btn-primary" href="<c:url value='/signOut.htm'/>" >Click here to Sign Out!</a><br><br>
        <img src="/HomeToGo/resources/images/success.jpg" alt="Auth-ErrorImage"><br><br>
        </div>
    </body>
</html>
