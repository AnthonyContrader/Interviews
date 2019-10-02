<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="it.contrader.dto.UserDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>
<%@ include file="../css/adminmenu.jsp"%>
<script>document.getElementById("userlink").classList.add("active");</script>
<br>

<div class="main">
<%UserDTO u = (UserDTO) request.getAttribute("userDTO");%>


<table>
	<tr> 
		<th>Username</th>
		<th>Password</th>
		<th>UserType</th>
		<th>Email</th>
	</tr>
	<tr>
		<td><%=u.getUsername()%></td>
		<td><%=u.getPassword()%></td>
		<td><%=u.getUserType()%></td>
		<td><%=u.getEmail()%></td>
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