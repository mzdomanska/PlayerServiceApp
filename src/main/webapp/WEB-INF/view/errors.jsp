<%--
  Created by IntelliJ IDEA.
  User: magda
  Date: 08.02.18
  Time: 20:20
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ERRORS</title>
    <style>
        #exception {
            width:500px;
            height:50px;
            color: black;
            background-color:gainsboro;
        }
        #message {
            width: 500px;
            height: 50px;
            color: black;
            background-color:lightsteelblue;
        }
    </style>
</head>
<body>
    <h4><spring:message code="error.header"/></h4>
    <div id="exception">
        <p>Exception: ${exception}</p>
    </div>
    <div id="message">
        <p>Message: ${message}</p></p>
    </div>
</body>
</html>
