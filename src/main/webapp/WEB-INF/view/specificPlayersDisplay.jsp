<%--
  Created by IntelliJ IDEA.
  User: magda
  Date: 08.02.18
  Time: 19:57
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chosen Players</title>
</head>
<body>
    <h4>Selected playerEntities</h4>
    <table>
        <tr>
            <th><spring:message code="playerEntities.firstName"/></th>
            <th><spring:message code="playerEntities.lastName"/></th>
        </tr>
        <c:forEach items="${playerEntities}" var="player">
            <tr>
                <td>${player.firstName}</td> <td>${player.lastName}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
