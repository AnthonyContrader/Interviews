<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.QuestionDTO"
    import="it.contrader.dto.UserDTO"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Read Question</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>
<%if(((UserDTO) session.getAttribute("user")).getUsertype().equals("ADMIN")) {%>
	<%@include file="../css/adminmenu.jsp"%><%}
else {%>
	<%@include file="../css/usermenu.jsp"%><%}%>
<script>document.getElementById("questionlink").classList.add("active");</script>
<br>

<div class="main">
<% QuestionDTO u = (QuestionDTO) request.getAttribute("dto");%>


<table>
	<tr> 
		<th>Question</th>
		<th>Sector</th>
		<th>Recruiter</th>
		<th>Company</th>
		
	</tr>
	<tr>
		<td><%=u.getQuestion()%></td>
		<td> <%=u.getSector()%></td>
		<td> <%=u.getRecruiter()%></td>
		<td> <%=u.getCompany()%></td>
		
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