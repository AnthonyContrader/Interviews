<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.List"
    import="it.contrader.dto.CompanyDTO"
	%>
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
	<script>document.getElementById("companylink").classList.add("active");</script>


	<!-- BODY -->
	
	<div class="main">
		<h1>Search Company</h1>
	    <div class="mainleft">
	    
	    
			<!-- COMPANYLIST -->
	
			<%List<CompanyDTO> companyResultList = (List<CompanyDTO>) request.getAttribute("companyResultList");
			  List<String> sectorDistinctList = (List<String>) request.getAttribute("sectorDistinctList");
			%>
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
				<% for (CompanyDTO company : companyResultList) {%>
					<tr>
						<td><a href=/Company/read?id=<%=company.getId()%>><%=company.getName()%></a></td>
						<td><%=company.getAddress()%></td>
						<td><%=company.getCity()%></td>
						<td><%=company.getSector()%></td>
						<td><a class="edit" href=/Company/update?id=<%=company.getId()%>>Edit</a></td>
						<td><a class="delete" href=/Company/delete?id=<%=company.getId()%>>Delete</a></td>
					</tr>
				<%}%>
			</table>
	    </div>
	    <div class="mainright">
	    
	    
			<!-- SEARCH FORM -->
			
			<form action="/Company/search" method="post">
				<div class="row">
				    <div class="col-25">
				    	<label for="name">Name</label>
				    </div>
				    <div class="col-75">
				    	<input type="text" id="name" name="name" placeholder="insert name">
				    </div>
				</div>
				<div class="row">
				    <div class="col-25">
				    	<label for="address">Address</label>
				    </div>
				    <div class="col-75">
				    	<input type="text" id="address" name="address" placeholder="insert address">
				    </div>
				</div>
				<div class="row">
				    <div class="col-25">
				    	<label for="city">City</label>
				    </div>
				    <div class="col-75">
				    	<input type="text" id="city" name="city" placeholder="inserit city">
				    </div>
				</div>
				<div class="row">
				    <div class="col-25">
				    	<label for="sector">Sector</label>
				    </div>
				    <div class="col-75">
				    <select id="sector" name="sector"> 
						<%for (String sector : sectorDistinctList) {%>
							<option value=<%=sector%>><%=sector%></option>
						<%}%>
						</select>
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