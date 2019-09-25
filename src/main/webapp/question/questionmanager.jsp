<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.QuestionDTO"
	import="it.contrader.dto.UserDTO"
	import="it.contrader.dto.CompanyDTO" %>
	
	
	
	<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Question Manager</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>
<%if(((UserDTO) session.getAttribute("user")).getUsertype().equals("ADMIN")) {%>
	<%@include file="../css/adminmenu.jsp"%><%}
else {%>
	<%@include file="../css/usermenu.jsp"%><%}%>

<script>document.getElementById("questionlink").classList.add("active");</script>
<div class="main">
	<%
		List<QuestionDTO> list = (List<QuestionDTO>) request.getAttribute("list");
    	CompanyDTO company = (CompanyDTO) request.getAttribute("company");
        	%>

<br>

	<table>
		<tr>
			<th>Question</th>
			<th>Sector</th>
			<th>Recruiter</th>
			<th>Company</th>
			<th></th>
			<th></th>
		</tr>
		<%
			for (QuestionDTO u : list) {
		%>
		<tr>
			<td><a href=QuestionServlet?mode=read&id=<%=u.getId()%>>
					<%=u.getQuestion()%>
			</a></td>
			<td><%=u.getSector()%></td>
			<td><%=u.getRecruiter()%></td>
			<td><%=u.getCompany()%></td>
			<td><a href=QuestionServlet?mode=read&update=true&id=<%=u.getId()%>>Edit</a>
			</td>
			<td><a href=QuestionServlet?mode=delete&id=<%=u.getId()%>>Delete</a>
			</td>

		</tr>
		<%
			}
		%>
	</table>



<form id="floatright" action="QuestionServlet?mode=insert&recruiter=${user.getUsername()}&recruiterid=${user.getId()}&company=<%=company.getName()%>&companyid=${user.getCompanyid()}&sector=<%=company.getSector()%>" method="post">
  <div class="row">
    <div class="col-25">
      <label for="question">question</label>
    </div>
    <div class="col-75">
      <input type="text" id="question" name="question" placeholder="inserisci la domanda">
    </div>    
 </div>
<button type="submit" >Insert</button>
</form>
</div>
<br>
<%@ include file="../css/footer.jsp" %>
</body>
</html>