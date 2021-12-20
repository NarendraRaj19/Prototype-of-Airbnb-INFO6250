<%-- 
    Document   : ownerDeletedProperty
    Created on : Dec 15, 2021, 4:50:44 PM
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
        <c:set var="PropetyAddress" value="${deletedProperty}" />
        <div align="center">
        <h1>Property at <strong><c:out value= "${PropetyAddress}"></c:out> </strong>Deleted By Owner!</h1>
        <a class = "btn btn-primary" href="<c:url value='/signOut.htm'/>" >Click here to Sign Out!</a><br><br>
        <img src="/HomeToGo/resources/images/rejected.jpg" alt="Auth-ErrorImage">
        </div>
    </body>
</html>
