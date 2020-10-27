<%@include file="/WEB-INF/jspf/head.jsp" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<link rel="stylesheet" href="css/main.css" xmlns:c="http://www.w3.org/1999/XSL/Transform">

<html>
<head>
    <title>Order</title>
</head>
<body>
<c:set var = "order" value = "${order}"/>
<c:set var = "user" value = "${user}"/>
<c:set var = "book" value = "${book}"/>

<div class="container">
        <div class="form-group row">
            <label for="bookName" class="col-sm-2 col-form-label">Book</label>
            <div class="col-sm-10">
                <input type="text" readonly class="form-control-plaintext" id="bookName" value="${book.name}">
            </div>
        </div>
        <div class="form-group row">
            <label for="userName" class="col-sm-2 col-form-label">Name</label>
            <div class="col-sm-10">
                <input type="text" readonly class="form-control-plaintext" id="userName" value="${user.firstName} ${user.lastName}">
            </div>
        </div>
        <div class="form-group row">
            <label for="orderStatus" class="col-sm-2 col-form-label">Status</label>
            <div class="col-sm-10">
                <input type="text" readonly class="form-control-plaintext" id="orderStatus" value="${statusMap.get(order.statusId)}">
            </div>
        </div>
</div>

</body>
</html>