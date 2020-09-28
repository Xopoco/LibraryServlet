<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jspf/boot.jsp" %>
<html>
<head>
    <title>Library Registration</title>
</head>
<body>
<div class="container" style="background-color: #99CCFF">
    <h2>Create account</h2> <br />
    <form method="post" action="/manager">
        <h4>Login</h4>
        <label><input type="text" name="login"></label>
        <h4>Password</h4>
        <label><input type="text" name="password"></label>
        <h4>First name</h4>
        <label><input type="text" name="first_name"></label>
        <h4>Last name</h4>
        <label><input type="text" name="last_name"></label>
        <h4>Email</h4>
        <label><input type="text" name="email"></label>
        <h4>Telephone</h4>
        <label><input type="text" name="telephone"></label>
        <br />
        <input type="submit" value="register" name="action" /><br />
    </form>

    <br />
    <form action="/">
        <br>
        <button type="submit">Back to main menu</button>
    </form>
</div>

</body>
</html>