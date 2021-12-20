<%-- 
    Document   : SignUpHome
    Created on : Dec 12, 2021, 8:46:45 AM
    Author     : naren
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Sign Up Page</h2>
        <c:set var="contextPath" value="${pageContext.request.contextPath}" />
        <a href="${contextPath}/user.htm"> Register As User</a><br>
        <a href="${contextPath}/property.htm"> Register As Property Owner</a><br>
        <c:out value="${contextPath}"/>
    </body>
</html>
