<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.UserDTO"
	import="it.contrader.dto.CompanyDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>User Manager</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>
<%@ include file="../css/adminmenu.jsp"%>
<script>document.getElementById("userlink").classList.add("active");</script>
<div class="main">
	<%
		List<UserDTO> list = (List<UserDTO>) request.getAttribute("list");
		List<CompanyDTO> companyList = (List<CompanyDTO>) request.getAttribute("companyList");
	%>

<br>

	<table>
		<tr>
			<th>Username</th>
			<th>Password</th>
			<th>Usertype</th>
			<th>Company</th>
			<th></th>
			<th></th>
		</tr>
		<%
			for (UserDTO u : list) {
		%>
		<tr>
			<td><a href=UserServlet?mode=read&id=<%=u.getId()%>>
					<%=u.getUsername()%>
			</a></td>
			<td><%=u.getPassword()%></td>
			<td><%=u.getUsertype()%></td>
			<td><%=u.getCompany()%></td>
			<td><a href=UserServlet?mode=read&update=true&id=<%=u.getId()%>>Edit</a>
			</td>
			<td><a href=UserServlet?mode=delete&id=<%=u.getId()%>>Delete</a>
			</td>

		</tr>
		<%
			}
		%>
	</table>



<form id="floatright" action="UserServlet?mode=insert" method="post">
  <div class="row">
    <div class="col-25">
      <label for="user">Username</label>
    </div>
    <div class="col-75">
      <input type="text" id="user" name="username" placeholder="inserisci username">
    </div>
  </div>
  
  <div class="row">
    <div class="col-25">
     <label for="pass">Password</label>
    </div>
    <div class="col-75">
      <input type="text" id="pass" name="password" placeholder="inserisci password"> 
    </div>
  </div>
  <div class="row">
  
    <div class="col-25">
      <label for="type">Usertype</label>
    </div>
   		 <div class="col-75">
 			<select id="type" name="usertype" onchange="changedCompany()">
  				<option value="ADMIN">ADMIN</option>
  				<option value="USER">USER</option>
  				<option value="RECRUITER">RECRUITER</option>
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
				for (CompanyDTO c : companyList) {
			%>
			<option value=<%=c.getId() + ":" + c.getName()%>><%=c.getName() %></option>
			<%
				}
			%>
		</select>
    </div>
  </div>
  <button type="submit" >Insert</button>
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