<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.List"
	import="it.contrader.dto.RecruiterDTO"
	import="it.contrader.dto.CompanyDTO"
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link href="/css/vittoriostyle.css" rel="stylesheet">
	<title>Recruiter Manager</title>
</head>
<body>


	<!-- HEADER -->
	
	<%@include file="../css/header.jsp"%>
	
	
	<!-- NAVBAR -->
	
	<%@include file="../css/adminmenu.jsp"%>
	<script>document.getElementById("recruiterlink").classList.add("active");</script>
	
	
	<!-- BODY -->
	
	<div class="main">
		<h1>Recruiters</h1>
	    <div class="mainleft">
	    
	    
			<!-- RECRUITERLIST -->
			
			<%List<RecruiterDTO> recruiterList = (List<RecruiterDTO>) request.getAttribute("allRecruiterDTO");
			  List<CompanyDTO> companyList = (List<CompanyDTO>) request.getAttribute("allCompanyDTO");
			%>
			<br>
			<table class="greenTable">
				<tr>
					<th>Name</th>
					<th>Company</th>
					<th></th>
					<th></th>
				</tr>
			<%for (RecruiterDTO r : recruiterList) {%>
				<tr>
					<td><a href=/Recruiter/read?id=<%=r.getId()%>><%=r.getName()%></a></td>
					<td><a href=/Company/read?id=<%=r.getCompany().getId()%>><%=r.getCompany().getName()%></a></td>
					<td><a class="edit" href=/Recruiter/update?update=false&id=<%=r.getId()%>>Edit</a></td>
					<td><a class="delete" href=/Recruiter/delete?id=<%=r.getId()%>>Delete</a></td>
				</tr>
			<%}%>
			</table>
	    </div>
		<div class="mainright">
		
		
	 		<!-- SEARCH BUTTON -->   
		
			<form action="/Recruiter/search" method="get">
				<button type="submit" ><i class="searchicon"></i>Search</button>
			</form>
	
	
			<!-- INSERT FORM -->
		
			<form action="/Recruiter/create" method="post">
				<div class="row">
					<div class="col-25">
						<label for="name">Name</label>
					</div>
					<div class="col-75">
						<input type="text" id="name" name="name" placeholder="insert name" required>
					</div>
				</div>
				<div class="row">
					<div class="col-25">
						<label for="company">Company</label>
					</div>
					<div class="col-75">
						<select id="company" name="company">
							<%for (CompanyDTO c : companyList) {%>
								<option value=<%=c.getId()%>><%=c.getName()%></option>
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
</html>