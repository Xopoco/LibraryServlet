<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jspf/boot.jsp" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Library</title>
</head>
<body>
<!--Шапка-->
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
                    <a href="/"  class="nav-link active">Main</a>
                    </form>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link">About us</a>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link">Services</a>
                </li>
                <li class="nav-item">
                    <a href="jsp/register.jsp" class="nav-link">Command</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<h2>Hello</h2>

<form action="manager">
    <br>
    <button type="submit" name="command" value="show">Show all users</button>
</form>
<br />
<form action="jsp/register.jsp">
    <button type="submit" >Registration</button>
</form>

</body>
</html>
