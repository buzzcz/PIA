<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-datepicker3.min.css">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
	<script type="application/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script type="application/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script type="application/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datepicker.min.js"></script>
	<script type="application/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
	<script type="application/javascript" src="${pageContext.request.contextPath}/js/login.js"></script>
	<title>Login</title>
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top container-fluid row">
	<div class="col-xs-9 col-sm-3 col-md-5 col-lg-6">
		<a class="btn btn-link" id="logo">Kiv<span class="logo-border">Book</span></a>
	</div>
	<div class="visible-xs col-xs-3 pull-right top-padding">
		<button class="btn btn-default glyphicon glyphicon-menu-hamburger" data-toggle="collapse"
				data-target=".collapsible-menu" aria-expanded="true" aria-controls="log-in-form"
				title="Menu"></button>
	</div>
	<div class="collapse in col-xs-12 col-sm-9 col-md-7 col-lg-6 pull-right top-padding collapsible-menu"
		 id="log-in-form">
        <form:form class="form-inline" id="loginForm" action="/authenticate" method="post" modelAttribute="user">
			<form:label class="sr-only" path="username">Username</form:label>
			<form:input class="form-control" type="text" id="username" path="username" placeholder="Username"
						value="${username}" required="true" autofocus="true"/>
			<form:label class="sr-only" path="password">Password</form:label>
			<form:input class="form-control" type="password" id="password" path="password" placeholder="Password"
						value="${password}" required="true"/>
			<button class="btn btn-default" type="submit" title="Log In">Log In</button>
		</form:form>
	</div>
</nav>
<div class="container-fluid full-height">
	<div class="row content full-height">
		<div class="col-xs-12 col-sm-6">
			<h2>Connect with your friends and meet new people.</h2>
			<ul>
				<li><h3>See your friends profiles, posts and photos.</h3></li>
				<li><h3>Share your own stories and photos.</h3></li>
				<li><h3>Find new people and connect with them.</h3></li>
			</ul>
		</div>
		<div class="col-xs-12 col-sm-6">
			<h1>Sign Up!</h1>
			<h4>By signing up you can access other people's profiles, contributions etc.</h4>
			<form:form class="form-horizontal" id="registerForm" action="/register" method="post"
					   modelAttribute="newUser">
                <c:set var="hasBindErrors">
                    <form:errors path="*"/>
                </c:set>
                <c:if test="${not empty hasBindErrors}">
                    <div class="well-sm form-inline bottom-margin errors">
                        <form:errors path="*"/>
                    </div>
                </c:if>
				<div class="form-inline bottom-padding">
					<form:label class="sr-only" path="firstName">First Name</form:label>
					<form:input class="form-control" type="text" id="first-name" path="firstName"
								placeholder="First Name" value="${firstName}" required="true"/>
					<form:label class="sr-only" path="lastName">Last Name</form:label>
					<form:input class="form-control" type="text" id="last-name" path="lastName" placeholder="Last Name"
								value="${lastName}" required="true"/>
				</div>
				<div class="form-inline bottom-padding">
					<form:label class="sr-only" path="username">Username</form:label>
					<form:input class="form-control" type="text" id="username-register" path="username"
								placeholder="Username" value="${username}" required="true"/>
					<form:label class="sr-only" path="email">Email</form:label>
					<form:input class="form-control" type="email" id="email" path="email" placeholder="Email"
								value="${email}" required="true"/>
				</div>
				<div class="form-inline bottom-padding">
					<form:label class="sr-only" path="password">Password</form:label>
					<form:input class="form-control" type="password" id="password-register" path="password"
								placeholder="Password" value="${password}" required="true"/>
					<form:label class="sr-only" path="passwordRepeat">Password Repeat</form:label>
					<form:input class="form-control" type="password" id="password-repeat" path="passwordRepeat"
								placeholder="Repeat Password" value="${passwordRepeat}" required="true"/>
				</div>
				<div class="form-inline bottom-padding">
					<form:label class="sr-only" path="birthday">Birthday</form:label>
					<div class="input-group date">
						<form:input class="form-control" type="text" id="birthday" path="birthday"
									placeholder="Birthday" value="${birthday}"/>
						<span class="input-group-addon"><span
								class="glyphicon glyphicon-calendar"></span></span>
					</div>
				</div>
				<div class="form-inline bottom-padding">
					<div class="radio-inline">
						<form:radiobutton path="gender" id="female" value="FEMALE" required="true"/>
						<form:label path="gender">Female</form:label>
					</div>
					<div class="radio-inline">
						<form:radiobutton path="gender" id="male" value="MALE" required="true"/>
						<form:label path="gender">Male</form:label>
					</div>
				</div>
				<div class="form-inline bottom-padding">
					<label for="selected-file">Profile Picture:</label>
					<div class="input-group">
						<form:label class="input-group-btn" path="picture"><span
								class="btn btn-default">Browse:</span>
							<form:input type="file" id="profile-picture" path="picture" accept="image/*"/>
						</form:label>
						<input class="form-control" id="selected-file" type="text" name="selected-file"
							   value="No file chosen&hellip;" readonly/>
					</div>
				</div>
				<button class="btn btn-default" type="submit" title="Sign Up">Sign Up</button>
			</form:form>
		</div>
	</div>
</div>
</body>
</html>
