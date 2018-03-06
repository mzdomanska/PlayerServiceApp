<%--
  Created by IntelliJ IDEA.
  User: magda
  Date: 01.02.18
  Time: 18:38
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Players</title>
</head>
<body>
    <table>
        <tr>
            <th>Player</th>
        </tr>
        <c:forEach items="${Players}" var="player">
            <tr>
                <td>${player}</td>
            </tr>
        </c:forEach>

    </table>
</body>
</html>
