<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
<link href="/css/vittoriostyle.css" rel="stylesheet">
<title>Login SAMPLE</title>
</head>
<body>
<%@ include file="/css/header.jsp" %>
		<form class="login" action="/Home/login" method="post">

				<label for="user">Username: </label>
			
				<input type="text" id="user" name="username" placeholder="Insert username" required>
		
				<label for="pass">Password: </label>
			
				<input type="password" id="pass" name="password" placeholder="Insert password" required>
						
			<button type="submit">Login</button>
			
			<a id="signup" href="/User/register" >Sign Up</a>
						
			<%if (request.getAttribute("error")!=null){%><a id="error" >Login Error!</a><%}%>
		</form>

	
</body>
</html>