<%-- 
    Document   : ownerUpdateProperty
    Created on : Dec 15, 2021, 6:01:40 PM
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
        <c:set var="PropertyInfo" value="${property}" />
        <c:set var="pid" value="${pid}" />
        <div align="center"> <h3>Here you can update the following Property's Details</h3></div>

        <div align="center">
            <br />
            <br>
            <form action="#" method="post">
                <input type="hidden" name="authToken" id="authToken" value="">
                <table class="login">
                    <tr>
                        <td>Property Address*:</td>
                        <td>
                            <input name="propertyAddress" size="50" value="${PropertyInfo.propertyAddress}" required="required" /></td>
                    </tr>
                    <tr>
                        <td>Property Type*:</td>
                        <td><select name="propertyType" id="propertyType">
                                <option value="Villa">Villa</option>
                                <option value="Private Room">Private Room</option>
                                <option value="Duplex">Duplex</option>
                                <option value="Shared Room">Shared Room</option>
                            </select></td>
                    </tr>
                    <tr>
                        <td>Property Cost*:</td>
                        <td><input type="text" name="propertyCost" size="20" value="${PropertyInfo.propertyCost}" required="required" /></td>
                    </tr>

                </table>
                <br/>
                <br>
                <center><input class="btn btn-primary"id="submitButton" type="submit" value="Update"/></center>
            </form>
        </div>
        <br>
        <br>
    </body>
</html>
