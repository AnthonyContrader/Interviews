<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" 
    import="it.contrader.dto.QuestionDTO" 
    import="it.contrader.dto.UserDTO"
    import="java.util.List"
    import="it.contrader.dto.CompanyDTO" %>
        
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Edit Question</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>
<%if(((UserDTO) session.getAttribute("user")).getUsertype().equals("ADMIN")) {%>
	<%@include file="../css/adminmenu.jsp"%><%}
else {%>
	<%@include file="../css/usermenu.jsp"%><%}%>
<script>document.getElementById("questionlink").classList.add("active");</script>
<br>
<div class="main">

<% QuestionDTO q = (QuestionDTO) request.getAttribute("dto");
%>



<form id="floatleft" action="QuestionServlet?mode=update&id=<%=q.getId()%>&recruiterid=<%=q.getRecruiterid()%>&companyid=<%=q.getCompanyid()%>" method="post">
	<div class="row">
		<div class="col-25">
			<label for="question">Question</label>
		</div>
    	<div class="col-75">
     		<input type="text" id="question" name="question" value=<%=q.getQuestion()%>>
    	</div>
	</div>
	<div class="row">
		<div class="col-25">
     		<label for="sector">Sector</label>
    	</div>
    	<div class="col-75">
			<input type="text" id="sector" name="sector" value=<%=q.getSector()%> readonly> 
		</div>
	</div>
	<div class="row">
		<div class="col-25">
      		<label for="recruiter">Recruiter</label>
   		</div>
		<div class="col-75">
      		<input type="text" id="recruiter" name="recruiter" value=<%=q.getRecruiter()%> readonly> 
		</div>
   </div>
   <div class="row" id="selcompany">		
    <div class="col-25">
     <label for="company">Company</label>
    </div>
    <div class="col-75">
      		<input type="text" id="company" name="company" value=<%=q.getCompany()%> readonly> 
	</div>    
  </div>
      <button type="submit" >Edit</button>
</form>

	
</div>
<br>
<%@ include file="../css/footer.jsp" %>	
</body>
</html>