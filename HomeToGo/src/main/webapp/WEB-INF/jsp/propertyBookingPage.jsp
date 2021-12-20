<%-- 
    Document   : propertyBookingPage
    Created on : Dec 16, 2021, 2:47:48 PM
    Author     : naren
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet"
              href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link
            rel="stylesheet"
            href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"

            crossorigin="anonymous"
            />
        <link rel="manifest" href="favicon/site.webmanifest">
        <title>JSP Page</title>
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
<!--        <h3>Welcome<strong> ${user.username}!</strong></h3>-->
        <jsp:include page="loginHeaderSignOut.jsp"></jsp:include>
        <br><br>
        <div align="center">
            <h1>Confirm Booking Details</h1>
            <c:set var="PropertyInfo" value="${property}" />

            <form action="<c:url value='/user/${PropertyInfo.propertyID}/${user.username}/bookingSuccess.htm'/>" method="post">
                <table class="login">
                    <tr>
                        <td>
                            Property Address:
                        </td>
                        <td>
                            <strong><c:out value="${PropertyInfo.propertyAddress}"></c:out></strong><br>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                City:
                            </td>
                            <td>
                                <strong><c:out value="${PropertyInfo.propertyCity}"></c:out></strong><br>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                State:
                            </td>
                            <td>
                                <strong><c:out value="${PropertyInfo.propertyState}"></c:out></strong><br>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Cost Per Day:
                            </td>
                            <td>
                                <strong><c:out value="${PropertyInfo.propertyCost}"></c:out></strong><br>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Owned By:
                            </td>
                            <td>
                                <strong><c:out value="${PropertyInfo.propertyOwner}"></c:out></strong><br>
                            </td>
                        </tr>
                    </table>

                    <br>
                    <br>

                    <strong>Which Date you are looking At Checking In ?</strong>
                    <select name="startDate" id="startDate">
                    <c:forEach var="date" items="${populatedDates}">
                        <option value="${date}">${date}</option>
                    </c:forEach>
                </select>
                <br><br>

                <strong>Which Date you are looking At Checking Out ?</strong>
                <select name="endDate" id="endDate">
                    <c:forEach var="date" items="${populatedDates}">
                        <option value="${date}">${date}</option>
                    </c:forEach>
                </select>
                <br><br>
                <center><input class="btn btn-primary"id="submitButton" type="submit" value="Confirm"/></center>
            </form>
        </div>

    </body>
</html>
