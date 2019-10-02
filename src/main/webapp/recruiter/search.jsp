<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.List"
    import="it.contrader.dto.RecruiterDTO"
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link href="css/vittoriostyle.css" rel="stylesheet">
	<title>Search page</title>
</head>
<body>
	<!-- HEADER -->

	<%@ include file="../css/header.jsp" %>
	
	
	<!-- NAVBAR -->

	<%@include file="../css/adminmenu.jsp"%>
	<script>document.getElementById("recruiterlink").classList.add("active");</script>


	<!-- BODY -->
	
	<div class="main">
		<h1>Cerca Recruiter</h1>
	    <div class="mainleft">
			<!-- USERLIST -->
	
			<% List<RecruiterDTO> allRecruiterList = (List<RecruiterDTO>) request.getAttribute("allRecruiterDTO");
			%>
			<br>
			<table class="greenTable">
				<tr>
					<th>Name</th>
					<th>Company</th>
					<th></th>
					<th></th>
				</tr>
				<% for (RecruiterDTO r : allRecruiterList) {%>
					<tr>
						<td><a href=/Recruiter/read?id=<%=r.getId()%>><%=r.getName()%></a></td>
						<td><%=r.getCompany().getName()%></td>
						<td><a class="edit" href=/Recruiter/update?id=<%=r.getId()%>>Edit</a></td>
						<td><a class="delete" href=/Recruiter/delete?id=<%=r.getId()%>>Delete</a></td>
					</tr>
				<%}%>
			</table>
		</div>
		<div class="mainright">
		
		
			<!-- SEARCH FORM -->
		
			<form id="floatright" action="/Recruiter/search?search=true" method="get">
				<div class="row">
				    <div class="col-25">
				    	<label for="name">Name</label>
				    </div>
				    <div class="col-75">
				    	<input type="text" id="name" name="name" placeholder="inserisci nome">
				    </div>
				</div>
				<div class="row">
				    <div class="col-25">
				    	<label for="company">Azienda</label>
				    </div>
				    <div class="col-75">
				    	<input type="text" id="company" name="company" placeholder="inserisci azienda">
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