<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FDM - Feedback</title>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous"/>
<link rel="stylesheet" href="resources/css/grid.css"/>
<link rel="stylesheet" href="resources/css/style.css"/>
</head>
<body>
	<div class="feedback-form-container">
		<div class="row">
			<h2>Feedback </h2>
		</div>
		<sf:form modelAttribute="feedback" method="POST" action="feedback/submit">
			<div class="row">
				<sf:label path="firstname">First name</sf:label>
				<sf:input type="text" path="firstname" /><br>
			</div>
			<div class="row">
				<sf:label path="lastname">Last name</sf:label>
				<sf:input type="text" path="lastname" /><br>
			</div>
			<div class="row">
				<sf:label path="email">Email</sf:label>
				<sf:input type="text" path="email" /><br>
			</div>
			<div class="row">
				<sf:label path="phone">Phone</sf:label>
				<sf:input type="text" path="phone" /><br>
			</div>
			<div class="row">
				<sf:label path="type">Type</sf:label>
				<sf:select path="type" items="${types}">
			</sf:select><br>
			</div>
			<div class="row">
				<sf:label path="priority">Priority</sf:label>
				<sf:select path="priority" items="${priorities}">
			</sf:select><br>
			</div>
			<div class="row">
				<sf:label path="subject">Subject</sf:label>
				<sf:input type="text" size="60" path="subject" /><br>
			</div>
			<div class="row">
				<sf:label path="content">Message</sf:label><br>
				&nbsp;<sf:textarea rows="10" cols="90" path="content" />
			</div>
			<div class="row">
				<input type="submit" value="Submit"/>
			</div>
		</sf:form>
	</div>
</body>
</html>