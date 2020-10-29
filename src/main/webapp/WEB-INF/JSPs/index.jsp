<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="bootstrap/css/assign.css">

<script src="bootstrap/js/jquery-1.12.4.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>

<meta charset="ISO-8859-1">
<title>Welcome</title>
</head>
<body>
	<div>
		<h2>Welcome to Elections</h2>
	</div>
	<div class="thumbnail  col-md-4 col-md-offset-4 align-middle">
		<table class="table  .table-bordered">
			<thead class="thead-dark">
				<tr>
					<th scope="col">Options</th>
				</tr>
			</thead>
			<tr>
				<td><a href="RegisterVoter" class="btn btn-block btn-primary">Register
						as voter</a></td>
			</tr>

			<tr>
				<td><a href="GenerateVoters" class="btn btn-block btn-info">Generate
						Dummy records</a></td>
			</tr>

			<tr>
				<td><a href="ShowVoters" class="btn btn-block btn-success">View
						Voters</a></td>
			</tr>

			<tr>
				<td><a href="CastVote" class="btn btn-block btn-primary">Vote</a></td>
			</tr>

			<tr>
				<td><a href="CastDummyVotes" class="btn btn-block btn-info">Assign
						Dummy Votes</a></td>
			</tr>

			<tr>
				<td><a href="ShowStats" class="btn btn-block btn-success">Show
						Statistics</a></td>
			</tr>
		</table>
	</div>
</body>
</html>