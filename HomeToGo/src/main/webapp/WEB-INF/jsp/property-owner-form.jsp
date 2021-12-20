<%-- 
    Document   : property-owner-form
    Created on : Dec 13, 2021, 11:26:23 AM
    Author     : naren
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            label {	float: left;
                    width: 150px;}
            #container{
                width: 500px;
                height: 550px;
                margin: auto;
                background-color: beige;
            } 
            main{
                padding: 50px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <div id="container">

                <main>
                    <h2>User Registration Form</h2>
                    Note : Fields marked with an asterisk(*) are mandatory
                    <br><br>
                    <form:form modelAttribute="property">

                        <hr/><br>
                        Email ID*: <form:input path="username" /><br><br>
                        <form:errors path="username" /><br>
                        Password*: <form:input path="password" /><br><br>
                        <form:errors path="password" /><br>
                        Property Name*: <form:input path="propertyName" /><br><br>
                        <form:errors path="phoneNo" /><br>
                        Property Name*: <form:input path="propertyName" /><br><br>
                        Property Address*: <form:input path="propertyAddress" /><br><br>
                        Property State*: <form:input path="propertyState" /><br><br>
                        Property Zip Code*: <form:input path="propertZipCode" /><br><br>
                        Property Type*: <form:input path="propertType" /><br><br>

                        <input type="submit" value="Register"/>

                    </form:form>
                </main>
            </div>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
