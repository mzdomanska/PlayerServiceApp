<%--
  Created by IntelliJ IDEA.
  User: magda
  Date: 06.02.18
  Time: 19:00
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Players</title>
</head>
<body>
    <h4>List of players</h4>
    <form:form method="post" action="/players" modelAttribute="player">
        <table>
            <tr>
                <th><spring:message code="players.firstName"/></th>
                <th><spring:message code="players.lastName"/></th>
                <th><spring:message code="players.delete" /></th>
                <th><spring:message code="players.edit"/></th>
            </tr>
            <c:forEach items="${players}" var="player">
                <tr>
                    <td>${player.firstName}</td> <td>${player.lastName}</td>

                    <form:form method="post" action="/players" modelAttribute="player">
                        <td><input type="submit" value="EDIT" name="action"></td>
                        <td><input type="hidden" value="${player.id}" name="playerToEdit"></td>

                        <td><input type="submit" value="DELETE" name="action"></td>
                        <td><input type="hidden" value="${player.id}" name="playerToDelete"></td>
                    </form:form>

                </tr>
            </c:forEach>
        </table>

        <h4>Add new player</h4>
        <table>
            <tr>
                <th><spring:message code="players.firstName"/></th>
                <th><spring:message code="players.lastName"/></th>
                <th><spring:message code="players.add" /></th>
            </tr>
            <tr>
                <td><form:input path="firstName" type="text"/></td>
                <td><form:input path="lastName" type="text"/></td>
                <td><button type="submit" value="ADD" name="action">Add</button></td>
            </tr>
        </table>
        <h4>ERRORS</h4>
        <form:errors path="firstName" />
        <form:errors path="lastName" />
        <h4>RECENT ACTIONS</h4>
        <c:if test="${playerSession.counter > 0}">
            <p>You have added: ${playerSession.counter} players and the last one is ${playerSession.recentylAddedPlayer} </p>
        </c:if>
    </form:form>
</body>
</html>
