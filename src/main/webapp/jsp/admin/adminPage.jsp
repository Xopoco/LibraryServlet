<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/jspf/head.jsp" %>
<link rel="stylesheet" href="css/main.css">

<html xmlns:c="http://www.w3.org/1999/html">
<head>
  <title>Admin page</title>
</head>
<body>

  <h2>Hello Some!</h2> <br />
  <h2>Все пользователи</h2> <br />

  <c:forEach var="user" items="${requestScope.users}">
    <ul>
      <li>Login: <c:out value="${user.login}" />
      <li>Password: <c:out value="${user.password}" />
      <li>Name: <c:out value="${user.firstName}" /> <c:out value="${user.lastName}" />
      <li>Email: <c:out value="${user.email}" />
      <li>Telephone: <c:out value="${user.telephone}" />
      <li>RoleId: <c:out value="${user.id}" />
      <br/>
<!--      remove-->
      <form action="${pageContext.request.contextPath}/manager">
        <input type="number" hidden name="id" value="${user.id}" />
        <button type="submit" name="command" value="removeUser">Remove user</button>
      </form>
<!--      update-->
      <form action="${pageContext.request.contextPath}/manager">
        <input type="text" hidden name="userId" value="${user.id}" />
        <button type="submit" name="command" value="showUpdateUser">Update user</button>
      </form>
    </ul>
    <hr />
  </c:forEach>

  <br />

</body>
</html>