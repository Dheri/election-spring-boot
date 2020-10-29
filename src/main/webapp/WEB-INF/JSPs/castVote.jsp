<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="bootstrap/css/assign.css">

<script src="bootstrap/js/jquery-1.12.4.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<meta charset="ISO-8859-1">
<title>Cast Vote</title>
</head>
<body>
	<jsp:include page="navbar.jsp">
		<jsp:param name="heading" value="Register as a voter" />
	</jsp:include>

	<div class="col-md-4 col-md-offset-4 ">

		<div class="panel panel-primary">
			<div class="panel-heading">Please enter your info</div>
			<div class="panel-body">
				<form:form action="CastVote" method="POST" modelAttribute="vote">

					<div class="form-group">
						<label for="sin">SIN</label>
						<form:input type="number" required="true" name="sin"
							class="form-control" min="100000000" max="999999999"
							value="987654321" path="voter.SIN" />
					</div>

					<div class="form-group">
						<label for="party">Party</label>
						<form:select path="party" items="${vote.parties}" />
					</div>

					<div class="form-group">
						<button type="submit" class="btn btn-primary">Submit</button>
					</div>
				</form:form>

			</div>
		</div>

	</div>
</body>
</html>