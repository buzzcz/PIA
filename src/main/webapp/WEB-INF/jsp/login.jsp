<!DOCTYPE html>
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
		<form class="form-inline" id="loginForm" action="javascript:" onsubmit="submitForm('#loginForm',
		'/authenticate')">
			<label class="sr-only" for="username">Username</label>
			<input class="form-control" type="text" id="username" name="username" placeholder="Username" required
				   autofocus>
			<label class="sr-only" for="password">Password</label>
			<input class="form-control" type="password" id="password" name="password" placeholder="Password" required>
			<button class="btn btn-default" type="submit" title="Log In">Log In</button>
		</form>
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
			<form class="form-horizontal" id="registerForm" action="javascript:" onsubmit="submitForm('#registerForm',
		'/register')">
				<div class="form-inline bottom-padding">
					<label class="sr-only" for="first-name">First Name</label>
					<input class="form-control" type="text" id="first-name" name="first-name" placeholder="First Name"
						   required>
					<label class="sr-only" for="last-name">Last Name</label>
					<input class="form-control" type="text" id="last-name" name="last-name" placeholder="Last Name"
						   required>
				</div>
				<div class="form-inline bottom-padding">
					<label class="sr-only" for="birthday">Birthday</label>
					<div class="input-group date">
					<input class="form-control" type="text" id="birthday" name="birthday" placeholder="Birthday"
						   required><span class="input-group-addon"><span
							class="glyphicon glyphicon-calendar"></span></span>
					</div>
					<label class="sr-only" for="email-register">Email</label>
					<input class="form-control" type="email" id="email-register" name="email" placeholder="Email"
						   required>
				</div>
				<div class="form-inline bottom-padding">
					<label class="sr-only" for="password-register">Password</label>
					<input class="form-control" type="password" id="password-register" name="password"
						   placeholder="Password" required>
					<label class="sr-only" for="password-repeat">Password Repeat</label>
					<input class="form-control" type="password" id="password-repeat" name="repeat-password"
						   placeholder="Repeat Password" required>
				</div>
				<div class="form-inline bottom-padding">
					<label class="sr-only" for="username">Username</label>
					<input class="form-control" type="text" id="username-register" name="username"
						   placeholder="Username"
						   required>
				</div>
				<div class="form-inline bottom-padding">
					<div class="radio-inline">
						<input type="radio" name="gender" id="female" value="female" required>
						<label for="female">Female</label>
					</div>
					<div class="radio-inline">
						<input type="radio" name="gender" id="male" value="male" required>
						<label for="male">Male</label>
					</div>
				</div>
				<div class="form-inline bottom-padding">
					<label for="selected-file">Profile Picture:</label>
					<div class="input-group">
						<label class="input-group-btn" for="profile-picture"><span
								class="btn btn-default">Browse:</span>
							<input type="file" id="profile-picture" accept="image/*" required>
						</label>
						<input class="form-control" id="selected-file" type="text" value="No file chosen&hellip;" readonly>
					</div>
				</div>
				<button class="btn btn-default" type="submit" title="Sign Up">Sign Up</button>
			</form>
		</div>
	</div>
</div>
</body>
</html>
