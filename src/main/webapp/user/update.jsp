<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
     import="java.util.List"
    import="it.contrader.dto.UserDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>User update</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>
<%@ include file="../css/adminmenu.jsp"%>
<script>document.getElementById("userlink").classList.add("active");</script>
<br>
<div class="main">
	<%
		UserDTO user = (UserDTO) request.getAttribute("user");
	%>
	<form id="floatleft" action="/User/update?id=<%=user.getId()%>" method="post">
		<div class="row">
  			<div class="col-25">
    			<label for="user">Username</label>
  			</div>
  			<div class="col-75">
    			<input type="text" id="user" name="username" value="<%=user.getUsername()%>">
  			</div>
  		</div>
		<div class="row">
			<div class="col-25">
				<label for="pass">Password</label>
			</div>
			<div class="col-75">
				<input type="text" id="pass" name="password" value="<%=user.getPassword()%>"> 
			</div>
		</div>
		<div class="row">
			<div class="col-25">
				<label for="userType">UserType</label>
			</div>
			<div class="col-75">
				<select id="userType" name="userType" >
					<option value="ADMIN" <% if(user.getUserType().equals("ADMIN")){ %>selected<%} %>>ADMIN</option>
					<option value="USER" <%if(user.getUserType().equals("USER")){ %>selected<%} %>>USER</option>
				</select>
			</div>
		</div><div class="row">
			<div class="col-25">
				<label for="email">Email</label>
			</div>
			<div class="col-75">
				<input type="text" id="email" name="email" value="<%=user.getEmail()%>"> 
			</div>
		</div>
		<button type="submit" >Edit</button>
	</form>	
</div>
<br>
<%@ include file="../css/footer.jsp" %>	
</body>
</html>