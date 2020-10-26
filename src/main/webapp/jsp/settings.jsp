<%@include file="/WEB-INF/jspf/head.jsp" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<link rel="stylesheet" href="css/main.css">

<html>
<head>
    <title>User update</title>
</head>
<body>
<div class="container">

    <form method="post" action="${pageContext.request.contextPath}/manager">
        <div class="form-group">
            <input class="form-control" type="text" value="${user.login}" name="login" minlength="3" maxlength="19"
                   pattern="^(?=\S+$).{3,19}$" required/>
        </div>
        <div class="form-group">
            <input id="passwordReg" type="password" class="form-control" value="${user.password}" minlength="6" maxlength="100"
                   pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{6,}$" required />
        </div>
        <div class="form-group">
            <input name="first_name" type="text" class="form-control" value="${user.firstName}" minlength="1" maxlength="19" required />
        </div>
        <div class="form-group">
            <input name="last_name" type="text" class="form-control" value="${user.lastName}" minlength="1" maxlength="19" required />
        </div>
        <div class="form-group">
            <input name="email" type="email" class="form-control"  value="${user.email}"
                   pattern="\b[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}\b" required/>
        </div>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text">+38</span>
            </div>
            <input name="telephone" type="tel" class="form-control" value="${user.telephone}" minlength="10" maxlength="10"
                   pattern="[0-9]+" required />
        </div>

        <div class="modal-footer">
            <div class="form-check">
                <input type="checkbox" class="form-check-input" id="checkUpdate">
                <label class="form-check-label" for="checkUpdate">Confirm changes</label>
            </div>
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            <input hidden name="userId" value="${user.id}">
            <button class="btn btn-warning" id="updateButton" type="submit" name="command" value="updateSettings" disabled="true">Update</button>
        </div>
    </form>

    <hr />
    <h2>My orders</h2>
    <table id="ordersTable" class="table table-striped table-hover">
        <thead>
        <tr>
            <th scope="col">Book</th>
            <th scope="col">When</th>
            <th scope="col">How long</th>
            <th scope="col">Status</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="order" items="${requestScope.orders}">
            <tr>
                <th scope="row"><c:out value="${bookMap.get(order.bookId).name}" /></th>
                <td><c:out value="${order.date}" /></td>
                <td><c:out value="${order.dayCount}" /></td>
                <td><c:out value="${statusMap.get(order.statusId).value}" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>

<script>
    $('input#checkUpdate').change(function () {
    if ($('input#checkUpdate').is(':checked')) {
        $('button#updateButton').prop( "disabled", false );
    } else {
        $('button#updateButton').prop( "disabled", true );
    }
});
</script>

</body>
</html>