<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jspf/head.jsp" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Books</title>
</head>
<body>

<c:forEach var="book" items="${requestScope.books}">
    <ul>
        <div class="card" style="width: 50%;">
            <h5 class="card-header">
                <c:out value="${book.name}" />
            </h5>
            <div class="card-body">
                <h5 class="card-title"><c:out value="${book.author}" /></h5>
                <p class="card-text"><c:out value="${book.review}" /></p>
                <a href="#" class="btn btn-info">Order</a>
                <footer class="blockquote-footer">Price by day: <cite title="Price"><c:out value="${book.price}" /></cite></footer>
            </div>
        </div>
    </ul>
    <hr />
</c:forEach>

</body>
</html>
