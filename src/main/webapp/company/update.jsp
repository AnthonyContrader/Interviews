<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.List"
    import="it.contrader.dto.CompanyDTO"
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link href="../css/vittoriostyle.css" rel="stylesheet">
	<title>Update page</title>

	
	<!-- SCRIPT -->
	
	<script type="text/javascript">
		function CheckSectors(val){
			var sectorSelecter=document.getElementById('selecter');
			var inputText=document.getElementById('inputText');
			if(val=='#') {
				sectorSelecter.style.display='none';
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
	
	<%@ include file="../css/header.jsp" %>
	
	
	<!-- NAVBAR -->
	
	<%@ include file="../css/adminmenu.jsp"%>
	<script>document.getElementById("companylink").classList.add("active");</script>
	<br>


	<!-- BODY -->
	
	<div class="main">
		<%CompanyDTO company = (CompanyDTO) request.getAttribute("company");
		  List<String> sectorDistinctList = (List<String>) request.getAttribute("sectorDistinctList");
		%>
		<form id="floatleft" action="/Company/update?update=true&id=<%=company.getId()%>" method="post">
			<div class="row">
				<div class="col-25">
					<label for="name">Name</label>
				</div>
				<div class="col-75">
					<input type="text" id="name" name="name" value="<%=company.getName()%>">
				</div>
			</div>
			<div class="row">
				<div class="col-25">
					<label for="address">Address</label>
				</div>
				<div class="col-75">
					<input type="text" id="address" name="address" value="<%=company.getAddress()%>"> 
				</div>
			</div>
			<div class="row">
				<div class="col-25">
					<label for="city">City</label>
				</div>
				<div class="col-75">
					<input type="text" id="city" name="city" value="<%=company.getCity()%>"> 
				</div>
			</div>
			<div id="selecter" class="row">
				<div class="col-25">
					<label for="sector">Sector</label>
				</div>
				<div class="col-75">
					<select id="sector" name="sector" onchange='CheckSectors(this.value);'> 
						<option>choose a sector</option>
						<%for (String sector : sectorDistinctList) {%>
							<option value=<%=sector%>><%=sector%></option>
						<%}%>
						<option value="#">Others...</option>
					</select>
				</div>
			</div>
			<div id="inputText" class="row" style='display:none;'>
				<div class="col-25">
					<label for="otherSector" id="sectorLabel">Sector</label>
				</div>
				<div class="col-75">
					<input type="text" id="otherSector" name="otherSector" placeholder="insert sector"/>
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