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
                <button class="btn btn-info collapsed" type="button" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                    Orders
                </button>
            </h5>
        </div>
        <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordionExample">
            <div class="card-body">
                <p>
                    <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#allOrders" aria-expanded="false" aria-controls="allOrders">All orders</button>
                    <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#outOfTimeOrders" aria-expanded="false" aria-controls="outOfTimeOrders">Overdue orders</button>
                </p>
                <div class="container">
<!--All orders-->
                    <div class="collapse multi-collapse" id="allOrders">
                        <div class="card card-body">
                            <table id="ordersTable" class="table table-striped">
                                <thead>
                                <tr>
                                    <th scope="col">user_id</th>
                                    <th scope="col">book_id</th>
                                    <th scope="col">date</th>
                                    <th scope="col">debt</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="order" items="${requestScope.orders}">
                                    <tr>
                                        <th scope="row"><c:out value="${order.userId}" /></th>
                                        <td><c:out value="${order.bookId}" /></td>
                                        <td><c:out value="${order.date}" /></td>
                                        <td><c:out value="${order.debt}" /></td>
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
                                    <th scope="col">user_id</th>
                                    <th scope="col">book_id</th>
                                    <th scope="col">date</th>
                                    <th scope="col">debt</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="order" items="${requestScope.outOfTimeOrders}">
                                    <tr>
                                        <th scope="row"><c:out value="${order.userId}" /></th>
                                        <td><c:out value="${order.bookId}" /></td>
                                        <td><c:out value="${order.date}" /></td>
                                        <td><c:out value="${order.debt}" /></td>
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

<!--    Books-->
    <div class="card">
        <div class="card-header" id="headingTwo">
            <h5 class="mb-0">
                <button class="btn btn-info collapsed" type="button" data-toggle="collapse" data-target="#bookCollapse" aria-expanded="false" aria-controls="bookCollapse">
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
                                        <td><c:out value="${book.author}" /></td>
                                        <td><c:out value="${book.genre}" /></td>
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
                                    <th scope="col">Day count</th>
                                    <th scope="col">Up</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="entry" items="${orderUserMap}">
                                    <tr>
                                        <th scope="row"><c:out value="${entry.value.firstName}" /> <c:out value="${entry.value.lastName}" /></th>
                                        <td><c:out value="${orderBookMap.get(entry.key).name}" /></td>
                                        <td><c:out value="${entry.key.date}" /></td>
                                        <td><c:out value="${entry.key.dayCount}" /></td>
                                        <td>
                                            <form>
                                                <input type="hidden" name="userId" value="${entry.value.id}">
                                                <button type="submit" name="command" value="showUpdateUser">Update user</button>
                                            </form>
                                        </td>
                                    </tr>

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
                                <c:forEach var="entry" items="${orderBookMap}">
                                    <tr>
                                        <th scope="row"><c:out value="${entry.value.name}" /></th>
                                        <td><c:out value="${entry.value.author}" /></td>
                                        <td><c:out value="${entry.key.date}" /></td>
                                        <td><c:out value="${entry.key.dayCount}" /></td>
                                        <td>
                                            <form>
                                                <input type="hidden" name="userId" value="${entry.value.id}">
                                                <button type="submit" name="command" value="showUpdateUser">Update user</button>
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

<!--    Users-->
    <div class="card">
        <div class="card-header" id="headingOne">
            <h5 class="mb-0">
                <button class="btn btn-info collapsed" type="button" data-toggle="collapse" data-target="#userCollapse" aria-expanded="false" aria-controls="collapseOne">
                    Users
                </button>
            </h5>
        </div>

        <div id="userCollapse" class="collapse show" aria-labelledby="headingOne" data-parent="#accordionExample">
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
<!-- Users with orders-->
                    <div class="collapse multi-collapse" id="orderUsers">
                        <div class="card card-body">
                            <table id="usersWithOrdersTable" class="table table-striped">
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
                                <c:forEach var="entry" items="${orderUserMap}">
                                    <tr>
                                        <th scope="row"><c:out value="${entry.value.firstName}" /> <c:out value="${entry.value.lastName}" /></th>
                                        <td><c:out value="${orderBookMap.get(entry.key).name}" /></td>
                                        <td><c:out value="${entry.key.date}" /></td>
                                        <td><c:out value="${entry.key.dayCount}" /></td>
                                        <td>
                                            <form>
                                                <input type="hidden" name="userId" value="${entry.value.id}">
                                                <button type="submit" name="command" value="showUpdateUser">Update user</button>
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

</body>
</html>