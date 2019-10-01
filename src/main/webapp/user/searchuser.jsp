<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.List"
    import="it.contrader.dto.QuestionDTO"
    import="it.contrader.dto.CompanyDTO"
    import="it.contrader.dto.CompanyNameDTO"
    import="it.contrader.dto.UserTypeDistinctDTO"
	import="it.contrader.dto.UserDTO"
	import="it.contrader.dto.UserRecruiterDTO"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="css/vittoriostyle.css" rel="stylesheet">
<title>Search User</title>
</head>
<body>
	<!-- HEADER -->

	<%@ include file="../css/header.jsp" %>
	
	
	<!-- NAVBAR -->

	<% UserDTO user = (UserDTO)session.getAttribute("user");
		if(user.getUsertype().equals("ADMIN")) {%>
			<%@include file="../css/adminmenu.jsp"%><%}
		else {%>
			<%@include file="../css/usermenu.jsp"%>
	<%}%>
	<script>document.getElementById("userlink").classList.add("active");</script>


	<!-- BODY -->
	
	<div class="main">
		<h1>Cerca Utente</h1>
	    <div class="mainleft">
			<!-- USERLIST -->
	
			<% List<UserDTO> userResultList = (List<UserDTO>) request.getAttribute("userResultList");
			   List<UserTypeDistinctDTO> usertypeDistinctAllList = (List<UserTypeDistinctDTO>) request.getAttribute("usertypeDistinctAllList");
			   List<CompanyNameDTO> companyAllList = (List<CompanyNameDTO>) request.getAttribute("companyAllList");
			%>
			<br>
			<table class="greenTable">
				<tr>
					<th>Username</th>
					<th>Password</th>
					<th>Usertype</th>
					<th>Company</th>
					<th></th>
					<th></th>
				</tr>
				<% for (UserDTO u : userResultList) {%>
					<tr>
						<td><a href=QuestionServlet?mode=read&id=<%=u.getId()%>><%=u.getUsername()%></a></td>
						<td><%=u.getPassword()%></td>
						<td><%=u.getUsertype()%></td>
						<td><%=u.getCompany()%></td>
						<td><a class="edit" href=UserServlet?mode=read&update=true&id=<%=u.getId()%>>Edit</a></td>
						<td><a class="delete" href=UserServlet?mode=delete&id=<%=u.getId()%>>Delete</a></td>
					</tr>
				<%}%>
			</table>
		</div>
		<div class="mainright">
			<!-- SEARCH FORM -->
		
			<form id="floatright" action="UserServlet?mode=search&search=true" method="post">
				<div class="row">
				    <div class="col-25">
				    	<label for="username">Username</label>
				    </div>
				    <div class="col-75">
				    	<input type="text" id="username" name="username" placeholder="inserisci username">
				    </div>
				</div>
				<div class="row">
				    <div class="col-25">
				    	<label for="usertype">Usertype</label>
				    </div>
				    <div class="col-75">
				    	<select id="usertype" name="usertype">
				    		<option value="%">TUTTI</option>
				    		<% for (UserTypeDistinctDTO usertype : usertypeDistinctAllList) {%>
				    			<option value="<%=usertype.getUsertype()%>"><%=usertype.getUsertype()%></option>
				    		<%}%>
				    	</select>
				    </div>
				</div>
				<div class="row">
				    <div class="col-25">
				    	<label for="company">Azienda</label>
				    </div>
				    <div class="col-75">
				    	<select id="company" name="companyid">
				    		<option value="%">TUTTE</option>
				    		<%for (CompanyNameDTO company : companyAllList) {
				    		%>
				    			<option value="<%=company.getId()%>"><%=company.getName()%></option>	
				    		<%}%>
				    	</select>
				    </div>
				</div>
				<button type="submit" >Cerca</button>
			</form>
		</div>
	</div>
	<br>

		
	<!-- FOOTER -->	
	
	<%@ include file="../css/footer.jsp" %>
</body>
</html>