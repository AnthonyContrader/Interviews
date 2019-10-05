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
			var label=document.getElementById('sectorLabel');
			var inputText=document.getElementById('otherSector');
			if(val=='#') {
				label.style.display='block';
				inputText.style.display='block';
				inputText.required = true;
			} else {
				label.style.display='none';
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
		<%CompanyDTO c = (CompanyDTO) request.getAttribute("allCompanyDTO");
		  List<String> allDistinctSectorList = (List<String>) request.getAttribute("allDistinctSector");
		%>
		<form id="floatleft" action="/Company/update?update=true&id=<%=c.getId()%>" method="post">
			<div class="row">
				<div class="col-25">
					<label for="name">Name</label>
				</div>
				<div class="col-75">
					<input type="text" id="name" name="name" value="<%=c.getName()%>">
				</div>
			</div>
			<div class="row">
				<div class="col-25">
					<label for="address">Address</label>
				</div>
				<div class="col-75">
					<input type="text" id="address" name="address" value="<%=c.getAddress()%>"> 
				</div>
			</div>
			<div class="row">
				<div class="col-25">
					<label for="city">City</label>
				</div>
				<div class="col-75">
					<input type="text" id="city" name="city" value="<%=c.getCity()%>"> 
				</div>
			</div>
			<div class="row">
				<div class="col-25">
					<label for="sector">Sector</label>
				</div>
				<div class="col-75">
					<select name="sector" onchange='CheckSectors(this.value);'> 
						<%for (String sector : allDistinctSectorList) {%>
						<option value=<%=sector%>><%=sector%></option>
					<%}%>
						<option value="#">Others...</option>
					</select>
				</div>
				<div class="col-25">
					<label for="otherSector" id="sectorLabel" style='display:none;'>Sector</label>
				</div>
				<div class="col-75">
					<input type="text" id="otherSector" name="otherSector" placeholder="insert sector" style='display:none;'/>
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