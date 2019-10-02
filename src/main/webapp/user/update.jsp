<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
     import="java.util.List"
    import="it.contrader.dto.UserDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>User update</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>
<%@ include file="../css/adminmenu.jsp"%>
<script>document.getElementById("userlink").classList.add("active");</script>
<br>
<div class="main">
<%
	UserDTO u = (UserDTO) request.getAttribute("userDTO");
	%>
<form id="floatleft" action="/User/update?id=<%=u.getId()%>" method="post">
  <div class="row">
    <div class="col-25">
      <label for="user">Username</label>
    </div>
    <div class="col-75">
      <input type="text" id="user" name="username" value="<%=u.getUsername()%>">
    </div>
  </div>
  <div class="row">
    <div class="col-25">
     <label for="pass">Password</label>
    </div>
    <div class="col-75">
      <input
			type="text" id="pass" name="password" value="<%=u.getPassword()%>"> 
    </div>
  </div>
  <div class="row">
    <div class="col-25">
     <label for="userType">UserType</label>
    </div>
    <div class="col-75">
      <input
			type="text" id="userType" name="userType" value="<%=u.getUserType()%>"> 
    </div>
  </div><div class="row">
    <div class="col-25">
     <label for="email">Email</label>
    </div>
    <div class="col-75">
      <input
			type="text" id="email" name="email" value="<%=u.getEmail()%>"> 
    </div>
  </div>
      <button type="submit" >Edit</button>
</form>	
</div>
<br>
<%@ include file="../css/footer.jsp" %>	
</body>
</html>