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
		<h1>Cerca Azienda</h1>
	    <div class="mainleft">
			<!-- COMPANYLIST -->
	
			<%List<CompanyDTO> companyList = (List<CompanyDTO>) request.getAttribute("allCompanyDTO");
			  List<String> allDistinctSectorList = (List<String>) request.getAttribute("allDistinctSector");
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
				<% for (CompanyDTO c : companyList) {%>
					<tr>
						<td><a href=/Company/read?id=<%=c.getId()%>><%=c.getName()%></a></td>
						<td><%=c.getAddress()%></td>
						<td><%=c.getCity()%></td>
						<td><%=c.getSector()%></td>
						<td><a class="edit" href=/Company/update?id=<%=c.getId()%>>Edit</a></td>
						<td><a class="delete" href=/Company/delete?id=<%=c.getId()%>>Delete</a></td>
					</tr>
				<%}%>
			</table>
	    </div>
	    <div class="mainright">
	    
	    
			<!-- SEARCH FORM -->
			
			<form action="/Company/search" method="post">
				<div class="row">
				    <div class="col-25">
				    	<label for="name">Nome</label>
				    </div>
				    <div class="col-75">
				    	<input type="text" id="name" name="name" placeholder="inserisci nome">
				    </div>
				</div>
				<div class="row">
				    <div class="col-25">
				    	<label for="address">Indirizzo</label>
				    </div>
				    <div class="col-75">
				    	<input type="text" id="address" name="address" placeholder="inserisci indirizzo">
				    </div>
				</div>
				<div class="row">
				    <div class="col-25">
				    	<label for="city">Città</label>
				    </div>
				    <div class="col-75">
				    	<input type="text" id="city" name="city" placeholder="inserisci città">
				    </div>
				</div>
				<div class="row">
				    <div class="col-25">
				    	<label for="sector">Settore</label>
				    </div>
				    <div class="col-75">
				    <select id="sector" name="sector"> 
						<%for (String sector : allDistinctSectorList) {%>
							<option value=<%=sector%>><%=sector%></option>
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