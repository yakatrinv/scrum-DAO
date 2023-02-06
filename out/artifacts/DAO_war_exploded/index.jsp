<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Index page</title>
</head>
<body>
<c:redirect url="/dispatcher">
    <c:param name="command" value="read"/>
</c:redirect>
</body>
</html>
