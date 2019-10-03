<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.List"
    import="it.contrader.dto.QuestionDTO"
    import="it.contrader.dto.CompanyDTO"
	import="it.contrader.dto.UserDTO"
	import="it.contrader.dto.RecruiterDTO"
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
		<h1>Cerca Domanda</h1>
		<div class="mainleft">
			<!-- QUESTIONLIST -->
	
			<%List<QuestionDTO> questionResultList = (List<QuestionDTO>) request.getAttribute("questionResultList");
			  List<RecruiterDTO> recruiterList = (List<RecruiterDTO>) request.getAttribute("allRecruiterDTO");
			  List<CompanyDTO> companyList = (List<CompanyDTO>) request.getAttribute("allCompanyDTO");
			  List<String> sectorDistinctList = (List<String>) request.getAttribute("sectorDistinctList");
			  /* List<CompanyNameDTO> companyAllList = (List<CompanyNameDTO>) request.getAttribute("companyAllList");
			   List<UserRecruiterDTO> recruiterAllList = (List<UserRecruiterDTO>) request.getAttribute("recruiterAllList");		   
			   CompanyDTO company = (CompanyDTO) request.getAttribute("company");*/
			%>
			<br>
			<table class="greenTable">
				<tr>
					<th>Question</th>
					<th>Argument</th>
					<th>Sector</th>
					<th>Recruiter</th>
					<th>Company</th>
					<th></th>
					<th></th>
				</tr>
				<% for (QuestionDTO q : questionResultList) {%>
					<tr>
						<td><a href=/Question/read?id=<%=q.getId()%>>
								<%=q.getQuestion()%></a></td>
						<td><%=q.getArgument()%></td>
						<td><%=q.getSector()%></td>
						<td><%=q.getRecruiter().getName()%></td>
						<td><%=q.getCompany().getName()%></td>
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
				    	<label for="text">Testo</label>
				    </div>
				    <div class="col-75">
				    	<input type="text" id="text" name="text" placeholder="inserisci una parola">
				    </div>
				</div>
				<div class="row">
				    <div class="col-25">
				    	<label for="argument">Argomento</label>
				    </div>
				    <div class="col-75">
				    	<input type="text" id="argument" name="argument" placeholder="inserisci l'argomento">
				    </div>
				</div>
				<div class="row">
				    <div class="col-25">
				    	<label for="sector">Settore</label>
				    </div>
				    <div class="col-75">
				    	<select id="sector" name="sector">
				    		<option value="%">TUTTI</option>
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
				    		<option value="%">TUTTI</option>
				    		<%for (RecruiterDTO recruiter : recruiterList) {%>
				    			<option value="<%=recruiter.getId()%>"><%=recruiter.getName()%></option>	
				    		<%}%>
				    	</select>
				    </div>
				</div>
				<div class="row">
				    <div class="col-25">
				    	<label for="company">Azienda</label>
				    </div>
				    <div class="col-75">
				    	<select id="company" name="companyId">
				    		<option value="%">TUTTE</option>
				    		<%for (CompanyDTO company : companyList) {%>
				    			<option value="<%=company.getId()%>"><%=company.getName()%></option>	
				    		<%}%>
				    	</select>
				    </div>
				</div>
				<button type="submit" >Cerca</button>
			</form>
		</div>
	</div>
	<br>

		
	<!-- FOOTER -->	
	
	<%@ include file="/css/footer.jsp" %>
</body>
</html>