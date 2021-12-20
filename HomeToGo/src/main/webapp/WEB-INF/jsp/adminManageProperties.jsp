<%-- 
    Document   : adminManageProperties
    Created on : Dec 15, 2021, 6:13:37 AM
    Author     : naren
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet"
              href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    </head>
    <style>
        .login {
            font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
            border-collapse: collapse;
        }

        .login td, .allMenu th {
            border: 1px solid #960018;
            padding: 8px;
        }

        /* .login tr:nth-child(even){background-color: #f4efed;} */

        .login tr:hover {background-color: rgba(228,186,81,0.80);}

        .login th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: center;
            background-color: #E4D9CD;
            color: #8b0000;
        }
    </style>
    <body>
        <jsp:include page="loginHeaderSignOut.jsp"></jsp:include>
        <h2>Welcome Admin!</h2>
        <div align="center">
            <h3>Approve or Deny the following requests from Property Owners who want to list on our application</h3>
            <c:set var="contextPath" value="${pageContext.request.contextPath}" />
            <table class='login'>
                <tr>
                    <th>ID</th>
                    <th>Address</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Cost</th>
                    <th>Type</th>
                    <th>Owned By</th>
                    <th>Action</th>
                </tr>

                <c:forEach var="j" items="${properties}">  
                    <tr>
                        <td><c:out value="${j.propertyID}"/></td>
                        <td><c:out value="${j.propertyAddress}"/></td>
                        <td><c:out value="${j.propertyCity}"/></td>
                        <td><c:out value="${j.propertyState}"/></td>
                        <td><c:out value="${j.propertyCost}"/></td>
                        <td><c:out value="${j.propertyType}"/></td>
                        <td><c:out value="${j.propertyOwner}"/></td>
                        <td><a href="<c:url value='/admin/${j.propertyID}/approve.htm'/>"> <button type="button" class="btn btn-success">APPROVE</button></a>
                            <a href="<c:url value ='/admin/${j.propertyID}/reject.htm'/>"> <button type="button" class="btn btn-danger">REJECT</button></a></td>
                    </tr>
                </c:forEach> 
            </table>
        </div>
    </body>
</html>
