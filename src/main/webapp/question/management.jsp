<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
	import="java.util.List"
	import="it.contrader.dto.QuestionDTO"
	import="it.contrader.dto.UserDTO"
	import="it.contrader.dto.RecruiterDTO"
	import="it.contrader.dto.RecruiterNameAndIdDTO"
%>
<!DOCTYPE html>
<html>
<head>
	<link href="/css/vittoriostyle.css" rel="stylesheet">
	<title>Question Manager</title>
	
	
	<!-- SCRIPT -->
	
	<script type="text/javascript">
	function CheckTopics(val){
		var topicSelecter=document.getElementById('selecter');
		var inputText=document.getElementById('inputText');
		if(val=='#') {
			topicSelecter.style.display='none';
			inputText.style.display='block';
			inputText.required = true;
		} else {
			inputText.style.display='none';
		}
	}
	</script>
</head>
<body>
	<!-- HEADER -->

	<%@ include file="/css/header.jsp" %>
	
	<%
		List<QuestionDTO> allQuestionList = (List<QuestionDTO>) request.getAttribute("allQuestionList");
		List<RecruiterNameAndIdDTO> allRecruiterList = (List<RecruiterNameAndIdDTO>) request.getAttribute("allRecruiterList");
		List<String> topicDistinctList = (List<String>) request.getAttribute("topicDistinctList");
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
	    <br>
	    
			<!-- QUESTIONLIST -->
			
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
				<% for (QuestionDTO question : allQuestionList) {%>
					<tr>
						<td><a href=/Question/read?id=<%=question.getId()%>><%=question.getQuestion()%></a></td>
						<td><%=question.getTopic()%></td>
						<td><%=question.getSector()%></td>
						<td><a href=/Recruiter/read?id=<%=question.getRecruiter().getId()%>><%=question.getRecruiter().getName()%></a></td>
						<td><a href=/Company/read?id=<%=question.getCompany().getId()%>><%=question.getCompany().getName()%></a></td>
						<%if (user.getUserType().equals("ADMIN")){%>
							<td><a class="edit" href="/Question/update?id=<%=question.getId()%>">Edit</a>
							</td>
							<td><a class="delete" href="/Question/delete?id=<%=question.getId()%>">Delete</a>
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
				<button type="submit" ><i class="searchicon"></i>Search</button>
			</form>
			
			
			<!-- INSERT FORM -->
			
			<form action="/Question/create" method="post">
				<div class="row">
				    <div class="col-25">
				    	<label for="question">Question</label>
				    </div>
				    <div class="col-75">
				    	<input type="text" id="question" name="question" placeholder="insert a question" required>
				    </div>    
				</div>
				<div id="selecter" class="row">
					<div class="col-25">
						<label for="topic">Topic</label>
					</div>
					<div class="col-75">
						<select id="topic" name="topic" onchange='CheckTopics(this.value);'>
							<option>choose a topic</option>
							<%for (String topic : topicDistinctList) {%>
								<option value=<%=topic%>><%=topic%></option>
							<%}%>
							<option value="#">Others...</option>
						</select>
					</div>
				</div>
				<div id="inputText" class="row" style='display:none;'>
					<div class="col-25">
						<label for="otherTopic" id="topicLabel">Topic</label>
					</div>
					<div class="col-75">
						<input type="text" id="otherTopic" name="otherTopic" placeholder="insert topic"/>
					</div>
				</div>
				<div class="row">
				    <div class="col-25">
				    	<label for="recruiter">Recruiter</label>
				    </div>
				    <div class="col-75">
						<select id="recruiter" name="recruiter">
				    		<%for (RecruiterNameAndIdDTO recruiter : allRecruiterList) {%>
				    			<option value=<%=recruiter.getId()%>><%=recruiter.getName()%></option>	
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