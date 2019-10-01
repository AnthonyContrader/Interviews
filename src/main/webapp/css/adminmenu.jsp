<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
<div class="menu">
	<ul>
	  <li><a id="homelink" href="homeadmin.jsp">Home</a></li>
	  <li><a id="userlink" href="UserServlet?mode=userlist">Users</a></li>
	  <li><a id="companylink" href="CompanyServlet?mode=companylist">Companies</a></li>
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