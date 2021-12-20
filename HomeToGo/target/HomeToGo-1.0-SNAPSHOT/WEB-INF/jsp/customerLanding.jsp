<%-- 
    Document   : test1
    Created on : Dec 14, 2021, 3:01:06 PM
    Author     : naren
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <style>

            body {
                margin: 0;
                padding: 0;
            }
            
            .header {
                height: 100vh;
                background-image: linear-gradient(rgba(0,0,0,0.5),rgba(0,0,0,0.5),url(/HomeToGo/resources/images/test.jpg));
                background-size: cover;
                background-position: center;
                display: flex;
                justify-content: center;
                align-items: center;
                font-family: sans-serif;
                    
            }
            h1 {
                color: #fff;
                margin-bottom: 70px;
                font-size: 45px;
                letter-spacing: 2px;
                text-align: center;
            }
            
            .search-field {
                height: 50px;
                padding: 10px;
                border: none;
                border-radius: 25px;
                outline: none;
            }
            
            .business {
                width: 400px;
            }
            
            .location {
                width: 200px;
            }
            
            .search-btn {
                height: 50px;
                width: 150px;
                background: #ffeb3b;
                border: none;
                color: #000;
                border-radius: 25px;
            }
            
            .search-btn:hover {
                background: #ffc107;
                cursor: pointer;
            }
            
            .form-box{
                padding: 30px;
                background: rgba(0,0,0,0.5);
            }
/*            value="/resources/css/style.css"/>">*/
        </style>
    </head>
    <body>
        <jsp:include page="loginHeaderSignOut_1.jsp"></jsp:include>
        <div class="header">
            <form action="<c:url value='/user/search.htm'/>" method="post">
                <h1>Find Homes Near You</h1>
                <div class="form-box">

                    <strong>Which City you are looking At ?:</strong>
                    <select name="propertyCity" id="propertyCity">
                        <c:forEach var="j" items="${properties}">
                            <option value="${j}">${j}</option>
                        </c:forEach>
                    </select>

                    <strong>What Type of Property ?:</strong>
                    <select name="propertyType" id="propertyType">
                        <option value="Villa">Villa</option>
                        <option value="Private Room">Private Room</option>
                        <option value="Duplex">Duplex</option>
                        <option value="Shared Room">Shared Room</option>
                    </select>
                    <button class="search-btn" type="submit">Search</button>
                </div>
            </form>
        </div>
        
    </body>
    <script>
        
    </script>
</html>
