<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<html>
<head>
<link href="css/vittoriostyle.css" rel="stylesheet">

<title>Login</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>
	<form class="registerform" action="RegisterServlet?register&usertype=USER" method="post">
		<div class="row">
			<div class="col-25">
				<label for="user">Username</label>
			</div>
			<div class="col-75">
				<input type="text" id="user" name="username" placeholder="inserisci username" required>
			</div>
		</div>
		<div class="row">
			<div class="col-25">
				<label for="pass">Password</label>
			</div>
			<div class="col-75">
				<input type="text" id="pass" name="password" placeholder="inserisci password" required> 
			</div>
		</div>
		<button type="submit" >Sign Up</button>
	</form>

	
</body>
</html>