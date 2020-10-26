<%@include file="/WEB-INF/jspf/head.jsp" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<link rel="stylesheet" href="css/main.css">

<html>
<head>
    <title>Settings</title>
</head>
<body>
<c:set var = "user" value = "${user}"/>

<div class="container">
    <div class="form-group row">
        <div class="col-sm-10 col-form-label">
            <form >
                <div class="form-group row">
                    <label for="staticLogin" class="col-sm-2 col-form-label">Login</label>
                    <div class="col-sm-10">
                        <input type="text" readonly class="form-control-plaintext" id="staticLogin" value="${user.login}">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="staticName" class="col-sm-2 col-form-label">Name</label>
                    <div class="col-sm-10">
                        <input type="text" readonly class="form-control-plaintext" id="staticName" value="${user.firstName} ${user.lastName}">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="staticEmail" class="col-sm-2 col-form-label">Email</label>
                    <div class="col-sm-10">
                        <input type="text" readonly class="form-control-plaintext" id="staticEmail" value="${user.email}">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="staticTelephone" class="col-sm-2 col-form-label">Telephone</label>
                    <div class="col-sm-10">
                        <input type="text" readonly class="form-control-plaintext" id="staticTelephone" value="${user.telephone}">
                    </div>
                </div>
            </form>
        </div>
        <div class="col-sm-2 col-form-label">
            <form method="post" action="${pageContext.request.contextPath}/manager">
                <input hidden name="userId" value="${user.id}">
                <c:if test="${user.blockStatus eq 0}">
                    <input hidden name="blockId" value="1">
                    <button class="btn btn-warning" id="blockUserButton" type="submit" name="command" value="blockUser">Block</button>
                </c:if>
                <c:if test="${user.blockStatus ne 0}">
                    <input hidden name="blockId" value="0">
                    <button class="btn btn-info" id="unblockUserButton" type="submit" name="command" value="blockUser">Unblock</button>
                </c:if>
                <hr />
                <c:if test="${user.roleId eq 1}">
                    <input hidden name="roleId" value="2">
                    <button class="btn btn-warning" id="removeLibrarianButton" type="submit" name="command" value="updateRole">Remove librarian</button>
                </c:if>
                <c:if test="${user.roleId gt 1}">
                    <input hidden name="roleId" value="1">
                    <button class="btn btn-warning" id="createLibrarianButton" type="submit" name="command" value="updateRole">Create librarian</button>
                </c:if>
            </form>
        </div>
    </div>

    <hr/>

    <h2>Orders</h2>
    <table id="ordersTable" class="table table-striped table-hover">
        <thead>
        <tr>
            <th scope="col">Book</th>
            <th scope="col">When</th>
            <th scope="col">How long</th>
            <th scope="col">Status</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="order" items="${requestScope.orders}">
            <tr>
                <th scope="row"><c:out value="${bookMap.get(order.bookId).name}" /></th>
                <td><c:out value="${order.date}" /></td>
                <td><c:out value="${order.dayCount}" /></td>
                <td><c:out value="${statusMap.get(order.statusId).value}" /></td>
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

</body>
</html>