<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.List"
    import="it.contrader.dto.QuestionDTO"
    import="it.contrader.dto.CompanyDTO"
    import="it.contrader.dto.CompanyNameAndIdDTO"
	import="it.contrader.dto.UserDTO"
	import="it.contrader.dto.RecruiterDTO"
	import="it.contrader.dto.RecruiterNameAndIdDTO"
%>
<!DOCTYPE html>
<html>
<head>
<link href="/css/vittoriostyle.css" rel="stylesheet">
<title>Search Question</title>
</head>
<body>
	<!-- HEADER -->

	<%@ include file="/css/header.jsp" %>
	
	
	<!-- NAVBAR -->

	<% UserDTO user = (UserDTO)session.getAttribute("user");
		if(user.getUserType().equals("ADMIN")) {%>
			<%@include file="/css/adminmenu.jsp"%><%}
		else {%>
			<%@include file="/css/usermenu.jsp"%>
	<%}%>
	<script>document.getElementById("questionlink").classList.add("active");</script>


	<!-- BODY -->
	
	<div class="main">
		<h1>Search Question</h1>
		<div class="mainleft">
			<!-- QUESTIONLIST -->
	
			<%List<QuestionDTO> questionResultList = (List<QuestionDTO>) request.getAttribute("questionResultList");
			  List<RecruiterNameAndIdDTO> recruiterList = (List<RecruiterNameAndIdDTO>) request.getAttribute("allRecruiterDTO");
			  List<CompanyNameAndIdDTO> companyList = (List<CompanyNameAndIdDTO>) request.getAttribute("allCompanyDTO");
			  List<String> sectorDistinctList = (List<String>) request.getAttribute("sectorDistinctList");
			  /* List<CompanyNameDTO> companyAllList = (List<CompanyNameDTO>) request.getAttribute("companyAllList");
			   List<UserRecruiterDTO> recruiterAllList = (List<UserRecruiterDTO>) request.getAttribute("recruiterAllList");		   
			   CompanyDTO company = (CompanyDTO) request.getAttribute("company");*/
			%>
			<br>
			<table class="greenTable">
				<tr>
					<th>Question</th>
					<th>Topic</th>
					<th>Sector</th>
					<th>Recruiter</th>
					<th>Company</th>
					<th></th>
					<th></th>
				</tr>
				<% for (QuestionDTO q : questionResultList) {%>
					<tr>
						<td><a href=/Question/read?id=<%=q.getId()%>><%=q.getQuestion()%></a></td>
						<td><%=q.getTopic()%></td>
						<td><%=q.getSector()%></td>
						<td><a href=/Recruiter/read?id=<%=q.getRecruiter().getId()%>><%=q.getRecruiter().getName()%></a></td>
						<td><a href=/Company/read?id=<%=q.getCompany().getId()%>><%=q.getCompany().getName()%></a></td>
						<%if (user.getUserType().equals("ADMIN")){%>
							<td><a class="edit" href="/Question/update?id=<%=q.getId()%>">Edit</a>
							</td>
							<td><a class="delete" href="/Question/delete?id=<%=q.getId()%>">Delete</a>
							</td>
						<%}else{%>
							<td><a class="disabled">Edit</a>
							</td>
							<td><a class="disabled">Delete</a>
							</td>
						<%}%>
					</tr>
				<%}%>
			</table>
		</div>
		<div class="mainright">
		
		
			<!-- SEARCH FORM -->
		
			<form id="floatright" action="/Question/search" method="post">
				<div class="row">
				    <div class="col-25">
				    	<label for="text">Word</label>
				    </div>
				    <div class="col-75">
				    	<input type="text" id="text" name="text" placeholder="insert a word">
				    </div>
				</div>
				<div class="row">
				    <div class="col-25">
				    	<label for="topic">Topic</label>
				    </div>
				    <div class="col-75">
				    	<input type="text" id="topic" name="topic" placeholder="insert a topic">
				    </div>
				</div>
				<div class="row">
				    <div class="col-25">
				    	<label for="sector">Sector</label>
				    </div>
				    <div class="col-75">
				    	<select id="sector" name="sector">
				    		<option value="%">ALL</option>
				    		<%for (String sector : sectorDistinctList) {%>
				    			<option value="<%=sector%>"><%=sector%></option>
				    		<%}%>
				    	</select>
				    </div>
				</div>
				<div class="row">
				    <div class="col-25">
				    	<label for="recruiter">Recruiter</label>
				    </div>
				    <div class="col-75">
				    	<select id="recruiter" name="recruiterId">
				    		<option value="%">ALL</option>
				    		<%for (RecruiterNameAndIdDTO recruiter : recruiterList) {%>
				    			<option value="<%=recruiter.getId()%>"><%=recruiter.getName()%></option>	
				    		<%}%>
				    	</select>
				    </div>
				</div>
				<div class="row">
				    <div class="col-25">
				    	<label for="company">Company</label>
				    </div>
				    <div class="col-75">
				    	<select id="company" name="companyId">
				    		<option value="%">ALL</option>
				    		<%for (CompanyNameAndIdDTO company : companyList) {%>
				    			<option value="<%=company.getId()%>"><%=company.getName()%></option>	
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
	
	<%@ include file="/css/footer.jsp" %>
</body>
</html>