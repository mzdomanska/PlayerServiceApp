<%--
  Created by IntelliJ IDEA.
  User: magda
  Date: 12.02.18
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Player</title>
</head>
<body>
    <form:form method="post" action="/edit" modelAttribute="player">

        <spring:message code="players.firstName"/> : <input type="text" value="${playerToEdit.firstName}" name="firstName">
        <spring:message code="players.lastName"/> : <input type="text" value="${playerToEdit.lastName}" name="lastName">
        <input type="submit" value="EDIT" name="edit">
        <input type="hidden" value="${playerToEdit.id}" name="playerToEdit">
    </form:form>
</body>
</html>
