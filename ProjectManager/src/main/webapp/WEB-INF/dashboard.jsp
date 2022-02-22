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
<title>Dashboard</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
</head>
<body>
	<div class="container py-2">
		<div class="d-flex align-items-center">
			<div class="h3">Welcome, ${user.getFirstName()}</div>
			<a class="h3 btn btn-sm btn-danger mx-5" href="/logout">Logout</a>
		</div>
		<div class="d-flex align-items-center justify-content-between">
			<div class="h5">All Projects</div>
			<a class="h5 btn btn-sm btn-success" href="/projects/new">+ new
				project</a>
		</div>
		<div class="mb-3">
			<table class="table">
				<thead>
					<tr>
						<th scope="col">Project</th>
						<th scope="col">Team Lead</th>
						<th scope="col">Due Date</th>
						<th scope="col">Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="project" items="${allProjects}">
						<tr>
							<td><a href="/projects/${project.getId()}"><c:out
										value="${project.getTitle()}" /></a></td>
							<td><c:out value="${project.getLeader().getFirstName()}" /></td>
							<td><fmt:formatDate pattern="MMMM dd" value="${project.getDueDate()}" /></td>
							<td><a href="/projects/${project.getId()}/jointeam">Join
									team</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<div class="d-flex align-items-center justify-content-between mt-3">
			<div class="h5">Your Projects</div>
		</div>
		<div class="mb-3">
			<table class="table">
				<thead>
					<tr>
						<th scope="col">Project</th>
						<th scope="col">Team Lead</th>
						<th scope="col">Due Date</th>
						<th scope="col">Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="project" items="${user.getProjects()}">
						<tr>
							<td><a href="/projects/${project.getId()}"><c:out
										value="${project.getTitle()}" /></a></td>
							<td><c:out value="${project.getLeader().getFirstName()}" /></td>
							<td><fmt:formatDate pattern="MMMM dd" value="${project.getDueDate()}" />
								</td>
							<td><c:choose>
									<c:when
										test="${project.getLeader().getId() == sessionScope.userid}">
										<a href="/projects/${project.getId()}/edit">edit</a>
									</c:when>
									<c:otherwise>
										<a href="/projects/${project.getId()}/leaveteam">Leave
											team</a>
									</c:otherwise>
								</c:choose></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>



</body>
</html>