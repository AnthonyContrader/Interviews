<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.UserDTO"
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link href="/css/vittoriostyle.css" rel="stylesheet">
	<title>User management</title>
</head>
<body>
<!-- HEADER -->
	
	<%@include file="../css/header.jsp"%>
	
	
	<!-- NAVBAR -->
	
	<%@include file="../css/adminmenu.jsp"%>
	<script>document.getElementById("userlink").classList.add("active");</script>
	
	<!-- BODY -->
	
	<div class="main">
		<h1>Users</h1>
	    <div class="mainleft">
			<!-- USERLIST -->
			
			<%List<UserDTO> allUserList = (List<UserDTO>) request.getAttribute("allUserList");%>
			<br>
			<table class="greenTable">
				<tr>
					<th>Username</th>
					<th>Password</th>
					<th>UserType</th>
					<th>Email</th>
					<th></th>
					<th></th>
				</tr>
			<%for (UserDTO user : allUserList) {%>
				<tr>
					<td><a href=/User/read?prevPage=/User/management&id=<%=user.getId()%>><%=user.getUsername()%></a></td>
					<td><%=user.getPassword()%></td>
					<td><%=user.getUserType()%></td>
					<td><%=user.getEmail()%></td>
					<td><a class="edit" href=/User/update?prevPage=/User/management&update=false&id=<%=user.getId()%>>Edit</a></td>
					<td><a class="delete" href=/User/delete?id=<%=user.getId()%>>Delete</a></td>
				</tr>
			<%}%>
			</table>
	    </div>
		<div class="mainright">
		
		
	 		<!-- SEARCH BUTTON -->   
		
			<form action="/User/search" method="get">
				<input type="hidden" name="prevPage" value="/User/management">
				<button type="submit" ><i class="searchicon"></i>Search</button>
			</form>
	
	
			<!-- CREATE FORM -->
		
			<form action="/User/create" method="post">
				<div class="row">
					<div class="col-25">
						<label for="user">Username</label>
					</div>
					<div class="col-75">
						<input type="text" id="user" name="username" placeholder="insert username" required>
					</div>
				</div>
				<div class="row">
					<div class="col-25">
						<label for="pass">Password</label>
					</div>
					<div class="col-75">
						<input type="text" id="pass" name="password" placeholder="insert password" required> 
					</div>
				</div>
					<div class="row">
				    <div class="col-25">
				    	<label for="userType">UserType</label>
				    </div>
				    <div class="col-75">
				    	<select id="userType" name="userType">
				    		<option value="ADMIN">ADMIN</option>
				    		<option value="USER">USER</option> 	
				    	</select>
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
				<button type="submit" >Insert</button>
			</form>
		</div>
	</div>
	<br>
	<%@ include file="../css/footer.jsp" %>
</body>
</html>

