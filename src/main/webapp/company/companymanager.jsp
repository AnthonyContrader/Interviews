<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.CompanyDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Company Manager</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>
<%@ include file="../css/adminmenu.jsp"%>
<script>document.getElementById("companylink").classList.add("active");</script>
<div class="main">
	<%
		List<CompanyDTO> list = (List<CompanyDTO>) request.getAttribute("list");
	%>

<br>

	<table>
		<tr>
			<th>Name</th>
			<th>Address</th>
			<th>City</th>
			<th></th>
			<th></th>
		</tr>
		<%
			for (CompanyDTO u : list) {
		%>
		<tr>
			<td><a href=CompanyServlet?mode=read&id=<%=u.getId()%>>
					<%=u.getName()%>
			</a></td>
			<td><%=u.getAddress()%></td>
			<td><%=u.getCity()%></td>
			<td><a href=CompanyServlet?mode=read&update=true&id=<%=u.getId()%>>Edit</a>
			</td>
			<td><a href=CompanyServlet?mode=delete&id=<%=u.getId()%>>Delete</a>
			</td>

		</tr>
		<%
			}
		%>
	</table>



<form id="floatright" action="CompanyServlet?mode=insert" method="post">
  <div class="row">
    <div class="col-25">
      <label for="user">Name</label>
    </div>
    <div class="col-75">
      <input type="text" id="company" name="name" placeholder="inserisci il nome">
    </div>
  </div>
  <div class="row">
    <div class="col-25">
     <label for="pass">Address</label>
    </div>
    <div class="col-75">
      <input type="text" id="address" name="address" placeholder="inserisci l'indirizzo"> 
    </div>
  </div>
  <div class="row">
    <div class="col-25">
      <label for="type">City</label>
    </div>
      <div class="col-75">
       <input type="text" id="city" name="city" placeholder="inserisci la citt�"> 
      </div>
   </div>
   <button type="submit" >Insert</button>
</form>

</div>
<br>
<%@ include file="../css/footer.jsp" %>
</body>
</html>