<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="it.contrader.dto.CompanyDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Read page</title>
</head>
<body>
	<%@ include file="../css/header.jsp" %>
	<%@ include file="../css/adminmenu.jsp"%>
	<script>document.getElementById("companylink").classList.add("active");</script>
	<br>
	
	<div class="main">
	<% CompanyDTO company = (CompanyDTO) request.getAttribute("company");%>
	
	<div class="mainleft">
		<table class="greenTable">
			<tr> 
				<th>Name</th>
				<th>Address</th>
				<th>City</th>
				<th>Sector</th>
			</tr>
			<tr>
				<td><%=company.getName()%></td>
				<td> <%=company.getAddress()%></td>
				<td> <%=company.getCity()%></td>
				<td> <%=company.getSector()%></td>
			</tr>	
		</table>
	</div>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	
	
	</div>
	
	<%@ include file="../css/footer.jsp" %>
</body>
</html>