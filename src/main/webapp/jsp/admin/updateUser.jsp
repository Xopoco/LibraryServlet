<%@include file="/WEB-INF/jspf/head.jsp" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<link rel="stylesheet" href="css/main.css">

<html>
<head>
    <title>Settings</title>
</head>
<body>
<c:set var = "user" value = "${user}"/>

<div class="container-fluid">
    <div class="row">
        <div class="col-6">
        <form class="needs-validation" novalidate method="post" action="${pageContext.request.contextPath}/manager">
            <div class="form-group">
                <input type="text" class="form-control" placeholder="${user.login}" name="login" required minlength="3">
                <div class="valid-feedback">
                    Looks good!
                </div>
                <div class="invalid-feedback">
                    Please choose a login.
                </div>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" value="${user.firstName}" name="first_name" required minlength="3">
            </div>
            <div class="form-group">
                <input type="text" class="form-control" value="${user.lastName}" name="last_name" required minlength="3" title="Minimum 3 signs">
            </div>
            <div class="form-group">
                <input type="email" class="form-control" aria-describedby="emailHelp" placeholder="${user.email}" name="email" required
                       pattern="[^@\s]+@[^@\s]+\.[^@\s]+" title="John..Doe@example.com" />
                <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">+38</span>
                </div>
                <input type="tel" class="form-control" id="basic-url" placeholder="${user.telephone}" name="telephone"
                       pattern="[0-9]{10}" maxlength="10" title="Ten digits code" required>
            </div>

            <div class="modal-footer">
                <div class="form-check">
                    <input type="checkbox" class="form-check-input" id="updateCheck">
                    <label class="form-check-label" for="updateCheck">Accept changes</label>
                    <div class="invalid-feedback">
                        You must agree before submitting.
                    </div>
                </div>
                <button type="submit" class="btn btn-warning" id="updateButton" name="command" value="updateUser" disabled="true">Update</button>
            </div>
        </form>
        </div>
    </div>
</div>

<script>
    $('input#updateCheck').change(function () {
        if ($('input#updateCheck').is(':checked')) {
            $('button#updateButton').prop( "disabled", false );
        } else {
            $('button#updateButton').prop( "disabled", true );
        }
    });

    (function() {
        'use strict';
        window.addEventListener('load', function() {
        var forms = document.getElementsByClassName('needs-validation');
        var validation = Array.prototype.filter.call(forms, function(form) {
          form.addEventListener('submit', function(event) {
            if (form.checkValidity() === false) {
              event.preventDefault();
              event.stopPropagation();
            }
            form.classList.add('was-validated');
          }, false);
        });
      }, false);
    })();
</script>
</body>
</html>