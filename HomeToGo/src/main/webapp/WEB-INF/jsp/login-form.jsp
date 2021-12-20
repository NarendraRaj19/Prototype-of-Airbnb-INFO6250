<%-- 
    Document   : login-form
    Created on : Dec 14, 2021, 11:04:11 AM
    Author     : naren
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <!--        document.getElementById("authToken").value= $('#elementID').val();-->
        <style>
            body{
                background-color: "#FFFAF0";
            }
            .login {
                font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
                border-collapse: collapse;
            }

            .login td, .allMenu th {
                border: 3px solid #960018;
                padding: 8px;
                font-size: 20px;
            }

            /* .login tr:nth-child(even){background-color: #f4efed;} */

            .login tr:hover {background-color: rgb(30, 123, 166);}

            .login th {
                padding-top: 12px;
                padding-bottom: 12px;
                text-align: center;
                background-color: #E4D9CD;
                color: #8b0000;
            }

            .errors {
                color: red;
                font-size: 18px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header_1.jsp"></jsp:include>
            <div align="center">
                <br>
                <br>
                <h2>Please enter your details below to login!</h2>
                <br>
                <form action="${contextPath}login.htm" method="post">
                <input type="hidden" name="authToken" id="authToken" value="">
                <table class="login">
                    <tr>
                        <td>Username:</td>
                        <td><input name="username" size="30" required="required" /></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type="password" name="password" size="30"
                                   required="required" /></td>
                    </tr>

                </table>
                <br/>
                <input class="btn btn-primary"id="submitButton" type="submit" value="Login"/><br>
            </form>
        </div>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
