<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login - Helpdesk</title>
<link rel="stylesheet" type="text/css" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous"/>
<link rel="stylesheet" type="text/css" href="resources/css/grid.css"/>
<link rel="stylesheet" type="text/css" href="resources/css/style.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
	<div class="vertically-centered-wrapper">
		<div class="vertically-centered">
			<div class="login-form">
				<div class="row">
					<h2>Login</h2>
				</div>
				<c:if test="${invalid}" >
					<span class="login-info"> Username or password incorrect </span>
				</c:if>
				<sf:form modelAttribute="user" method="POST" action="login/authenticate">
					<div class="row">
						<div class="col col-3">
							<sf:label path="username">Username</sf:label>
						</div>
						<div class="col col-9 col-last">
							<sf:input type="text" path="username" />
						</div>
					</div>
					<div class="row">
						<div class="col col-3">
							<sf:label path="password">Password</sf:label>
						</div>
						<div class="col col-9 col-last">
							<sf:input type="password" path="password" />
						</div>
					</div>
					<div class="row submit">
						<input type="submit" value="Log In"/>
					</div>
				</sf:form>
			</div>
		</div>
	</div>
</body>
</html>