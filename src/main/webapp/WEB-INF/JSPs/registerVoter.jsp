<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/bootstrap/css/assign.css">

<script src="/bootstrap/js/jquery-1.12.4.min.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		var today = new Date();
		today.setFullYear(today.getFullYear() - 18);
		dobPicker.max = today.toISOString().split("T")[0];
		today.setFullYear(today.getFullYear() - 100);
		dobPicker.min = today.toISOString().split("T")[0];

	});
</script>
<meta charset="ISO-8859-1">
<title>Welcome</title>
</head>
<body>

	<jsp:include page="navbar.jsp">
		<jsp:param name="heading" value="Register as a voter" />
	</jsp:include>

	<div class="col-md-4 col-md-offset-4 ">

		<div class="panel panel-primary">
			<div class="panel-heading">Please enter your info</div>
			<div class="panel-body">
				<form:form action="${dest}" method="POST" modelAttribute="voter">

					<div class="form-group">
						<label for="Name">Name </label>
						<div class="form-row">
							<div class="form-group col-md-6">
								<form:input type="text" class="form-control"
									placeholder="First name" path="firstName" />
							</div>
							<div class="form-group col-md-6">
								<form:input type="text" class="form-control"
									placeholder="Last name" path="lastName" />
							</div>
						</div>
					</div>


					<div class="form-group">
						<label for="sin">SIN</label>
						<form:input type="number" required="true" class="form-control"
							min="100000000" max="999999999" readonly="${disable}" path="SIN" />
					</div>
					<div class="form-group">
						<label for="dob">Date of Birth</label>
						<form:input type="date" class="form-control" id="dobPicker"
							path="birthdate" />
					</div>


					<div class="form-group">
						<label for="Address">Address </label>
						<div class="form-row">
							<div class="form-group col-md-4">
								<form:input type="text" class="form-control"
									placeholder="Number" path="address.number" />
							</div>
							<div class="form-group col-md-8">
								<form:input type="text" class="form-control"
									placeholder="Street" path="address.street" />
							</div>
							<div class="form-group col-md-4">
								<form:input type="text" class="form-control" placeholder="City"
									path="address.city" />
							</div>
							<div class="form-group col-md-4">
								<form:input type="text" class="form-control"
									placeholder="Province" path="address.province" />

							</div>
							<div class="form-group col-md-4">
								<form:input type="text" class="form-control"
									placeholder="Postal" path="address.postal" />

							</div>
							<div class="form-group col-md-6">
								<input class="form-control" type="text" placeholder="Canada"
									readonly>

							</div>

						</div>
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