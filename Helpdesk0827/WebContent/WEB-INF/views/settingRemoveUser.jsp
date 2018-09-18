<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Settings - Helpdesk</title>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous"/>
<link rel="stylesheet" href="../resources/css/grid.css"/>
<link rel="stylesheet" href="../resources/css/style.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="../js/main.js"></script>
</head>
<body onload="setActiveTab('#settingsButton')">
	<div class="nav-bar">
		<div class="logo-container">
			<img src="/Helpdesk0827/resources/images/fdm-logo-anim.gif"/>
		</div>
		<div class="nav-tabs">
			<div class="nav-tab-icon-container">
				<a class="nav-tab-left" id="ticketsButton" href="/Helpdesk0827/tickets">
					<i class="fas fa-ticket-alt"></i>
				</a>
			</div>
			<div class="nav-tab-icon-container">
				<a class="nav-tab-left" id="statsButton" href="/Helpdesk0827/stats">
					<i class="fas fa-chart-pie"></i>
				</a>
			</div>
			<div class="nav-tab-icon-container">
				<a class="nav-tab-left" id="settingsButton" href="/Helpdesk0827/settings">
					<i class="fas fa-cog"></i>
				</a>
			</div>
		</div>
	</div>
	
	<div class="main-container">
		<div class="settings-main-container">
			<div class="row form-title">
				<h2>Remove employee</h2>
			</div>
			<sf:form modelAttribute="userToRemove" action="removeUser/submit" method="POST">
				<div class="row">
					<sf:label path="adminPword">Please enter your password again:</sf:label>
				</div>
				<div class="row">
					<sf:password path="adminPword"></sf:password>
				</div>
				<div class="row">
					Admins
				</div>
				<c:forEach items="${admins}" var="admin">
					<div class="row">
						<sf:checkbox path="userList" label="${admin.id}, ${admin.username}, ${admin.firstname} ${employee.lastname}" value="${admin.id}"/>
					</div>
				</c:forEach>
				<div class="row">
					Employees
				</div>
				<c:forEach items="${employees}" var="employee">
					<div class="row">
						<sf:checkbox path="userList" label="${employee.id}, ${employee.username}, ${employee.firstname} ${employee.lastname}" value="${employee.id}"/>
					</div>
				</c:forEach>
				<div class="row">
					<input type="submit" value="Submit">
				</div>
			</sf:form>
		</div>
	</div>
</body>
</html>