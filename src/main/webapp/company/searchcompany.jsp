<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.List"
    import="it.contrader.dto.QuestionDTO"
    import="it.contrader.dto.CompanyDTO"
    import="it.contrader.dto.CompanyNameDTO"
    import="it.contrader.dto.CompanySectorDistinctDTO"
	import="it.contrader.dto.UserDTO"
	import="it.contrader.dto.UserTypeDistinctDTO"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Search Company</title>
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
	<script>document.getElementById("companylink").classList.add("active");</script>


	<!-- BODY -->
	
	<div class="main">
		<!-- COMPANYLIST -->

		<% List<CompanyDTO> companyResultList = (List<CompanyDTO>) request.getAttribute("companyResultList");
		   List<CompanySectorDistinctDTO> sectorDistinctAllList = (List<CompanySectorDistinctDTO>) request.getAttribute("sectorDistinctAllList");
		%>
		<br>
		<table>
			<tr>
				<th>Name</th>
				<th>address</th>
				<th>city</th>
				<th>Sector</th>
				<th></th>
				<th></th>
			</tr>
			<% for (CompanyDTO c : companyResultList) {%>
				<tr>
					<td><a href=CompanyServlet?mode=read&id=<%=c.getId()%>><%=c.getName()%></a></td>
					<td><%=c.getAddress()%></td>
					<td><%=c.getCity()%></td>
					<td><%=c.getSector()%></td>
					<td><a href=CompanyServlet?mode=read&update=true&id=<%=c.getId()%>>Edit</a></td>
					<td><a href=CompanyServlet?mode=delete&id=<%=c.getId()%>>Delete</a></td>
				</tr>
			<%}%>
		</table>
	
	
		<!-- SEARCH FORM -->
	
		<form id="floatright" action="CompanyServlet?mode=search&search=true" method="post">
			<div class="row">
			    <div class="col-25">
			    	<label for="name">nome</label>
			    </div>
			    <div class="col-75">
			    	<input type="text" id="name" name="name" placeholder="inserisci nome">
			    </div>
			    <div class="col-25">
			    	<label for="address">indirizzo</label>
			    </div>
			    <div class="col-75">
			    	<input type="text" id="address" name="address" placeholder="inserisci indirizzo">
			    </div>
			    <div class="col-25">
			    	<label for="city">città</label>
			    </div>
			    <div class="col-75">
			    	<input type="text" id="city" name="city" placeholder="inserisci città">
			    </div>
			   <div class="col-25">
			    	<label for="sector">settore</label>
			    </div>
			    <div class="col-75">
			    	<select id="sector" name="sector">
			    		<option value="%">TUTTI</option>
			    		<% for (CompanySectorDistinctDTO sector : sectorDistinctAllList) {%>
			    			<option value="<%=sector.getSector()%>"><%=sector.getSector()%></option>
			    		<%}%>
			    	</select>
			    </div>
			</div>
			<button type="submit" >Cerca</button>
		</form>
	</div>
	<br>

		
	<!-- FOOTER -->	
	
	<%@ include file="../css/footer.jsp" %>
</body>
</html>