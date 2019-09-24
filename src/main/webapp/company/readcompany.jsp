<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.CompanyDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Read Company</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>
<%@ include file="../css/adminmenu.jsp"%>
<script>document.getElementById("companylink").classList.add("active");</script>
<br>

<div class="main">
<% CompanyDTO u = (CompanyDTO) request.getAttribute("dto");%>


<table>
	<tr> 
		<th>Name</th>
		<th>Address</th>
		<th>City</th>
	</tr>
	<tr>
		<td><%=u.getName()%></td>
		<td> <%=u.getAddress()%></td>
		<td> <%=u.getCity()%></td>
	</tr>	
</table>

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