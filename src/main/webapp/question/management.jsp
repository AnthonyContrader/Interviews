<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
	import="java.util.List"
	import="it.contrader.dto.QuestionDTO"
	import="it.contrader.dto.UserDTO"
	import="it.contrader.dto.RecruiterDTO"
%>
<!DOCTYPE html>
<html>
<head>
<link href="/css/vittoriostyle.css" rel="stylesheet">
<title>Question Manager</title>
</head>
<body>
	<!-- HEADER -->

	<%@ include file="/css/header.jsp" %>
	
	<%
		List<QuestionDTO> listQuestion = (List<QuestionDTO>) request.getAttribute("allQuestionDTO");
		List<RecruiterDTO> listRecruiter = (List<RecruiterDTO>) request.getAttribute("allRecruiterDTO");
	%>
	<!-- NAVBAR -->

	<%UserDTO user = (UserDTO)session.getAttribute("user");
	  if(user.getUserType().equals("ADMIN")) {%>
	  	<%@include file="/css/adminmenu.jsp"%><%}
	  else {%>
		<%@include file="/css/usermenu.jsp"%>
	<%}%>
	<script>document.getElementById("questionlink").classList.add("active");</script>

	<!-- BODY -->
	
	<div class="main">
		<h1>Questions</h1>
	    <div class="mainleft">
			<!-- QUESTIONLIST -->
			
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
				<% for (QuestionDTO q : listQuestion) {%>
					<tr>
						<td><a href=/Question/read?id=<%=q.getId()%>>
								<%=q.getQuestion()%>
						</a></td>
						<td><%=q.getArgument()%></td>
						<td><%=q.getSector()%></td>
						<td><%=q.getRecruiter().getName()%></td>
						<td><%=q.getCompany().getName()%></td>
						<%if (q.getRecruiter().getId()==user.getId() || user.getUserType().equals("ADMIN")){%>
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
		
			<!-- SEARCH BUTTON -->
		
			<form action="/Question/search" method="get">
				<button type="submit" ><i class="searchicon"></i>Cerca</button>
			</form>
			
			
			<!-- INSERT FORM -->
			
			<form action="/Question/create" method="post">
				<div class="row">
				    <div class="col-25">
				    	<label for="question">Question</label>
				    </div>
				    <div class="col-75">
				    	<input type="text" id="question" name="question" placeholder="inserisci la domanda" required>
				    </div>    
				</div>
				<div class="row">
				    <div class="col-25">
				    	<label for="argument">Argument</label>
				    </div>
				    <div class="col-75">
				    	<input type="text" id="argument" name="argument" placeholder="inserisci l'argomento" required>
				    </div> 
				</div>
				<div class="row">
				    <div class="col-25">
				    	<label for="recruiter">Recruiter</label>
				    </div>
				    <div class="col-75">
						<select id="recruiter" name="recruiter" onChange="getCompany()">
				    		<%for (RecruiterDTO r : listRecruiter) {%>
				    			<option value=<%=r.getId()%>><%=r.getName()%></option>	
				    		<%}%>
						</select>
					</div> 
				</div>
				<button type="submit" >Insert</button>
			</form>
		</div>
	</div>
			
	<!-- FOOTER -->	
	
	<%@ include file="../css/footer.jsp" %>
</body>
</html>