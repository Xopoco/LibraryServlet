<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/jspf/head.jsp" %>
<link rel="stylesheet" href="css/main.css">

<html xmlns:c="http://www.w3.org/1999/html">

<head>
    <title>Manager page</title>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.min.css">
    <script type="text/javascript" language="javascript" src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>

    <script type="text/javascript" class="init">

		$(document).ready(function() {
			$('#usersTable').DataTable();
		} );

		$(document).ready(function() {
			$('#usersWithOrdersTable').DataTable();
		} );

		$(document).ready(function() {
			$('#ordersTable').DataTable();
		} );

		$(document).ready(function() {
			$('#overdueOrdersTable').DataTable();
		} );

		$(document).ready(function() {
			$('#booksTable').DataTable();
		} );

		$(document).ready(function() {
			$('#readingRoomTable').DataTable();
		} );

		$(document).ready(function() {
			$('#outBooksTable').DataTable();
		} );


    </script>

</head>
<body>

<div class="accordion" id="accordionExample">

<!--    Orders-->
    <div class="card">
        <div class="card-header" id="headingThree">
            <h5 class="mb-0">
                <button class="btn btn-info btn-lg collapsed" type="button" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                    Orders
                </button>
            </h5>
        </div>
        <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordionExample">
            <div class="card-body">
                <p>
                    <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#allOrders" aria-expanded="false" aria-controls="allOrders">All orders</button>
                    <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#outOfTimeOrders" aria-expanded="false" aria-controls="outOfTimeOrders">Overdue orders</button>
                    <button type="button" class="btn btn-light btn-sm" data-toggle="modal" data-target="#newOrderModal">New order</button>
                </p>
                <div class="container">
<!--All orders-->
                    <div class="collapse multi-collapse" id="allOrders">
                        <div class="card card-body">
                            <table id="ordersTable" class="table table-striped">
                                <thead>
                                <tr>
                                    <th scope="col">User</th>
                                    <th scope="col">Book</th>
                                    <th scope="col">When</th>
                                    <th scope="col">How long</th>
                                    <th scope="col">Status</th>
                                    <th scope="col">Status id</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="order" items="${requestScope.orders}">
                                    <tr>
                                        <th scope="row"><c:out value="${userMap.get(order.userId).firstName} ${userMap.get(order.userId).lastName}" /></th>
                                        <td><c:out value="${bookMap.get(order.bookId).name}" /></td>
                                        <td><c:out value="${order.date}" /></td>
                                        <td><c:out value="${order.dayCount}" /></td>
                                        <td><c:out value="${statusMap.get(order.statusId).value}" /></td>
                                        <td><c:out value="${statusMap.get(order.statusId).id}" /></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
<!--Overdue orders-->
                    <div class="collapse multi-collapse" id="outOfTimeOrders">
                        <div class="card card-body">
                            <table id="overdueOrdersTable" class="table table-striped">
                                <thead>
                                <tr>
                                    <th scope="col">User</th>
                                    <th scope="col">Book</th>
                                    <th scope="col">Date</th>
                                    <th scope="col">Debt</th>
                                    <th scope="col">Status</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="order" items="${requestScope.orders}">
                                    <c:if test="${order.debt gt 0}">
                                        <tr>
                                            <th scope="row"><c:out value="${userMap.get(order.userId).firstName} ${userMap.get(order.userId).lastName}" /></th>
                                            <td><c:out value="${bookMap.get(order.bookId).name}" /></td>
                                            <td><c:out value="${order.date}" /></td>
                                            <td><c:out value="${order.debt}" /></td>
                                            <td><c:out value="${statusMap.get(order.statusId).value}" /></td>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

<!--    Books-->
    <div class="card">
        <div class="card-header" id="headingTwo">
            <h5 class="mb-0">
                <button class="btn btn-info btn-lg collapsed" type="button" data-toggle="collapse" data-target="#bookCollapse" aria-expanded="false" aria-controls="bookCollapse">
                    Books
                </button>
            </h5>
        </div>
        <div id="bookCollapse" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionExample">
            <div class="card-body">
                <p>
                    <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#allBooksCollapse" aria-expanded="false" aria-controls="multiCollapseExample2">All books</button>
                    <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#readingRoomBooks" aria-expanded="false" aria-controls="multiCollapseExample2">Books in reading room</button>
                    <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#outBooks" aria-expanded="false" aria-controls="multiCollapseExample2">Books out</button>
                </p>
                <div class="container">
    <!-- All books-->
                    <div class="collapse multi-collapse" id="allBooksCollapse">
                        <div class="card card-body">
                            <table id="booksTable" class="table table-striped">
                                <thead>
                                <tr>
                                    <th scope="col">Name</th>
                                    <th scope="col">Author</th>
                                    <th scope="col">Genre</th>
                                    <th scope="col">Edition</th>
                                    <th scope="col">Edition Date</th>
                                    <th scope="col">Count</th>
                                    <th scope="col">Up</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="book" items="${requestScope.books}">
                                    <tr>
                                        <th scope="row"><c:out value="${book.name}" /></th>
                                        <td><c:out value="${authors.get(book.authorId).firstName} ${authors.get(book.authorId).lastName}" /></td>
                                        <td><c:out value="${genreMap.get(book.genreId).name}" /></td>
                                        <td><c:out value="${book.edition}" /></td>
                                        <td><c:out value="${book.dateOfEdition}" /></td>
                                        <td><c:out value="${book.count}" /></td>
                                        <td>
                                            <form>
                                                <input type="hidden" name="userId" value="${user.id}">
                                                <button class="btn btn-danger" type="submit" name="command" value="updateBook">Update user</button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
    <!-- Books in reading room-->
                    <div class="collapse multi-collapse" id="readingRoomBooks">
                        <div class="card card-body">
                            <table id="readingRoomTable" class="table table-striped">
                                <thead>
                                <tr>
                                    <th scope="col">Name</th>
                                    <th scope="col">Book</th>
                                    <th scope="col">Order date</th>
                                    <th scope="col">Status</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="order" items="${requestScope.orders}">
                                    <c:if test="${order.statusId eq 2}">
                                        <tr>
                                            <th scope="row"><c:out value="${userMap.get(order.userId).firstName} ${userMap.get(order.userId).lastName}" /></th>
                                            <td><c:out value="${bookMap.get(order.bookId).name}" /></td>
                                            <td><c:out value="${order.date}" /></td>
                                            <td><c:out value="${statusMap.get(order.statusId).value}" /></td>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
    <!--Books out of library-->
                    <div class="collapse multi-collapse" id="outBooks">
                        <div class="card card-body">
                            <table id="outBooksTable" class="table table-striped">
                                <thead>
                                <tr>
                                    <th scope="col">Name</th>
                                    <th scope="col">Book</th>
                                    <th scope="col">Order date</th>
                                    <th scope="col">Day count</th>
                                    <th scope="col">Up</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="order" items="${requestScope.orders}">
                                    <c:if test="${order.statusId eq 3}">
                                        <tr>
                                            <th scope="row"><c:out value="${userMap.get(order.userId).firstName} ${userMap.get(order.userId).lastName}" /></th>
                                            <td><c:out value="${bookMap.get(order.bookId).name}" /></td>
                                            <td><c:out value="${order.date}" /></td>
                                            <td><c:out value="${statusMap.get(order.statusId).value}" /></td>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

