<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>People</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<c:choose>
    <c:when test="${requestScope.people.size() > 0}">
        <table>
            <tr>
                <th>&#8470;</th>
                <th>Name</th>
                <th>Surname</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach var="elem" items="${requestScope.people}" varStatus="status">
                <tr>
                    <td>${status.count}</td>
                    <td>${elem.name}</td>
                    <td>${elem.surname}</td>
                    <td>
                        <form method="get" action="${pageContext.request.contextPath}/dispatcher">
                            <input type="hidden" name="command" value="update">
                            <input type="hidden" name="personId" value=${elem.id}>
                            <button type="submit">Edit</button>
                        </form>
                    </td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/dispatcher">
                            <input type="hidden" name="command" value="delete">
                            <input type="hidden" name="personId" value=${elem.id}>
                            <button type="submit">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <div class="emptyList">
            <h1>No people added yet!</h1>
        </div>
    </c:otherwise>
</c:choose>
<form class="addForm" method="get" action="${pageContext.request.contextPath}/dispatcher">
    <input type="hidden" name="command" value="create">
    <button class="red" type="submit"><i class="icon ion-md-lock"></i>Add new person</button>
</form>
</body>
</html>
