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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
                                <button class="dropdown-item" type="submit" name="command" value="showSettings"><fmt:message key="settings" /></button>
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

<!-- Modal Registration -->
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
                <form class="needs-validation" novalidate>
                    <div id="registr-info"></div>
                    <div class="form-group">
                        <input class="form-control" type="text" placeholder="Login" id="loginReg" minlength="3" maxlength="19"
                               pattern="^(?=\S+$).{3,19}$" required/>
                        <div class="invalid-feedback">
                            Please choose a login.
                        </div>
                    </div>
                    <div class="form-group">
                        <input id="passwordReg" type="password" class="form-control" placeholder="Password" minlength="6" maxlength="100"
                               pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{6,}$" required />
                        <div class="invalid-feedback">
                            <ul>
                            <li> a digit must occur at least once </li>
                            <li> a lower case letter must occur at least once </li>
                            <li> an upper case letter must occur at least once </li>
                            <li> no whitespace allowed in the entire string </li>
                            <li> at least 8 characters </li>
                            </ul>
                        </div>
                    </div>
                    <div class="form-group">
                        <input id="firstNameReg" type="text" class="form-control" placeholder="First name" minlength="1" maxlength="19" required />
                        <div class="invalid-feedback">
                            Enter correct first name
                        </div>
                    </div>
                    <div class="form-group">
                        <input id="lastNameReg" type="text" class="form-control" placeholder="Last name" minlength="1" maxlength="19" required />
                        <div class="invalid-feedback">
                            Enter correct last name
                        </div>
                    </div>
                    <div class="form-group">
                        <input id="emailReg" type="email" class="form-control"  placeholder="Enter email"
                               pattern="\b[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}\b" required/>
                        <div class="invalid-feedback">
                            Enter correct email
                        </div>
                    </div>
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text">+38</span>
                        </div>
                        <input id="telephoneReg" type="tel" class="form-control" placeholder="Telephone" minlength="10" maxlength="10"
                               pattern="[0-9]+" required />
                        <div class="invalid-feedback">
                            Telephone is invalid
                        </div>
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
                        <button class="btn btn-warning" id="regisButton" disabled="true">Register</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Modal Login-->
<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modalTitle">Sign-In</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form>
                <div class="modal-body">
                    <div id="auth-info"></div>
                    <div class="form-group">
                        <input class="form-control" type="text" placeholder="Login" id="loginLog"/>
                    </div>
                    <div class="form-group">
                        <input class="form-control" type="password" placeholder="Password" id="passwordLog"/>
                    </div>
                </div>
            </form>
                <div class="modal-footer">
                    <div class="form-check">
                        <input type="checkbox" class="form-check-input" id="stayLogin">
                        <label class="form-check-label" for="Check">Remember me</label>
                    </div>
                    <button class="btn btn-warning" id="button">Login</button>
                </div>
        </div>
    </div>
</div>


<script type="text/javascript" src="js/login.js"></script>
<script type="text/javascript" src="js/registr.js"></script>
</body>
</html>
