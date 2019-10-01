<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.CompanyDTO"
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link href="css/vittoriostyle.css" rel="stylesheet">
	<title>Company Manager</title>
</head>
<body>
	<!-- HEADER -->
	
	<%@ include file="../css/header.jsp" %>
	
	
	<!-- NAVBAR -->
	
	<%@ include file="../css/adminmenu.jsp"%>
	<script>document.getElementById("companylink").classList.add("active");</script>

	<!-- BODY -->
	
	<div class="main">
		<h1>Companies</h1>
	    <div class="mainleft">	        
			<%List<CompanyDTO> list = (List<CompanyDTO>) request.getAttribute("list");%>
			<br>
			<table class="greenTable">
				<tr>
					<th>Name</th>
					<th>Address</th>
					<th>City</th>
					<th>Sector</th>
					<th></th>
					<th></th>
				</tr>
				<%for (CompanyDTO u : list) {%>
					<tr>
						<td><a href=CompanyServlet?mode=read&id=<%=u.getId()%>><%=u.getName()%></a></td>
						<td><%=u.getAddress()%></td>
						<td><%=u.getCity()%></td>
						<td><%=u.getSector()%></td>
						<td><a class="edit" href=CompanyServlet?mode=read&update=true&id=<%=u.getId()%>>Edit</a></td>
						<td><a class="delete" href=CompanyServlet?mode=delete&id=<%=u.getId()%>>Delete</a></td>
					</tr>
				<%}%>
			</table>
	    </div>
		<div class="mainright">
			<!-- SEARCH BUTTON -->
			
			<form action="CompanyServlet?mode=search" method="post">
				<button type="submit" ><i class="searchicon"></i>Cerca</button>
			</form>
				
				
			<!-- INSERT FORM -->
		
			<form action="CompanyServlet?mode=insert" method="post">
				<div class="row">
					<div class="col-25">
						<label for="company">Name</label>
					</div>
					<div class="col-75">
						<input type="text" id="company" name="name" placeholder="inserisci il nome" required>
					</div>
				</div>
				<div class="row">
					<div class="col-25">
						<label for="address">Address</label>
					</div>
					<div class="col-75">
						<input type="text" id="address" name="address" placeholder="inserisci l'indirizzo" required> 
					</div>
				</div>
				<div class="row">
					<div class="col-25">
						<label for="city">City</label>
					</div>
					<div class="col-75">
						<input type="text" id="city" name="city" placeholder="inserisci la città" required> 
					</div>
				</div>
				<div class="row">
					<div class="col-25">
						<label for="sector">Sector</label>
					</div>
					<div class="col-75">
						<select id="sector" name="sector">
							<option value="Informatica">Informatica</option>
							<option value="Economia">Economia</option>
							<option value="Altro">Altro</option>
						</select>
					</div>
				</div>
				<button type="submit" >Insert</button>
			</form>
		</div>
	</div>
	<br>

		
	<!-- FOOTER -->	
	
	<%@ include file="../css/footer.jsp" %>
</body>
</html>