<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.List"
    import="it.contrader.dto.UserDTO"
    import="it.contrader.dto.CompanyDTO"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Edit User</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>
<%@ include file="../css/adminmenu.jsp"%>
<script>document.getElementById("userlink").classList.add("active");</script>
<br>
<div class="main">

<%
	UserDTO u = (UserDTO) request.getAttribute("dto");
	List<CompanyDTO> companyList = (List<CompanyDTO>) request.getAttribute("companyList");
%>


<form id="floatleft" action="UserServlet?mode=update&id=<%=u.getId()%>" method="post">
  <div class="row">
    <div class="col-25">
      <label for="user">Username</label>
    </div>
    <div class="col-75">
      <input type="text" id="user" name="username" value=<%=u.getUsername()%>>
    </div>
  </div>
  <div class="row">
    <div class="col-25">
     <label for="pass">Password</label>
    </div>
    <div class="col-75">
      <input
			type="text" id="pass" name="password" value=<%=u.getPassword()%>> 
    </div>
  </div>
  <div class="row">
    <div class="col-25">
      <label for="type">Usertype</label>
    </div>
   		 <div class="col-75">
 			<select id="type" name="usertype">
  				<option value="ADMIN" <%if(u.getUsertype().equals("ADMIN")) {%>selected<%}%>>ADMIN</option>
  				<option value="RECRUITER" <%if(u.getUsertype().equals("RECRUITER")) {%>selected<%}%>>RECRUITER</option>
  				<option value="USER" <%if(u.getUsertype().equals("USER")) {%>selected<%}%>>USER</option>
			</select>
    	</div>
  </div>
   <div class="row">		
    <div class="col-25">
     <label for="company">Company</label>
    </div>
    <div class="col-75">
	    <select id="company" name="company">
	    	<option value=0>NO COMPANY</option>
	      	<%
				for (CompanyDTO c : companyList) {
			%>
			<option value=<%=c.getId()%> <%if(c.getName().equals(u.getCompany())) {%>selected<%}%>> <%=c.getName() %></option>
			<%
				}
			%>
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