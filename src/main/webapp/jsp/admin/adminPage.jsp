<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/jspf/head.jspf" %>
<%@include file="/WEB-INF/jspf/boot.jsp" %>

<html>
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
      <br/>
      <form action="manager">
        <input type="number" hidden name="id" value="${user.id}" />
        <button type="submit" name="command" value="removeUser">Remove user</button>
      </form>

      <form action="jsp/updateUser.jsp">
        <input type="number" hidden name="user" value="${user}" />
        <button type="submit" name="command" value="updateUser">Update user</button>
      </form>
    </ul>
    <hr />
  </c:forEach>

  <br />

  <form action="/">
    <button type="submit">Back to main menu</button>
  </form>

</body>
</html>