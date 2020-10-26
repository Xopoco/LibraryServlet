<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jspf/head.jsp" %>
<link rel="stylesheet" href="css/main.css">

<html>
<head>
    <title>Books</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <script type="text/javascript" language="javascript" src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" class="init">

        $(document).ready(function() {
			$('#booksTable').DataTable();
		} );

    </script>
</head>
<body>

<div class="container">
    <div class="row">
<!--All books-->
    <table id="booksTable" class="table">
        <thead>
        <tr>
                <th scope="col">Book</th>
                <th scope="col">Author</th>
                <th scope="col">Review</th>
                <th scope="col"></th>
                <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="book" items="${requestScope.books}">
            <tr>
                <th scope="row"><c:out value="${book.name}" /></th>
                <td><c:out value="${authors.get(book.authorId).firstName} ${authors.get(book.authorId).lastName} (${authors.get(book.authorId).dateOfBirth})" /></td>
                <td><c:out value="${book.review}" /></td>
                <td>
                    <c:if test="${(userId gt 0)}">
                        <form action="${pageContext.request.contextPath}/manager">
                            <input type="hidden" name="bookId" value="${book.id}">
                            <button id="orderButton" type="submit" class="btn btn-warning" name="command" value="orderBook">Order</button>
                        </form>
                    </c:if>
                    <c:if test="${(userId eq null)}">
                        <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#notRegisteredUserModal">Order</button>
                    </c:if>
                </td>
                <td><img src="${book.picture}" class="img-fluids" alt="some description"></td>
            </tr>

        </c:forEach>
        </tbody>
    </table>
    </div>
</div>

<div class="modal fade" id="notRegisteredUserModal" tabindex="-1" role="dialog" aria-labelledby="modalCenterTitle" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" >You need to sign-in to order the book.</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">x</span>
                </button>
            </div>
        </div>
    </div>
</div>

</body>
</html>
