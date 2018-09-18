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
<link rel="stylesheet" href="/Helpdesk0827/resources/css/grid.css"/>
<link rel="stylesheet" href="/Helpdesk0827/resources/css/style.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="/Helpdesk0827/js/main.js"></script>
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
			<h2 class="result-message">Sorry, you have no access.</h2>
			<p class="result-details">Reasons could be: wrong username or password, or you are not an admin.</p>
			<a href="${backUrl}"> Back to previous page</a>
		</div>
	</div>

</body>
</html>