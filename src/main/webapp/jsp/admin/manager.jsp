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
			$('#processingOrdersTable').DataTable();
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
                    <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#processingOrders" aria-expanded="false" aria-controls="processingOrders">Orders in processing</button>
                    <button type="button" class="btn btn-light btn-sm" data-toggle="modal" data-target="#newOrderModal">New order</button>
                </p>
                <div class="container">
<!--All orders-->
                    <div class="collapse multi-collapse" id="allOrders">
                        <div class="card card-body">
                            <table id="ordersTable" class="table table-striped table-hover">
                                <thead>
                                <tr>
                                    <th scope="col">User</th>
                                    <th scope="col">Book</th>
                                    <th scope="col">When</th>
                                    <th scope="col">How long</th>
                                    <th scope="col">Status</th>
                                    <th scope="col">Status id</th>
                                    <th scope="col"></th>
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
                                        <td>
                                            <form method="post" action="${pageContext.request.contextPath}/manager">
                                                <input hidden name="orderId" value="${order.id}">
                                                <button class="btn btn-info" id="updateOrderButton" type="submit" name="command" value="showOrder">Update</button>
                                            </form>
                                        </td>
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
<!-- Orders in processing-->
                <div class="collapse multi-collapse" id="processingOrders">
                    <div class="card card-body">
                        <table id="processingOrdersTable" class="table table-striped">
                            <thead>
                            <tr>
                                <th scope="col">User</th>
                                <th scope="col">Book</th>
                                <th scope="col">Date</th>
                                <th scope="col">Status</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="order" items="${requestScope.orders}">
                                <c:if test="${order.statusId eq 1}">
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
                    <c:if test="${(userId eq 1)}">
                        <button type="button" class="btn btn-light btn-sm" data-toggle="modal" data-target="#newBookModal">New book</button>
                        <button type="button" class="btn btn-light btn-sm" data-toggle="modal" data-target="#newAuthorModal">New author</button>
                    </c:if>
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
                                                <button class="btn btn-danger" type="submit" name="command" value="updateBook">Update book</button>
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
                                    <th scope="col">Role id</th>
                                    <c:if test="${(userId eq 1)}">
                                        <th scope="col">Update</th>
                                    </c:if>
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
                                                        not blocked
                                                    </c:if>
                                                    <c:if test="${(user.blockStatus ne 0)}">
                                                        blocked
                                                    </c:if>
                                                </td>
                                                <td><c:out value="${user.roleId}" /></td>
                                                <c:if test="${(userId eq 1)}">
                                                <td>
                                                    <form action="${pageContext.request.contextPath}/manager">
                                                        <input type="hidden" name="userId" value="${user.id}">
                                                        <button class="btn btn-danger" type="submit" name="command" value="showUpdateUser">Update user</button>
                                                    </form>
                                                </td>
                                                </c:if>
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
                <h5 class="modal-title" >New order</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">x</span>
                </button>
            </div>
            <div class="modal-body">

                <div class="accordion" id="newOrder">
<!--        OUT-->
                    <div class="card">
                        <div class="card-header" id="out">
                            <h5 class="mb-0">
                                <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseOut" aria-expanded="true" aria-controls="collapseOut">
                                    Out
                                </button>
                            </h5>
                        </div>
                        <div id="collapseOut" class="collapse show" aria-labelledby="out" data-parent="#newOrder">
                            <div class="card-body">
                                <form action="${pageContext.request.contextPath}/manager">
                                    <input type="hidden" name="statusId" value="3">

                                    <div class="form-group">
                                        <div class="input-group mb-3">
                                            <div class="input-group-prepend">
                                                <label class="input-group-text" for="inputGroupSelect01">User</label>
                                            </div>
                                            <select name="userId" class="custom-select" id="inputGroupSelect01">
                                                <option selected>Choose...</option>
                                                <c:forEach var="user" items="${requestScope.users}">
                                                    <option value="${user.id}">
                                                        <c:out value="${user.email}" />
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="input-group mb-3">
                                            <div class="input-group-prepend">
                                                <label class="input-group-text" for="inputGroupSelect02">Book</label>
                                            </div>
                                            <select name="bookId" class="custom-select" id="inputGroupSelect02">
                                                <option selected>Choose...</option>
                                                <c:forEach var="book" items="${requestScope.books}">
                                                    <option value="${book.id}">
                                                        <c:out value="${book.name}" />
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="input-group mb-3">
                                            <div class="input-group-prepend">
                                                <label class="input-group-text" for="inputGroupSelect03">How long</label>
                                            </div>
                                            <select name="count" class="custom-select" id="inputGroupSelect03">
                                                <option selected>Choose...</option>
                                                <option value="1">1</option>
                                                <option value="2">2</option>
                                                <option value="3">3</option>
                                                <option value="4">4</option>
                                                <option value="5">5</option>
                                                <option value="6">6</option>
                                                <option value="7">7</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                        <button type="submit" class="btn btn-warning" name="command" value="createOrder">Create order</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
