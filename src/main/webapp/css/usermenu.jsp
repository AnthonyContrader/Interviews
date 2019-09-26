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
  <a id="homelink" href="homeuser.jsp">Home</a>
  <a id="questionlink" href="QuestionServlet?mode=questionlist">Questions</a>
  <a href="LogoutServlet" id="logout">Logout</a>
  <label id="usernamelabel">${user.getUsername()}</label>
</div>

</body>
</html>