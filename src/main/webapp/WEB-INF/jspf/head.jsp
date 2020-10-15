<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8"%>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
          crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
            integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
            integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
            crossorigin="anonymous"></script>
    <script src="https://kit.fontawesome.com/73f5baf37a.js"
            crossorigin="anonymous"></script>
</head>
<body>

<!--Lang-->
<c:if test="${empty lang}">
    <c:set var="lang" value="resources_en" />
</c:if>
<fmt:setBundle basename="${lang}"/>

<!--Header-->
<nav class="navbar navbar-expand-md navbar-light bg-light sticky-top">
    <div class="container-fluid">
        <a href="#" class="navbar-brand">
            <i class="fas fa-archway"></i> Library
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <div class="btn-group" role="group" aria-label="Basic example">
                        <form action="${pageContext.request.contextPath}/">
                            <button type="submit" class="btn btn-secondary btn-lg">
                                <i class="fas fa-book"></i>
                                <fmt:message key="main" />
                            </button>
                        </form>
                        <form action="${pageContext.request.contextPath}/manager">
                            <button type="submit" class="btn btn-secondary btn-lg " name="command" value="showBooks">
                                <i class="fas fa-book-open"></i>
                                <fmt:message key="books" />
                            </button>
                            <c:if test="${(userId eq 1)  || (userId eq 2)}">
                                <button class="btn btn-primary" type="submit" name="command" value="manager"><fmt:message key="manager" /></button>
                            </c:if>
                        </form>
                    </div>
                </li>
            </ul>
            <ul class="navbar-nav justify-content-end">
<!--    Language-->
                <form action="${pageContext.request.contextPath}/manager">
                    <input type="hidden" name="command" value="lang">
                    <div class="btn-group">
                        <button type="button" class="btn btn-light btn-sm dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <fmt:message key="language" />
                        </button>
                        <div class="dropdown-menu dropdown-menu-right">
                            <button class="dropdown-item" type="submit" name="value" value="resources_ru">Ru</button>
                            <div class="dropdown-divider"></div>
                            <button class="dropdown-item" type="submit" name="value" value="resources_en">En</button>
                        </div>
                    </div>
                </form>
<!--    Register / Sign-In-->
                <c:if test="${login eq null}">
                    <button type="button" class="btn btn-outline-dark btn-sm" data-toggle="modal" data-target="#registerModal"><fmt:message key="registration" /></button>
                    <button type="button" class="btn btn-warning btn-md" data-toggle="modal" data-target="#loginModal"><fmt:message key="login" /></button>
                </c:if>
<!--    Settings-->
                <c:if test="${login ne null}">
                    <form action="${pageContext.request.contextPath}/manager">
                        <div class="btn-group">
                            <button type="button" class="btn btn-warning dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <c:out value="${login}" />
                            </button>
                            <div class="dropdown-menu dropdown-menu-right">
                                <button class="dropdown-item" type="submit" name="command" value="showUpdateUser"><fmt:message key="settings" /></button>
                                <button class="dropdown-item" type="button"><fmt:message key="orders" /></button>
                                <div class="dropdown-divider"></div>
                                <button class="dropdown-item" type="submit" name="command" value="logout"><fmt:message key="logout" /></button>
                            </div>
                        </div>
                    </form>
                </c:if>
            </ul>
        </div>
    </div>
</nav>

<!-- Registration -->
<div class="modal fade" id="registerModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Registration</h5>
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

<!--  Login-->
<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modalTitle">Sign-In</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form method="post" action="${pageContext.request.contextPath}/manager">
                <div class="modal-body">
                        <div class="form-group">
                            <input name="login" type="text" class="form-control" placeholder="Login" required>
                        </div>
                        <div class="form-group">
                            <input name="password" type="password" class="form-control" placeholder="Password" required>
                        </div>
                </div>
                <div class="modal-footer">
                    <div class="form-check">
                        <input type="checkbox" class="form-check-input" id="stayLogin">
                        <label class="form-check-label" for="Check">Remember me</label>
                    </div>
                    <button type="submit" class="btn btn-warning" name="command" value="login">Login</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    $('input#Check').change(function () {
        if ($('input#Check').is(':checked')) {
            $('button#regisButton').prop( "disabled", false );
        } else {
            $('button#regisButton').prop( "disabled", true );
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
