<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.List"
    import="it.contrader.dto.QuestionDTO"
    import="it.contrader.dto.CompanyDTO"
    import="it.contrader.dto.CompanyNameDTO"
    import="it.contrader.dto.CompanySectorDistinctDTO"
	import="it.contrader.dto.UserDTO"
	import="it.contrader.dto.UserRecruiterDTO"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="css/vittoriostyle.css" rel="stylesheet">
<title>Search Question</title>
</head>
<body>
	<!-- HEADER -->

	<%@ include file="../css/header.jsp" %>
	
	
	<!-- NAVBAR -->

	<% UserDTO user = (UserDTO)session.getAttribute("user");
		if(user.getUsertype().equals("ADMIN")) {%>
			<%@include file="../css/adminmenu.jsp"%><%}
		else {%>
			<%@include file="../css/usermenu.jsp"%>
	<%}%>
	<script>document.getElementById("questionlink").classList.add("active");</script>


	<!-- BODY -->
	
	<div class="main">
		<h1>Cerca Domanda</h1>
		<div class="mainleft">
			<!-- QUESTIONLIST -->
	
			<% List<QuestionDTO> questionResultList = (List<QuestionDTO>) request.getAttribute("questionResultList");
			   List<CompanySectorDistinctDTO> sectorDistinctAllList = (List<CompanySectorDistinctDTO>) request.getAttribute("sectorDistinctAllList");
			   List<CompanyNameDTO> companyAllList = (List<CompanyNameDTO>) request.getAttribute("companyAllList");
			   List<UserRecruiterDTO> recruiterAllList = (List<UserRecruiterDTO>) request.getAttribute("recruiterAllList");		   
		//	   CompanyDTO company = (CompanyDTO) request.getAttribute("company");
			%>
			<br>
			<table class="greenTable">
				<tr>
					<th>Question</th>
					<th>Sector</th>
					<th>Recruiter</th>
					<th>Company</th>
					<th></th>
					<th></th>
				</tr>
				<% for (QuestionDTO q : questionResultList) {%>
					<tr>
						<td><a href=QuestionServlet?mode=read&id=<%=q.getId()%>>
							<%=q.getQuestion()%></a></td>
						<td><%=q.getSector()%></td>
						<td><%=q.getRecruiter()%></td>
						<td><%=q.getCompany()%></td>
						<%if(q.getRecruiterid()==user.getId()||user.getUsertype().equals("ADMIN")){%>
							<td><a class="edit" href=QuestionServlet?mode=read&update=true&id=<%=q.getId()%>>
								Edit</a></td>
							<td><a class="delete" href=QuestionServlet?mode=delete&id=<%=q.getId()%>>
								Delete</a></td>
						<%}%>
					</tr>
				<%}%>
			</table>
		</div>
		<div class="mainright">
			<!-- SEARCH FORM -->
		
			<form id="floatright" action="QuestionServlet?mode=search&search=true" method="post">
				<div class="row">
				    <div class="col-25">
				    	<label for="word">Parola</label>
				    </div>
				    <div class="col-75">
				    	<input type="text" id="word" name="word" placeholder="inserisci una parola">
				    </div>
				</div>
				<div class="row">
				    <div class="col-25">
				    	<label for="sector">Settore</label>
				    </div>
				    <div class="col-75">
				    	<select id="sector" name="sector">
				    		<option value="%">TUTTI</option>
				    		<% for (CompanySectorDistinctDTO sector : sectorDistinctAllList) {%>
				    			<option value="<%=sector.getSector()%>"><%=sector.getSector()%></option>
				    		<%}%>
				    	</select>
				    </div>
				</div>
				<div class="row">
				    <div class="col-25">
				    	<label for="recruiter">Recruiter</label>
				    </div>
				    <div class="col-75">
				    	<select id="recruiter" name="recruiterid">
				    		<option value="%">TUTTI</option>
				    		<%for (UserRecruiterDTO recruiter : recruiterAllList) {%>
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
				    	<select id="company" name="companyid">
				    		<option value="%">TUTTE</option>
				    		<%for (CompanyNameDTO company : companyAllList) {
				    		%>
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
	
	<%@ include file="../css/footer.jsp" %>
</body>
</html>