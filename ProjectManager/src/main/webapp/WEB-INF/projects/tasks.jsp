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
			<div class="h3">Project: ${project.getTitle()}</div>
			<a class="h3 btn btn-sm btn-primary mx-5" href="/">Back to
				dashboard</a>
		</div>

		<form:form
				class="row col-5 mx-auto my-4 g-2 justify-content-center bg-dark text-light py-4 px-3 rounded"
				action="/projects/${project.getId()}/addTask" method="post" modelAttribute="task">

				<form:label for="ticket" path="ticket" class="form-label">Add a task ticket for this team:</form:label>
				<form:errors path="ticket" class="text-danger" />
				<form:input type="text" path="ticket" class="form-control" id="ticket" />
				
				
				<button class="btn btn-primary mt-4" type="submit">Submit</button>
			</form:form>

		<div class="col-6 my-3">
			<c:forEach var="task" items="${project.getTasks()}">
			
			<h4>Added by ${task.getAuthor().getFirstName()} at <fmt:formatDate type = "both" 
         dateStyle = "short" timeStyle = "short" value = "${task.getCreatedAt()}" /> </h4>
			<p class="mb-3">${task.getTicket()}</p>

			</c:forEach>
		</div>

	</div>



</body>
</html>