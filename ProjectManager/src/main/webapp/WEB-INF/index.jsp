<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login & Register</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<style type="text/css">
body::before {
    display: block;
    content: "";
    height: 90px;
}
</style>
</head>
<body class="bg-light">
	<nav class="navbar fixed-top navbar-dark bg-dark text-light">
		<div class="container">
			<h1>Authentication</h1>
		</div>
	</nav>
	<div class="container">
		<div class="row justify-content-evenly">
			<div class="px-5 px-sm-0 col-sm-6 col-md-4 mb-5">
				<h2 class="text-center mb-3">Register</h2>

				<form:form
					class="row g-2 justify-content-center bg-dark text-light py-4 px-3 rounded"
					action="/register" method="post" modelAttribute="newUser">
					<form:label for="first_name" path="firstName" class="form-label">First Name:</form:label> 
					<form:errors path="firstName" class="text-danger" />
					<form:input
						type="text" path="firstName" class="form-control" id="first_name" />
						
						
					<form:label for="last_name" path="lastName" class="form-label">Last Name:</form:label> 
					<form:errors path="lastName" class="text-danger" />
					<form:input
						type="text" path="lastName" class="form-control" id="last_name" />
						
						
					<form:label for="email" path="email" class="form-label">Email:</form:label> 
					<form:errors path="email" class="text-danger" />
					<form:input
						type="email" path="email" class="form-control" id="email" /> 
						
					<form:label
						for="password" path="password" class="form-label">Password:</form:label> 
						<form:errors path="password" class="text-danger" />
						<form:input
						type="password" path="password" class="form-control" id="password" />
						
					<form:label for="repeat_password" path="confirm" class="form-label">Repeat
						password:</form:label> 
						<form:errors path="confirm" class="text-danger" />
						<form:input type="password" path="confirm"
						class="form-control" id="repeat_password" /> 
						
						<input
						class="btn btn-outline-light mt-3" type="submit" value="Send" />
				</form:form>
			</div>
			<div class="px-5 px-sm-0 col-sm-6 col-md-4">
				<h2 class="text-center mb-3">Login</h2>

				<form:form
					class="row g-2 justify-content-center bg-secondary text-light py-4 px-3 rounded"
					action="/login" method="post" modelAttribute="newLogin">
					<form:label for="email" path="email" class="form-label">Email:</form:label> 
					<form:errors path="email" class="text-danger" />
					<form:input
						type="email" path="email" class="form-control" id="email" /> 
					<form:errors path="password" class="text-danger" />
					<form:label
						for="password" path="password" class="form-label">Password:</form:label> 						
					<form:input
						type="password" path="password" class="form-control" id="password" />					
					
					<input class="btn btn-outline-light mt-3" type="submit"
						value="Send" />
				</form:form>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="{{ url_for('static', filename='js/script.js') }}"></script>
</body>
</html>