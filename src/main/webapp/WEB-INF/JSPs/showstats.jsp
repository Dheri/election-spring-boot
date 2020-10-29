<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="bootstrap/css/assign.css">

<script src="bootstrap/js/jquery-1.12.4.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<meta charset="ISO-8859-1">
<title>Statistics</title>
<script src="bootstrap/js/canvasjs.min.js"></script>

<script type="text/javascript">
	window.onload = function() {
		func_partyStats();
		func_voteStats();
		func_ageStats();
	}
	<jsp:include page="pieChart.jsp">
		<jsp:param name="chartName" value="partyStats" />
		<jsp:param name="titleText"
			value="Percent  of votes received by each party" />
		<jsp:param name="chartJSON" value="${partyStatsJson}" />
	</jsp:include>
	
	<jsp:include page="pieChart.jsp">
		<jsp:param name="chartName" value="voteStats" />
		<jsp:param name="titleText" value="Percent of voters voted" />
		<jsp:param name="chartJSON" value="${voteStatsJson}" />
	</jsp:include>
	
	<jsp:include page="pieChart.jsp">
		<jsp:param name="chartName" value="ageStats" />
		<jsp:param name="titleText"
			value="Percent of votes casted by each age group" />
		<jsp:param name="chartJSON" value="${ageStatsJson}" />
	</jsp:include>
</script>

</head>
<body>
	<jsp:include page="navbar.jsp">
		<jsp:param name="heading" value="Voting Statistics" />
	</jsp:include>
	<div
		class="thumbnail   bg-info  text-white col-md-6 col-md-offset-3 align-middle  ">
		<div id="partyStats_Container" class="thumbnail m-3 centered-div "
			style="height: 380px; width: 100%;"></div>
		<hr>
		<div id="voteStats_Container" class="thumbnail m-3  centered-div"
			style="height: 380px; width: 100%;"></div>
		<hr>
		<div id="ageStats_Container" class="thumbnail m-5 centered-div"
			style="height: 370px; width: 100%;"></div>
	</div>
</body>
</html>
