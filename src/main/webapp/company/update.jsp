<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="it.contrader.dto.CompanyDTO"%>
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
<script>document.getElementById("companylink").classList.add("active");</script>
<br>
<div class="main">

<% CompanyDTO c = (CompanyDTO) request.getAttribute("companyDTO");%>


<form id="floatleft" action="/Company/update?update=true&id=<%=c.getId()%>" method="post">
	<div class="row">
		<div class="col-25">
			<label for="name">Name</label>
		</div>
    	<div class="col-75">
     		<input type="text" id="name" name="name" value="<%=c.getName()%>">
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
      		<input type="text" id="sector" name="sector" value="<%=c.getSector()%>"> 
		</div>
   </div>
   
   </div>
      <button type="submit" >Edit</button>
</form>

	
</div>
<br>
<%@ include file="../css/footer.jsp" %>	
</body>
</html>