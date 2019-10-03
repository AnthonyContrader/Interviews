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


	<!-- HEADER -->
	
	<%@ include file="../css/header.jsp" %>
	
	
	<!-- NAVBAR -->
	
	<%@ include file="../css/adminmenu.jsp"%>
	<script>document.getElementById("recruiterlink").classList.add("active");</script>
	<br>
	
	
	<!-- BODY -->
	
	<div class="main">
		<%RecruiterDTO r = (RecruiterDTO) request.getAttribute("recruiterDTO");%>
		<div class="mainleft">
			<table class="greenTable">
				<tr> 
					<th>Name</th>
					<th>Company</th>
				</tr>
				<tr>
					<td><%=r.getName()%></td>
					<td><%=r.getCompany().getName()%></td>
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
	
	
	<!-- FOOTER -->	
	
	<%@ include file="../css/footer.jsp" %>
</body>
</html>