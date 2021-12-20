<%-- 
    Document   : userSearchResults
    Created on : Dec 16, 2021, 8:53:43 AM
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
        <link
            rel="stylesheet"
            href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"

            crossorigin="anonymous"
            />
        <link rel="manifest" href="favicon/site.webmanifest">
    </head>
    <style>
        body{
            background-color: "#FFFAF0";
        }
        .login {
            font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
            border-collapse: collapse;
        }

        .login td, .allMenu th {
            border: 1px solid #86c475;
            padding: 8px;
        }

        /* .login tr:nth-child(even){background-color: #f4efed;} */

        .login tr:hover {background-color: #4be156;}
        /*            rgba(228,186,81,0.80)*/

        .login th {
            padding-top: 12px;
            padding-bottom: 12px;
            padding-left: 5px;
            padding-right: 5px;
            text-align: center;
            background-color: #86c475;
            color: #ffffff;
        }
    </style>
    <body>
        <jsp:include page="loginHeaderSignOut.jsp"></jsp:include>
        <div align="center">
            <h1>Home Properties that Match Your Search Criteria!</h1>

            <table class='login'>
                <tr>
                    <th>ID</th>
                    <th>Address</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Cost</th>
                    <th>Type</th>
                    <th>Owned By</th>
                    <th>Interested</th>
                </tr>

                <c:forEach var="j" items="${searchResults}">  
                    <tr>
                        <td><c:out value="${j.propertyID}"/></td>
                        <td><c:out value="${j.propertyAddress}"/></td>
                        <td><c:out value="${j.propertyCity}"/></td>
                        <td><c:out value="${j.propertyState}"/></td>
                        <td><c:out value="${j.propertyCost}"/></td>
                        <td><c:out value="${j.propertyType}"/></td>
                        <td><c:out value="${j.propertyOwner}"/></td>
                        <td><a href="<c:url value='/user/${j.propertyID}/booking.htm'/>"><i class="fas fa-check text-success" style="font-size: 40px;"></i></a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
