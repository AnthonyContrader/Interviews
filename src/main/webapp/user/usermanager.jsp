<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.UserDTO"
	import="it.contrader.dto.UserTypeDistinctDTO"
	
	import="it.contrader.dto.CompanyNameDTO"
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link href="css/vittoriostyle.css" rel="stylesheet">
	<title>User Manager</title>
</head>
<body>
	<!-- HEADER -->
	
	<%@include file="../css/header.jsp"%>
	
	
	<!-- NAVBAR -->
	
	<%@include file="../css/adminmenu.jsp"%>
	<script>document.getElementById("userlink").classList.add("active");</script>
	
	<!-- BODY -->
	
	<div class="main">
		<h1>Users</h1>
	    <div class="mainleft">
			<!-- QUESTIONLIST -->
			
			<%List<UserDTO> list = (List<UserDTO>) request.getAttribute("list");
			  List<UserTypeDistinctDTO> usertypeDistinctAllList = (List<UserTypeDistinctDTO>) request.getAttribute("usertypeDistinctAllList");
		//	  List<CompanyDTO> companyList = (List<CompanyDTO>) request.getAttribute("companyList");
			  List<CompanyNameDTO> companyAllList = (List<CompanyNameDTO>) request.getAttribute("companyAllList");
			%>
			<br>
			<table class="greenTable">
				<tr>
					<th>Username</th>
					<th>Password</th>
					<th>Usertype</th>
					<th>Company</th>
					<th></th>
					<th></th>
				</tr>
			<%for (UserDTO u : list) {%>
				<tr>
					<td><a href=UserServlet?mode=read&id=<%=u.getId()%>><%=u.getUsername()%></a></td>
					<td><%=u.getPassword()%></td>
					<td><%=u.getUsertype()%></td>
					<td><%=u.getCompany()%></td>
					<td><a class="edit" href=UserServlet?mode=read&update=true&id=<%=u.getId()%>>Edit</a></td>
					<td><a class="delete" href=UserServlet?mode=delete&id=<%=u.getId()%>>Delete</a></td>
				</tr>
			<%}%>
			</table>
	    </div>
		<div class="mainright">
	 		<!-- SEARCH BUTTON -->   
		
			<form action="UserServlet?mode=search" method="post">
				<button type="submit" ><i class="searchicon"></i>Cerca</button>
			</form>
	
	
			<!-- INSERT FORM -->
		
			<form action="UserServlet?mode=insert" method="post">
				<div class="row">
					<div class="col-25">
						<label for="user">Username</label>
					</div>
					<div class="col-75">
						<input type="text" id="user" name="username" placeholder="inserisci username" required>
					</div>
				</div>
				<div class="row">
					<div class="col-25">
						<label for="pass">Password</label>
					</div>
					<div class="col-75">
						<input type="text" id="pass" name="password" placeholder="inserisci password" required> 
					</div>
				</div>
				<div class="row">
					<div class="col-25">
						<label for="type">Usertype</label>
					</div>
					<div class="col-75">
						<select id="type" name="usertype" onchange="changedUsertype()">
				    		<%for (UserTypeDistinctDTO recruiter : usertypeDistinctAllList) {%>
				    			<option value="<%=recruiter.getUsertype()%>"><%=recruiter.getUsertype()%></option>	
				    		<%}%>
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
			      	<%for (CompanyNameDTO c : companyAllList) {%>
		<option value="<%=c.getId() + ":" + c.getName()%>"><%=c.getName()%></option> <!-- HO MESSO LE QUOTES -->
		<%}%>
				</select>
		    </div>
		  </div>
		  <button type="submit" >Insert</button>
			</form>
		</div>
	</div>
	<br>
	<%@ include file="../css/footer.jsp" %>
</body>
<script>  
function changedUsertype(){	
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