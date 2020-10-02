<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Library Registration</title>
</head>
<body>
<h2>Create account</h2> <br />

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
</ul>

<form method="post" action="/manager">
    <h5>Login</h5>
    <label><input type="text" name="login"></label>
    <h5>Password</h5>
    <label><input type="text" name="password"></label>
    <h5>First name</h5>
    <label><input type="text" name="first_name"></label>
    <h5>Last name</h5>
    <label><input type="text" name="last_name"></label>
    <h5>Email</h5>
    <label><input type="text" name="email"></label>
    <h5>Telephone</h5>
    <label><input type="text" name="telephone"></label>
    <br />
    <input type="submit" value="updateUser" name="command" /><br />
</form>

<br />

<form action="/">
    <br>
    <button type="submit">Back to main menu</button>
</form>

</body>
</html>