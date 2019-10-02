<!DOCTYPE html>
<html>
<head>
</head>
<body>
<div class="menu">
	<ul>
	  <li><a id="homelink" href="/Home/homeadmin">Home</a></li>
	  <li><a id="userlink" href="/User/management">Users</a></li>
	  <li><a id="recruiterlink" href="/Recruiter/management">Recruiters</a></li>
	  <li><a id="companylink" href="/Company/management">Companies</a></li>
	  <li><a id="questionlink" href="/Question/management">Questions</a></li>
	  <li class="userdropdown">
	    <a class="userdropbtn"><i class="user-icon" aria-hidden="true"></i></a>
	    <div class="dropdown-content">
	      <label>Username:</label><label class="username">${user.getUsername()}</label>
	      <label>Usertype:</label><label class="usertype">${user.getUserType()}</label>
	      <a class="logout" href="/Home/logout">Logout</a>
	    </div>	  	
	  </li>
	</ul>
</div>
</body>
</html>