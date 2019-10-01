<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="css/vittoriostyle.css" rel="stylesheet">
</head>
<body>
<div class="menu">
	<ul>
	  <li><a id="homelink" href="homeuser.jsp">Home</a></li>
	  <li><a id="questionlink" href="QuestionServlet?mode=questionlist">Questions</a></li>
	  <li class="userdropdown">
	    <a class="userdropbtn"><i class="user-icon" aria-hidden="true"></i></a>
	    <div class="dropdown-content">
	      <label>Username:</label><label class="username">${user.getUsername()}</label>
	      <label>Usertype:</label><label class="usertype">${user.getUsertype()}</label>
	      <a class="logout" href="LogoutServlet">Logout</a>
	    </div>	  	
	  </li>
	</ul>
</div>
</body>
</html>