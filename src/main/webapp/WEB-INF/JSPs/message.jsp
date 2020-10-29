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
<title>${tittle}</title>
</head>
<body class="assign-center">
	<div>
		<div class="alert alert-${alert_class} centered-div " role="alert">
			<h4 class="alert-heading">${tittle}</h4>
			<p>${messsage}</p>
			<hr>
			<a class="btn btn-primary" href="${loc}"> Go Back</a>
		</div>
	</div>
</body>
</html>