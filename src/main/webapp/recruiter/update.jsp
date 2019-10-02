<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.List"
    import="it.contrader.dto.RecruiterDTO"
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link href="../css/vittoriostyle.css" rel="stylesheet">
	<title>Update page</title>
</head>
<body>
	<%@ include file="../css/header.jsp" %>
	<%@ include file="../css/adminmenu.jsp"%>
	<script>document.getElementById("recruiterlink").classList.add("active");</script>
	<br>
	<div class="main">
	<%RecruiterDTO r = (RecruiterDTO) request.getAttribute("recruiterDTO");%>
	
	
	<form id="floatleft" action="/Recruiter/update?update=true&id=<%=r.getId()%>" method="post">
	  <div class="row">
	    <div class="col-25">
	      <label for="name">Nome</label>
	    </div>
	    <div class="col-75">
	      <input type="text" id="name" name="name" value="<%=r.getName()%>">
	    </div>
	  </div>
	  <div class="row">
	    <div class="col-25">
	     <label for="company">Azienda</label>
	    </div>
	    <div class="col-75">
	      <input
				type="text" id="company" name="company" value="<%=r.getCompany().getName()%>"> 
	    </div>
	  </div>
	      <button type="submit" >Edit</button>
	</form>
	</div>
	<br>
	<%@ include file="../css/footer.jsp" %>	
</body>
</html>