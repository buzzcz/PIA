<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-datepicker3.min.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
    <script type="application/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="application/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="application/javascript"
            src="${pageContext.request.contextPath}/js/bootstrap-datepicker.min.js"></script>
    <script type="application/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
    <title>Login</title>
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top container-fluid row">
    <div class="col-xs-12">
        <a class="btn btn-link" id="logo">Kiv<span class="logo-border">Book</span></a>
    </div>
</nav>
<div class="container-fluid full-height">
    <div class="row content full-height">
        <div
                class="col-xs-12 col-sm-6 col-sm-offset-3 col-md-4 col-md-offset-4 col-lg-3 col-lg-offset-5 top-padding">
            <div class="bottom-padding">
                <div class="well-sm bottom-margin errors">
                    Wrong username or password.
                </div>
                <form:form class="form-horizontal" id="loginForm" action="authenticate" method="post"
                           modelAttribute="user">
                    <form:label class="sr-only" path="username">Username</form:label>
                    <form:input class="form-control" type="text" id="username" path="username" placeholder="Username"
                                value="${username}" required="true" autofocus="true"/>
                    <form:label class="sr-only" path="password">Password</form:label>
                    <form:input class="form-control" type="password" id="password" path="password"
                                placeholder="Password"
                                value="${password}" required="true"/>
                    <button class="btn btn-default form-control" type="submit" title="Log In">Log In</button>
                </form:form>
            </div>
            <div>
                <button class="btn btn-default form-control" type="button" onclick="window.location.href='login'"
                        title="Register">Register
                </button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
