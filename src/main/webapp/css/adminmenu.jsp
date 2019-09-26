<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="css/vittoriostyle.css" rel="stylesheet">
</head>
<body>

<div class="navbar">
  <a id="homelink" href="homeadmin.jsp">Home</a>
  <a id="userlink" href="UserServlet?mode=userlist">Users</a>
  <a id="companylink" href="CompanyServlet?mode=companylist">Companies</a>
  <a id="questionlink" href="QuestionServlet?mode=questionlist">Questions</a>
  <a href="LogoutServlet" id="logout">Logout</a>
  <label id="usernamelabel">${user.getUsername()}</label>
</div>

</body>
</html>