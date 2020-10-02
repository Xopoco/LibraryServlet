<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jspf/boot.jsp" %>
<html>
<head>
    <title>Library Registration</title>
</head>
<body>

<nav class="navbar navbar-expand-md navbar-light bg-light sticky-top">
    <div class="container-fluid">
        <a href="#" class="navbar-brand">
            <i class="fas fa-archway"></i> Library
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <form action="manager">
                        <a href="/"  class="nav-link">Main</a>
                    </form>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link">About us</a>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link">Services</a>
                </li>
                <li class="nav-item">
                    <a href="jsp/register.jsp" class="nav-link  active">Command</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container" style="background-color: #999">
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
        <input type="submit" value="register" name="command" /><br />
    </form>

    <br />
    <form action="/">
        <br>
        <button type="submit">Back to main menu</button>
    </form>
</div>

</body>
</html>