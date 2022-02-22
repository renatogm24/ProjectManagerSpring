<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Show project</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
</head>
<body>
	<div class="container py-2">
		<div class="d-flex align-items-center">
			<div class="h3">Project Details</div>
			<a class="h3 btn btn-sm btn-primary mx-5" href="/">Back to
				dashboard</a>
		</div>

		<div class="col-8 mb-3">
			<div class="row">
				<div class="col-6">Project</div>
				<div class="col-6">${project.getTitle()}</div>
			</div>

			<div class="row">
				<div class="col-6">Description</div>
				<div class="col-6">${project.getDescription()}</div>
			</div>

			<div class="row">
				<div class="col-6">Due Date</div>
				<div class="col-6">${project.getDueDate()}</div>
			</div>
		</div>

		<a href="/projects/${project.getId()}/tasks">See tasks</a>

		<c:choose>
			<c:when test="${project.getLeader().getId() == sessionScope.userid}">
				<div class="d-flex justify-content-end">
					<a class="btn btn-sm btn-danger"
						href="/projects/${project.getId()}/delete">Delete Project</a>
				</div>
			</c:when>
		</c:choose>


	</div>



</body>
</html>