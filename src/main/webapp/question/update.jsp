<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="it.contrader.dto.QuestionDTO" 
    import="it.contrader.dto.RecruiterDTO"
    import="it.contrader.dto.RecruiterNameAndIdDTO"
    import="it.contrader.dto.UserDTO"
    import="java.util.List"
%>
        
    
<!DOCTYPE html>
<html>
<head>
	<link href="/css/vittoriostyle.css" rel="stylesheet">
	<title>Edit Question</title>
	
	
	<!-- SCRIPT -->
	
	<script type="text/javascript">
		function CheckTopics(val){
			var topicSelecter=document.getElementById('selecter');
			var inputText=document.getElementById('inputText');
			if(val=='#') {
				topicSelecter.style.display='none;'
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
	
	<%List<RecruiterNameAndIdDTO> allRecruiterList = (List<RecruiterNameAndIdDTO>) request.getAttribute("allRecruiterList");
	  List<String> topicDistinctList = (List<String>) request.getAttribute("topicDistinctList");
	%>
	
	
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
		
		<% QuestionDTO question = (QuestionDTO) request.getAttribute("question");%>
		<form id="floatleft" action="/Question/update?id=<%=question.getId()%>" method="post">
			<div class="row">
			    <div class="col-25">
			    	<label for="question">Question</label>
			    </div>
			    <div class="col-75">
			    	<input type="text" id="question" name="question" value="<%=question.getQuestion()%>" required>
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
			    		<%for (RecruiterNameAndIdDTO recruiter : allRecruiterList) {
			    		    if(recruiter.getId()==question.getRecruiter().getId()){%>
			    				<option value=<%=recruiter.getId()%> selected><%=recruiter.getName()%></option>	
			    			<%}else{%>
			    				<option value=<%=recruiter.getId()%>><%=recruiter.getName()%></option>
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