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
	<link href="../css/vittoriostyle.css" rel="stylesheet">
	<title>Search Recruiter</title>
</head>
<body>


	<!-- HEADER -->

	<%@ include file="../css/header.jsp" %>
	
	
	<!-- NAVBAR -->

	<%@include file="../css/adminmenu.jsp"%>
	<script>document.getElementById("recruiterlink").classList.add("active");</script>


	<!-- BODY -->
	
	<div class="main">
		<h1>Search Recruiter</h1>
	    <div class="mainleft">
	    
	    
			<!-- USERLIST -->
	
			<%List<RecruiterDTO> allRecruiterList = (List<RecruiterDTO>) request.getAttribute("allRecruiterList");
			  List<CompanyDTO> allCompanyList = (List<CompanyDTO>) request.getAttribute("allCompanyList");
			%>
			<br>
			<table class="greenTable">
				<tr>
					<th>Name</th>
					<th>Company</th>
					<th></th>
					<th></th>
				</tr>
				<% for (RecruiterDTO recruiter : allRecruiterList) {%>
					<tr>
						<td><a href=/Recruiter/read?id=<%=recruiter.getId()%>><%=recruiter.getName()%></a></td>
						<td><a href=/Company/read?id=<%=recruiter.getCompany().getId()%>><%=recruiter.getCompany().getName()%></a></td>
						<td><a class="edit" href=/Recruiter/update?id=<%=recruiter.getId()%>>Edit</a></td>
						<td><a class="delete" href=/Recruiter/delete?id=<%=recruiter.getId()%>>Delete</a></td>
					</tr>
				<%}%>
			</table>
		</div>
		<div class="mainright">
		
		
			<!-- SEARCH FORM -->
		
			<form id="floatright" action="/Recruiter/search?" method="post">
				<div class="row">
				    <div class="col-25">
				    	<label for="name">Name</label>
				    </div>
				    <div class="col-75">
				    	<input type="text" id="name" name="name" placeholder="insert name">
				    </div>
				</div>
				<div class="row">
				    <div class="col-25">
				    	<label for="company">Company</label>
				    </div>
				    <div class="col-75">
						<select id="company" name="company">
							<option value="%">ALL</option>
							<%for (CompanyDTO company : allCompanyList) {%>
								<option value=<%=company.getId()%>><%=company.getName()%></option>
							<%}%>
						</select>
				    </div>
				</div>
				<button type="submit" >Search</button>
			</form>
		</div>
	</div>
	<br>

		
	<!-- FOOTER -->	
	
	<%@ include file="../css/footer.jsp" %>
</body>
</html>