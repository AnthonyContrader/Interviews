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
<div class="navbar">
  <a href="homeadmin.jsp">Home</a>
  <a class="active" href="CompanyServlet?mode=companylist">Companies</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
<br>
<div class="main">

<% CompanyDTO u = (CompanyDTO) request.getAttribute("dto");%>


<form id="floatleft" action="CompanyServlet?mode=update&id=<%=u.getId()%>" method="post">
  <div class="row">
    <div class="col-25">
      <label for="company">Name</label>
    </div>
    <div class="col-75">
      <input type="text" id="company" name="Name" value=<%=u.getName()%>>
    </div>
  </div>
  <div class="row">
    <div class="col-25">
     <label for="address">Address</label>
    </div>
    <div class="col-75">
      <input
			type="text" id="address" name="address" value=<%=u.getAddress()%>> 
    </div>
  </div>
  <div class="row">
    <div class="col-25">
      <label for="city">City</label>
    </div>
      <div class="col-75">
      <input
			type="text" id="city" name="city" value=<%=u.getCity()%>> 
      </div>
   </div>
   	
  				
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