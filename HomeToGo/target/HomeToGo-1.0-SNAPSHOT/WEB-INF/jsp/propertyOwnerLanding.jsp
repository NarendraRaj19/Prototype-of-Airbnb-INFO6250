<%-- 
    Document   : Test2
    Created on : Dec 14, 2021, 3:01:48 PM
    Author     : naren
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <style>
            .container{
                text-align: center;
            }
            body{
/*                background-color: #008B8B;*/
            }
            .btn{
                margin: 10px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="loginHeaderSignOut.jsp"></jsp:include>
        <h3>Welcome<strong> ${user.username}!</strong></h3><br><br>
        <div class="container">
            <a href="http://localhost:8081/HomeToGo/property.htm"> <button type="button" class="btn btn-warning">Add Property</button></a>
            <a href="http://localhost:8081/HomeToGo/manage-property.htm"><button type="button" class="btn btn-warning">Manage Properties</button></a>      
            <a href="http://localhost:8081/HomeToGo/view-bookings.htm"><button type="button" class="btn btn-warning">View Bookings</button></a><br><br>   
            <img src="/HomeToGo/resources/images/customer.jpg" alt="Auth-ErrorImage">
        </div>

    </body>
</html>