<!--        READING ROOM-->
                    <div class="card">
                        <div class="card-header" id="readingRoom">
                            <h5 class="mb-0">
                                <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseRoom" aria-expanded="false" aria-controls="collapseRoom">
                                    Reading room
                                </button>
                            </h5>
                        </div>
                        <div id="collapseRoom" class="collapse" aria-labelledby="readingRoom" data-parent="#newOrder">
                            <div class="card-body">
                                <form action="${pageContext.request.contextPath}/manager">
                                    <input type="hidden" name="statusId" value="2">
                                    <input type="hidden" name="count" value="0">

                                    <div class="form-group">
                                        <div class="input-group mb-3">
                                            <div class="input-group-prepend">
                                                <label class="input-group-text" for="roomSelect">User</label>
                                            </div>
                                            <select name="userId" class="custom-select" id="roomSelect">
                                                <option selected>Choose...</option>
                                                <c:forEach var="user" items="${requestScope.users}">
                                                    <option value="${user.id}">
                                                        <c:out value="${user.email}" />
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="input-group mb-3">
                                            <div class="input-group-prepend">
                                                <label class="input-group-text" for="roomSelect02">Book</label>
                                            </div>
                                            <select name="bookId" class="custom-select" id="roomSelect02">
                                                <option selected>Choose...</option>
                                                <c:forEach var="book" items="${requestScope.books}">
                                                    <option value="${book.id}">
                                                        <c:out value="${book.name}" />
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                        <button type="submit" class="btn btn-warning" name="command" value="createOrder">Create order</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- New book-->
<div class="modal fade" id="newBookModal" tabindex="-1" role="dialog" aria-labelledby="modalCenterTitle" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="title">New book</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">x</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="${pageContext.request.contextPath}/manager">
                    <div class="form-group">
                        <input class="form-control" type="text" placeholder="Title of the book" name="bookTitle" minlength="2" maxlength="99" required/>
                    </div>
                    <div class="form-group">
                        <input name="bookEdition" type="text" class="form-control" placeholder="Edition" minlength="2" maxlength="99" required />
                    </div>
                    <div class="form-group">
                        <input name="editionDate" type="text" class="form-control" placeholder="Year of edition" minlength="4" maxlength="4" pattern="[0-9]+" required />
                    </div>
                    <div class="form-group">
                        <input name="bookReview" type="text" class="form-control" placeholder="Book review" minlength="1" required />
                    </div>
                    <div class="form-group">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <label class="input-group-text" for="inputGroupSelect01">Author</label>
                            </div>
                            <select name="authorId" class="custom-select" id="authorsGroup">
                                <option selected>Choose...</option>
                                <c:forEach var="author" items="${requestScope.authors}">
                                    <option value="${author.getValue().id}">
                                        <c:out value="${author.getValue().firstName}" /> <c:out value="${author.getValue().lastName}" />
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <label class="input-group-text" for="inputGroupSelect01">Genre</label>
                            </div>
                            <select name="genreId" class="custom-select" id="genresGroup">
                                <option selected>Choose...</option>
                                <c:forEach var="genre" items="${requestScope.genreMap}">
                                    <option value="${genre.getValue().id}">
                                        <c:out value="${genre.getValue().name}" />
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <input name="bookPrice" type="text" class="form-control" placeholder="Price" minlength="3" maxlength="9" pattern="[0-9]+" required />
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-light btn-sm" data-toggle="modal" data-target="#newAuthorModal">New author</button>
                        <button type="submit" class="btn btn-warning" name="command" value="createBook">Create book</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- New author-->
<div class="modal fade" id="newAuthorModal" tabindex="-1" role="dialog" aria-labelledby="modalCenterTitle" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="newAuthorTitle">New author</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">x</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="${pageContext.request.contextPath}/manager">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">Name</span>
                            </div>
                            <input name="authorFirstName" type="text" aria-label="First name" placeholder="First name" class="form-control" required >
                            <input name="authorLastName" type="text" aria-label="Last name" placeholder="Last name" class="form-control" required >
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">Date of birthday</span>
                            </div>
                            <input name="year" type="text" aria-label="Year" placeholder="Year" class="form-control" minlength="4" maxlength="4" pattern="[0-9]+" required >
                            <input name="month" type="text" aria-label="Last name" placeholder="Month" class="form-control" minlength="2" maxlength="2" pattern="[0-9]+" required >
                            <input name="day" type="text" aria-label="Last name" placeholder="Day" class="form-control" minlength="2" maxlength="2" pattern="[0-9]+" required >
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-warning" name="command" value="createAuthor">Create author</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>