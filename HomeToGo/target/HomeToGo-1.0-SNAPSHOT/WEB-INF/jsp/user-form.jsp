<%-- 
    Document   : user-form.jsp
    Created on : Dec 12, 2021, 10:12:53 AM
    Author     : naren
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>User-Form</title>
        <link rel="stylesheet"
              href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link
            rel="stylesheet"
            href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"

            crossorigin="anonymous"
            />
        <link rel="manifest" href="favicon/site.webmanifest">
        <style>
            /*            label {	float: left;
                                width: 150px;}
                        #container{
                            width: 500px;
                            height: 550px;
                            margin: auto;
                            background-color: beige;
                        } 
                        main{
                            padding: 50px;
                        }*/body{
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

            .login tr:hover {background-color: rgb(247, 225, 43);}

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
        <jsp:include page="header_2.jsp"></jsp:include>

            <div id="container">

                <main>
                    <div align="center"><h2>User Registration Form</h2>
                        Note : Fields marked with an asterisk(*) are mandatory
                        <br>

                    <form:form modelAttribute="user">
                        <form:errors path="*" class="errors"></form:errors>
                            <hr/><br>
                            <table class="login">
                                <tr>
                                    <td>Email ID*: </td>
                                    <td><form:input path="username" />
                                </td>
                            </tr>
                            <br>


                            <tr>
                                <td>Password*:</td>
                                <td><form:password path="password" />
                                </td>
                            </tr>

                            <tr>
                                <td>Registering For: </td>
                                <td><form:select path="userRole">
                                        <form:option value="customer" label="Customer"/>  
                                        <form:option value="propertyowner" label="Property Owner"/>   
                                    </form:select> 
                                </td>
                            </tr>
                        </table><br>
                        <center><input class="btn btn-primary" type="submit" value="Register"/></center>
                        </form:form>
                </div>
            </main>
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

        <jsp:include page="footer.jsp"></jsp:include>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script>

            $(document).ready(function () {

                // Validate User First name
                $('#errorMsgFName').hide();
                let usernameError = true;
                $('#firstName').keyup(function () {
                    validateUserFname();
                });

                function validateUserFname() {
                    let usernameValue = $('#firstName').val();
                    let fn = /^[a-zA-z]+$/;
                    if (fn.test(usernameValue)) {
                        $('#errorMsgFName').hide();
                        usernameError = true;
                        return true;
                    } else {
                        $('#errorMsgFName').show();
                        usernameError = false;
                        return false;
                    }
                }

                // Validate User Last name
                $('#errorMsgLName').hide();
                let usernameError2 = true;
                $('#lastName').keyup(function () {
                    validateUserLname();
                });

                function validateUserLname() {
                    let usernameValue = $('#lastName').val();
                    let ln = /^[a-zA-z]+$/;
                    if (ln.test(usernameValue)) {
                        $('#errorMsgLName').hide();
                        usernameError2 = true;
                        return true;
                    } else {
                        $('#errorMsgLName').show();
                        usernameError2 = false;
                        return false;
                    }
                }



                // Validate Email
                $('#errorMsgEmail').hide();
                const email =
                        document.getElementById('emailId');
                email.addEventListener('blur', () => {
                    let regex = /^([_\-\.0-9a-zA-Z]+)@([_\-\.0-9a-zA-Z]+)\.([a-zA-Z]){2,7}$/;
                    let s = email.value;
                    if (regex.test(s)) {
                        $('#errorMsgEmail').hide();
                        emailError = true;
                    } else {
                        $('#errorMsgEmail').show();
                        emailError = false;
                    }
                });


                //validate phone Number 
                var regExPhone =
                        $('#errorMsgPhone').hide();
                const phone =
                        document.getElementById('phoneNumber');
                phone.addEventListener('blur', () => {
                    let regex1 = /\d{3}-?\d{3}-\d{4}$/;
                    let ph = phone.value;
                    if (regex1.test(ph)) {
                        $('#errorMsgPhone').hide();
                        phoneError = true;
                    } else {
                        $('#errorMsgPhone').show();
                        phoneError = false;
                    }
                });


                //Validate Zip Code

                $('#errorMsgZip').hide();
                const zipCode = document.getElementById('zipcode');
                zipCode.addEventListener('blur', () => {
                    let regex2 = /(^\d{5}$)|(^\d{5}-\d{4}$)/;
                    let zp = zipCode.value;
                    if (regex2.test(zp)) {
                        $('#errorMsgZip').hide();
                        zipCodeError = true;
                    } else {
                        $('#errorMsgZip').show();
                        zipCodeError = false;
                    }
                });

                //validate comments section
                $('#errorMsgComments').hide();
                const commentsection = document.getElementById('comments');
                commentsection.addEventListener('blur', () => {
                    if (commentsection.value == '') {
                        $('#errorMsgComments').show();
                        commentsError = false;
                    } else {
                        $('#errorMsgComments').hide();
                        commentsError = true;
                    }
                });



                // Submitt button
                $('#submitID').click(function () {
                    if ((usernameError == true) &&
                            (zipCodeError == true) && (phoneError == true) &&
                            (usernameError2 == true) && (emailError == true) && (commentsError == true)) {
                        alert("You have successfully submitted your form !");
                        return true;
                    } else {
                        return false;
                    }
                });
            });

        </script>

    </body>
</html>

