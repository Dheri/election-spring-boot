<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/bootstrap/css/assign.css">

<script src="/bootstrap/js/jquery-1.12.4.min.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
<meta charset="ISO-8859-1">
<title>Voters</title>
</head>
<body>
<body>
	<jsp:include page="navbar.jsp">
		<jsp:param name="heading" value="Registered Voter List" />
	</jsp:include>

	<table class="table">
		<thead>
			<tr>
				<th scope="col">#</th>
				<th scope="col">First</th>
				<th scope="col">Last</th>
				<th scope="col">DOB</th>
				<th scope="col">SIN</th>
				<th scope="col">Street</th>
				<th scope="col">City</th>
				<th scope="col">Province</th>
				<th scope="col">Postal</th>
				<th scope="col">Voted</th>
				<th scope="col"></th>
				<th scope="col"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="voter" items="${voters}" varStatus="loop">

				<c:choose>
					<c:when test="${ empty voter.vote}">
						<c:set var="vote" value="Not Voted" />
					</c:when>
					<c:otherwise>
						<c:set var="vote" value="${ voter.vote.party}" />
					</c:otherwise>
				</c:choose>

				<tr>
					<th scope="row">${loop.count}</th>
					<td>${voter.firstName}</td>
					<td>${voter.lastName}</td>
					<td>${voter.birthdate}</td>
					<td>${voter.SIN}</td>
					<td>${voter.address.number}<span> </span>${voter.address.street}</td>
					<td>${voter.address.city}</td>
					<td>${voter.address.province}</td>
					<td>${voter.address.postal}</td>
					<td>${vote}</td>
					<td><a href="<c:url value="/EditVoter/${voter.SIN}" />"
						class="btn btn-block btn-info">Edit</a></td>
					<td><a href="<c:url value="DeleteVoter/${voter.SIN}" />"
						class="btn btn-block btn-danger">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>