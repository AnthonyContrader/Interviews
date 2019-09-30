<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.CompanyDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Edit Company</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>
<%@ include file="../css/adminmenu.jsp"%>
<script>document.getElementById("companylink").classList.add("active");</script>
<br>
<div class="main">

<% CompanyDTO c = (CompanyDTO) request.getAttribute("dto");%>


<form id="floatleft" action="CompanyServlet?mode=update&id=<%=c.getId()%>" method="post">
	<div class="row">
		<div class="col-25">
			<label for="company">Name</label>
		</div>
    	<div class="col-75">
     		<input type="text" id="company" name="Name" value="<%=c.getName()%>">
    	</div>
	</div>
	<div class="row">
		<div class="col-25">
     		<label for="address">Address</label>
    	</div>
    	<div class="col-75">
			<input type="text" id="address" name="address" value="<%=c.getAddress()%>"> 
		</div>
	</div>
	<div class="row">
		<div class="col-25">
      		<label for="city">City</label>
   		</div>
		<div class="col-75">
      		<input type="text" id="city" name="city" value="<%=c.getCity()%>"> 
		</div>
   </div>
   <div class="row">
		<div class="col-25">
      		<label for="sector">Sector</label>
   		</div>
		<div class="col-75">
      		<select id="sector" name="sector">
      			<option value="Informatica"<%if (c.getSector().equals("Informatica")) {%>selected<%} %>>Informatica</option>
      			<option value="Economia"<%if(c.getSector().equals("Economia")) { %>selected<%} %>>Economia</option>
      			<option value="Altro"<%if(c.getSector().equals("Altro")) { %>selected<%} %>>Altro</option>
      		</select> 
		</div>
   </div>
      <button type="submit" >Edit</button>
</form>

	
</div>
<br>
<%@ include file="../css/footer.jsp" %>	
</body>
</html>