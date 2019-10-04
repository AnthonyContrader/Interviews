<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.List"
    import="it.contrader.dto.UserDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="/css/vittoriostyle.css" rel="stylesheet">
<title>Search page</title>
</head>
<body>
	<!-- HEADER -->

	<%@ include file="../css/header.jsp" %>
	
	
	<!-- NAVBAR -->

	<%@include file="../css/adminmenu.jsp"%>
	<script>document.getElementById("userlink").classList.add("active");</script>


	<!-- BODY -->
	
	<div class="main">
		<h1>Search User</h1>
	    <div class="mainleft">
	    
	    
			<!-- USERLIST -->
	
			<% List<UserDTO> allUserList = (List<UserDTO>) request.getAttribute("userResultList");%>
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
				<% for (UserDTO u : allUserList ) {%>
					<tr>
						<td><a href=/User/read?id=<%=u.getId()%>><%=u.getUsername()%></a></td>
						<td><%=u.getPassword()%></td>
						<td><%=u.getUserType()%></td>
						<td><%=u.getEmail()%></td>
						<td><a class="edit" href=/User/update?id=<%=u.getId()%>>Edit</a></td>
						<td><a class="delete" href=/User/delete?id=<%=u.getId()%>>Delete</a></td>
					</tr>
				<%}%>
			</table>
		</div>
		<div class="mainright">
		
		
			<!-- SEARCH FORM -->
		
			<form id="floatright" action="/User/search" method="post">
				<div class="row">
				    <div class="col-25">
				    	<label for="username">Username</label>
				    </div>
				    <div class="col-75">
				    	<input type="text" id="username" name="username" placeholder="insert username">
				    </div>
				</div>
				<div class="row">
				    <div class="col-25">
				    	<label for="userType">UserType</label>
				    </div>
				    <div class="col-75">
				    	<select id="userType" name="userType">
				    		<option value="%">ALL</option>
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
				    	<input type="text" id="email" name="email" placeholder="insert email">
				    </div>
				</div>
				
				
				<button type="submit" >Search</button>
			</form>
		</div>
	</div>
	<br>

		
	<!-- FOOTER -->	
	
	<%@ include file="../css/footer.jsp" %>
</body>
</html>