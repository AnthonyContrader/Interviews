<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<html>
<head>
<link href="/css/vittoriostyle.css" rel="stylesheet">

<title>Login</title>
</head>
<body>
<%@ include file="/css/header.jsp" %>
	<form class="registerform" action="/User/register" method="post">
		<div class="row">
			<div class="col-25">
				<label for="username">Username</label>
			</div>
			<div class="col-75">
				<input type="text" id="username" name="username" placeholder="insert username" required>
			</div>
		</div>
		<div class="row">
			<div class="col-25">
				<label for="password">Password</label>
			</div>
			<div class="col-75">
				<input type="text" id="password" name="password" placeholder="insert password" required> 
			</div>
		</div>
		<div class="row">
			<div class="col-25">
				<label for="email">Email</label>
			</div>
			<div class="col-75">
				<input type="email" id="email" name="email" placeholder="insert email" required> 
			</div>
		</div>
		<button type="submit" >Sign Up</button>
	</form>

	
</body>
</html>