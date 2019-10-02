<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" 
    import="it.contrader.dto.RecruiterDTO"
%>
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
	<script>document.getElementById("recruiterlink").classList.add("active");</script>
	<br>
	<div class="main">
		<%RecruiterDTO r = (RecruiterDTO) request.getAttribute("recruiterDTO");%>
		
		
		<table>
			<tr> 
				<th>Name</th>
				<th>Company</th>
			</tr>
			<tr>
				<td><%=r.getName()%></td>
				<td><%=r.getCompany().getName()%></td>
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