<%@include file="/WEB-INF/jspf/head.jsp" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<link rel="stylesheet" href="css/main.css">

<html>
<head>
    <title>User update</title>
</head>
<body>
<c:set var = "user" value = "${user}"/>
<c:out value="${user.login}" />
<c:out value="${user.id}" />

<form method="post" action="${pageContext.request.contextPath}/manager">
    <h5>Login</h5>
    <label><input value="${user.login}" type="text" name="login"></label> <br>
    <h5>First name</h5>
    <label><input value="${user.firstName}" type="text" name="first_name"></label>
    <h5>Last name</h5>
    <label><input value="${user.lastName}" type="text" name="last_name"></label>
    <h5>Email</h5>
    <label><input value="${user.email}" type="text" name="email"></label>
    <h5>Telephone</h5>
    <label><input value="${user.telephone}" type="text" name="telephone"></label>
    <br>
    <input hidden name="userId" value="${user.id}">
    <button type="submit" name="command" value="updateUser">Update</button>
</form>

<br />

<form action="${pageContext.request.contextPath}/">
    <br>
    <button type="submit">Back to main menu</button>
</form>


<%@include file="/WEB-INF/jspf/footer.jsp" %>
</body>
</html>