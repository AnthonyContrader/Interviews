<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="it.contrader.dto.QuestionDTO" 
    import="it.contrader.dto.RecruiterDTO"
    import="it.contrader.dto.UserDTO"
    import="java.util.List"
%>
        
    
<!DOCTYPE html>
<html>
<head>
	<link href="/css/vittoriostyle.css" rel="stylesheet">
	<title>Edit Question</title>
</head>
<body>
	<!-- HEADER -->
	
	<%@ include file="/css/header.jsp" %>
	
	<% List<RecruiterDTO> listRecruiter = (List<RecruiterDTO>) request.getAttribute("allRecruiterDTO");	%>
	
	<!-- NAVBAR -->
	
	<%if(((UserDTO) session.getAttribute("user")).getUserType().equals("ADMIN")) {%>
		<%@include file="/css/adminmenu.jsp"%><%}
	else {%>
		<%@include file="/css/usermenu.jsp"%><%}%>
	<script>document.getElementById("questionlink").classList.add("active");</script>
	<br>
	
	
	<!-- BODY -->
	
	<div class="main">
	
	
		<!-- UPDATE FORM -->
		
		<% QuestionDTO q = (QuestionDTO) request.getAttribute("questionDTO");%>
		<form id="floatleft" action="/Question/update?id=<%=q.getId()%>" method="post">
			<div class="row">
			    <div class="col-25">
			    	<label for="question">Question</label>
			    </div>
			    <div class="col-75">
			    	<input type="text" id="question" name="question" value="<%=q.getQuestion()%>" required>
			    </div>    
			</div>
			<div class="row">
			    <div class="col-25">
			    	<label for="argument">Topic</label>
			    </div>
			    <div class="col-75">
			    	<input type="text" id="argument" name="argument" value="<%=q.getArgument()%>" required>
			    </div> 
			</div>
			<div class="row">
			    <div class="col-25">
			    	<label for="recruiter">Recruiter</label>
			    </div>
			    <div class="col-75">
					<select id="recruiter" name="recruiter">
			    		<%for (RecruiterDTO r : listRecruiter) {
			    		    if(r.getId()==q.getRecruiter().getId()){%>
			    				<option value=<%=r.getId()%> selected><%=r.getName()%></option>	
			    			<%}else{%>
			    				<option value=<%=r.getId()%>><%=r.getName()%></option>
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