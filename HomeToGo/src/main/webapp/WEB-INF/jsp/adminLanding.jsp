<%-- 
    Document   : test3.jsp
    Created on : Dec 14, 2021, 3:35:48 PM
    Author     : naren
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet"
              href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <title>JSP Page</title>
        <style>
            .container{
                text-align: center;
            }
            body{
                background-color: #F08080;
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
            <a href="http://localhost:8081/HomeToGo/admin-manage.htm"> <button type="button" class="btn btn-warning">Approve or Deny Properties</button></a><br><br> 
            <img src="/HomeToGo/resources/images/admin.jpg" alt="Auth-ErrorImage">
        </div>
    </body>
</html>
