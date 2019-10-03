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
	<title>Edit Recruiter</title>
</head>
<body>
	<!-- HEADER -->
	
	<%@ include file="../css/header.jsp" %>
	
	
	<!-- NAVBAR -->
	
	<%@ include file="../css/adminmenu.jsp"%>
	<script>document.getElementById("recruiterlink").classList.add("active");</script>
	<br>
	
	
	<!-- BODY -->
	
	<div class="main">
	
	
		<!-- RECRUITERLIST -->
		
		<%RecruiterDTO recruiter = (RecruiterDTO) request.getAttribute("recruiterDTO");
		  List<CompanyDTO> companyList = (List<CompanyDTO>) request.getAttribute("allCompanyDTO");
		%>
		<form id="floatleft" action="/Recruiter/update?update=true&id=<%=recruiter.getId()%>" method="post">
			<div class="row">
				<div class="col-25">
					<label for="name">Name</label>
				</div>
				<div class="col-75">
					<input type="text" id="name" name="name" value="<%=recruiter.getName()%>">
				</div>
			</div>
			<div class="row">
				<div class="col-25">
					<label for="company">Company</label>
				</div>
				<div class="col-75">
					<select id="company" name="company">
						<%for (CompanyDTO c : companyList) {
							 if(c.getId()==recruiter.getCompany().getId()){%>
					    		<option value=<%=c.getId()%> selected><%=c.getName()%></option>	
					    	<%}else{%>
					    		<option value=<%=c.getId()%>><%=c.getName()%></option>
					    	<%}
						}%>
					</select>
				</div>
			</div>
			<button type="submit" >Edit</button>
		</form>
	</div>
	<br>
	
	
	<!-- FOOTER -->	
	
	<%@ include file="../css/footer.jsp" %>	
</body>
</html>