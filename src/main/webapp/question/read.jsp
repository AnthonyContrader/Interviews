<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.QuestionDTO"
    import="it.contrader.dto.UserDTO"%>
    
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<link href="/css/vittoriostyle.css" rel="stylesheet">
	<title>Read Question</title>
</head>
<body>

	<!-- HEADER -->

	<%@ include file="/css/header.jsp" %>
	
	
	<!-- NAVBAR -->
	
	<%if(((UserDTO) session.getAttribute("user")).getUserType().equals("ADMIN")) {%>
		<%@include file="/css/adminmenu.jsp"%>
	<%} else {%>
		<%@include file="/css/usermenu.jsp"%>
	<%}%>
	<script>document.getElementById("questionlink").classList.add("active");</script>
	<br>
	

	<!-- BODY -->

	<div class="main">
		<% QuestionDTO q = (QuestionDTO) request.getAttribute("questionDTO");%>
		<div class="mainleft">
			<table class="greenTable">
				<tr> 
					<th>Question</th>
					<th>Topic</th>
					<th>Sector</th>
					<th>Recruiter</th>
					<th>Company</th>
				</tr>
				<tr>
					<td><%=q.getQuestion()%></td>
					<td> <%=q.getArgument()%></td>
					<td> <%=q.getSector()%></td>
					<td> <%=q.getRecruiter().getName()%></td>
					<td> <%=q.getCompany().getName()%></td>
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