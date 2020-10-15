<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jspf/head.jsp" %>
<link rel="stylesheet" href="css/main.css">

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
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-3 col-lg-3 col-xl-2">
                <img src="${book.picture}" class="img-fluid">
            </div>
            <div class="col-xs-12 col-sm-12 col-md-9 col-lg-9 col-xl-10">
                <div class="card bg-light mb-3 w-50 text-center">
                    <h5 class="card-header">
                        <c:out value="${book.name}" />
                    </h5>
                    <div class="card-body">
                        <h5 class="card-title"><c:out value="${authors.get(book.authorId).firstName} ${authors.get(book.authorId).lastName} (${authors.get(book.authorId).dateOfBirth})" /></h5>
                        <p class="card-text"><c:out value="${book.review}" /></p>
                        <c:if test="${(userId gt 0)}">
                            <a href="#" class="btn btn-info">Order</a>
                        </c:if>
                        <footer class="blockquote-footer">Price by day: <cite title="Price"><c:out value="${book.price}" /></cite></footer>
                    </div>
                </div>
            </div>
        </div>
    </ul>
    <hr />
</c:forEach>


<%@include file="/WEB-INF/jspf/footer.jsp" %>
</body>
</html>
