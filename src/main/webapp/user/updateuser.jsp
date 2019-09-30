<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.List"
    import="it.contrader.dto.UserDTO"
    import="it.contrader.dto.CompanyNameDTO"
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
	List<CompanyNameDTO> companyAllList = (List<CompanyNameDTO>) request.getAttribute("companyAllList");
%>


<form id="floatleft" action="UserServlet?mode=update&id=<%=u.getId()%>" method="post">
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
      <label for="type">Usertype</label>
    </div>
   		 <div class="col-75">
 			<select id="type" name="usertype" onchange="changedCompany()">
  				<option value="ADMIN" <%if(u.getUsertype().equals("ADMIN")) {%>selected<%}%>>ADMIN</option>
  				<option value="RECRUITER" <%if(u.getUsertype().equals("RECRUITER")) {%>selected<%}%>>RECRUITER</option>
  				<option value="USER" <%if(u.getUsertype().equals("USER")) {%>selected<%}%>>USER</option>
			</select>
    	</div>
  </div>
   <div class="row" id="selcompany">		
    <div class="col-25">
     <label for="company">Company</label>
    </div>
    <div class="col-75">
	    <select id="company" name="company">
	    	<option value="0:null">NO COMPANY</option>
	      	<%
				for (CompanyNameDTO c : companyAllList) {
			%>
			<option value=<%=c.getId() + ":" + c.getName()%> <%if(c.getName().equals(u.getCompany())) {%>selected<%}%>> <%=c.getName() %></option>
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
<script>  
var e = document.getElementById("type");
var str = e.options[e.selectedIndex].value;
if ( str != "RECRUITER" && str != "ADMIN"){
	document.getElementById("selcompany").style.display = "none";
	document.getElementById("company").selectedIndex = 0;
}
function changedCompany(){	
	var e = document.getElementById("type");
	var str = e.options[e.selectedIndex].value;
	if ( str == "RECRUITER" || str == "ADMIN"){
		document.getElementById("selcompany").style.display = "";
	}
	else{
		document.getElementById("selcompany").style.display = "none";
		document.getElementById("company").selectedIndex = 0;
	}
}
</script>
</html>