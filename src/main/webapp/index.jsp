<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jspf/boot.jsp" %>

<html>
<head>
    <title>Main</title>
</head>
<body>

<h2>Hello</h2>

<form action="manager">
    <br>
    <button type="submit" name="action" value="show">Show all users</button>
</form>
<br />
<form action="jsp/register.jsp">
    <button type="submit" >Registration</button>
</form>

</body>
</html>