<!--    Users-->
    <div class="card">
        <div class="card-header" id="headingOne">
            <h5 class="mb-0">
                <button class="btn btn-info btn-lg collapsed" type="button" data-toggle="collapse" data-target="#userCollapse" aria-expanded="false" aria-controls="userCollapse">
                    Users
                </button>
            </h5>
        </div>

        <div id="userCollapse" class="collapse" aria-labelledby="headingOne" data-parent="#accordionExample">
            <div class="card-body">
                <p>
                    <a class="btn btn-primary collapsed" data-toggle="collapse" href="#allUsersCollapse" role="button" aria-expanded="false" aria-controls="allUsersCollapse">All users</a>
                    <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#orderUsers" aria-expanded="false" aria-controls="orderUsers">Users with orders</button>
                </p>
                <div class="container">
<!-- All users-->
                    <div class="collapse multi-collapse" id="allUsersCollapse">
                        <div class="card card-body">
                            <table id="usersTable" class="table table-striped">
                                <thead>
                                <tr>
                                    <th scope="col">Name</th>
                                    <th scope="col">Login</th>
                                    <th scope="col">Email</th>
                                    <th scope="col">Phone</th>
                                    <th scope="col">Block</th>
                                    <th scope="col">Up</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="user" items="${requestScope.users}">
                                            <tr>
                                                <th scope="row"><c:out value="${user.firstName}" /> <c:out value="${user.lastName}" /></th>
                                                <td><c:out value="${user.login}" /></td>
                                                <td><c:out value="${user.email}" /></td>
                                                <td><c:out value="${user.telephone}" /></td>
                                                <td>
                                                    <c:if test="${(user.blockStatus eq 0)}">
                                                        ok
                                                    </c:if>
                                                    <c:if test="${(user.blockStatus ne 0)}">
                                                        blocked
                                                    </c:if>
                                                </td>
                                                <td>
                                                    <form>
                                                        <input type="hidden" name="userId" value="${user.id}">
                                                        <button class="btn btn-danger" type="submit" name="command" value="showUpdateUser">Update user</button>
                                                    </form>
                                                </td>
                                            </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<!-- New order -->
<div class="modal fade" id="newOrderModal" tabindex="-1" role="dialog" aria-labelledby="modalCenterTitle" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modalLongTitle">New order</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">x</span>
                </button>
            </div>
            <div class="modal-body">

                <form class="needs-validation" novalidate method="post" action="${pageContext.request.contextPath}/manager">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Login" name="login" required minlength="3">
                        <div class="valid-feedback">
                            Looks good!
                        </div>
                        <div class="invalid-feedback">
                            Please choose a login.
                        </div>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" placeholder="Password" name="password" aria-describedby="passwordHelpBlock" required>
                        <small id="passwordHelpBlock" class="form-text text-muted">
                            Your password must be 8-20 characters long, contain letters and numbers, and must not contain spaces, special characters, or emoji.
                        </small>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="First name" name="first_name" required minlength="3">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Last name" name="last_name" required minlength="3" title="Minimum 3 signs">
                    </div>
                    <div class="form-group">
                        <input type="email" class="form-control" aria-describedby="emailHelp" placeholder="Enter email" name="email" required
                               pattern="[^@\s]+@[^@\s]+\.[^@\s]+" title="John..Doe@example.com" />
                        <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                    </div>
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text">+38</span>
                        </div>
                        <input type="tel" class="form-control" id="basic-url" placeholder="Telephone" name="telephone"
                               pattern="[0-9]{10}" maxlength="10" title="Ten digits code" required>
                    </div>

                    <div class="modal-footer">
                        <div class="form-check">
                            <input type="checkbox" class="form-check-input" id="Check">
                            <label class="form-check-label" for="Check">Some conditions</label>
                            <div class="invalid-feedback">
                                You must agree before submitting.
                            </div>
                        </div>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-warning" id="regisButton" name="command" value="register" disabled="true">Register</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>