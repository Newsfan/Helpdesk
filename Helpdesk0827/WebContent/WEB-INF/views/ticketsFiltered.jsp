<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Helpdesk</title>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous"/>
<link rel="stylesheet" href="../resources/css/grid.css"/>
<link rel="stylesheet" href="../resources/css/style.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="../js/main.js"></script>
<script src="../js/tickets.js"></script>
</head>
<body onload="setActiveTab('#ticketsButton')">
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
		<div class="message-area">
			<div class="nav-status">
				<button id="all-messages-list-tab" onclick="setTicketStatusActive('all-messages-list')">
					All
				</button>
				<c:forEach items="${selectedStatuses}" var="status">
					<button id="${status.name}-messages-list-tab" onclick="setTicketStatusActive('${status.name}-messages-list')">
						${status.name}
					</button>
				</c:forEach>
			</div>
			
			<div class="messages-list all-messages-list">
				<c:forEach items="${tickets}" var="ticket">
					<div class="message ${ticket.status.name}" id="ticket${ticket.id}">
						<div class="ticket-header"> 
							<span>[${ticket.status.name}][${ticket.priority.name}][${ticket.type.name}]</span>
							<span class="ticket-subject"> 
								${ticket.subject}
							</span>
							<div class="ticket-list-item-options">
								<!-- <a class="option-take" href="tickets/changeStatus?ticketId=${ticket.id}&statusName=Taken">Take</a>
								<a class="option-resolve" href="tickets/changeStatus?ticketId=${ticket.id}&statusName=Resolved">Resolve</a>
								<a class="option-untake" href="tickets/changeStatus?ticketId=${ticket.id}&statusName=New">Untake</a>
								<a class="option-unresolve" href="tickets/changeStatus?ticketId=${ticket.id}&statusName=Taken">Unresolve</a>-->
								<a class="option-take" href="javascript:changeTicketStatus(${ticket.id},'Taken')">Take</a>
								<a class="option-resolve" href="javascript:changeTicketStatus(${ticket.id},'Resolved')">Resolve</a>
								<a class="option-untake" href="javascript:changeTicketStatus(${ticket.id},'New')">Untake</a>
								<a class="option-unresolve" href="javascript:changeTicketStatus(${ticket.id},'Taken')">Unresolve</a>
								<a class="ticket-short" href="javascript:expandTicket(${ticket.id},'all')">Details</a>
								<a class="ticket-detailed" href="javascript:collapseTicket(${ticket.id}, 'all')">Collapse</a>
							</div>
						</div>
						<div class="ticket-list-item-content">
							<div class="ticket-short">
								<span class="date">${ticket.date}</span>&nbsp;<span class="message-content-short">${ticket.message}</span>
							</div>
							<div class="ticket-detailed">
								Ticket ID: ${ticket.id}<br>
								Time: ${ticket.date}<br>
								Customer: ${ticket.customer.id}<br>
								&nbsp;&nbsp;&nbsp;&nbsp;${ticket.customer.fname} ${ticket.customer.lname}<br>
								&nbsp;&nbsp;&nbsp;&nbsp;${ticket.customer.phone} ${ticket.customer.email}<br>
								<br>
								${ticket.message}
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<c:forEach items="${selectedStatuses}" var="status">
				<div class="messages-list ${status.name}-messages-list">
					<c:forEach items="${ticketsByStatus[status.name]}" var="ticket">
						<div class="message ${status.name}" id="ticket${ticket.id}">
							<div class="ticket-header"> 
								<span>[${ticket.priority.name}][${ticket.type.name}]</span>
								<span class="ticket-subject"> 
									${ticket.subject}
								</span>
								<div class="ticket-list-item-options">
									<!-- <a class="option-take" href="tickets/changeStatus?ticketId=${ticket.id}&statusName=Taken">Take</a>
									<a class="option-resolve" href="tickets/changeStatus?ticketId=${ticket.id}&statusName=Resolved">Resolve</a>
									<a class="option-untake" href="tickets/changeStatus?ticketId=${ticket.id}&statusName=New">Untake</a>
									<a class="option-unresolve" href="tickets/changeStatus?ticketId=${ticket.id}&statusName=Taken">Unresolve</a>-->
									<a class="option-take" href="javascript:changeTicketStatus(${ticket.id},'Taken')">Take</a>
									<a class="option-resolve" href="javascript:changeTicketStatus(${ticket.id},'Resolved')">Resolve</a>
									<a class="option-untake" href="javascript:changeTicketStatus(${ticket.id},'New')">Untake</a>
									<a class="option-unresolve" href="javascript:changeTicketStatus(${ticket.id},'Taken')">Unresolve</a>
									<a class="ticket-short" href="javascript:expandTicket(${ticket.id}, '${ticket.status.name}')">Details</a>
									<a class="ticket-detailed" href="javascript:collapseTicket(${ticket.id}, '${ticket.status.name}')">Collapse</a>
								</div>
							</div>
							<div class="ticket-list-item-content">
								<div class="ticket-short">
									<span class="date">${ticket.date}</span>&nbsp;<span class="message-content-short">${ticket.message}</span>
								</div>
								<div class="ticket-detailed">
									Ticket ID: ${ticket.id}<br>
									Time: ${ticket.date}<br>
									Customer: ${ticket.customer.id}<br>
									&nbsp;&nbsp;&nbsp;&nbsp;${ticket.customer.fname} ${ticket.customer.lname}<br>
									&nbsp;&nbsp;&nbsp;&nbsp;${ticket.customer.phone} ${ticket.customer.email}<br>
									<br>
									${ticket.message}
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</c:forEach>
		</div>
		<!-- End: singleTicketView/TicketListView -->
		<div class="filter-container">
			<!-- <h3>Filtered by:</h3>
			<div class="row">
				Types:
				<c:forEach items="${selectedTypes}" var="type">${type.name},&nbsp;</c:forEach>
			</div>
			<div class="row">
				Statuses<br>
				<c:forEach items="${selectedStatuses}" var="status">${status.name},&nbsp;</c:forEach>
			</div>
			<div class="row">
				Priorities<br>
				<c:forEach items="${selectedPriorities}" var="priority">${priority.name},&nbsp;</c:forEach>
			</div>
			<!-- <div class="row">
				Keywords<br>
				<c:forEach items="${keyWords}" var="keyWord">${keyWord},&nbsp;</c:forEach>
			</div>
			<div class="row">
				Date<br>
				From ${selectedFromDate} to ${selectedToDate}
			</div>-->
			<sf:form class="filter" modelAttribute="filterOptions" method="GET" action="/Helpdesk0827/tickets/filter" target="_blank">
				<h3>Filter</h3>
				<div class="row">
					<sf:label path="type">By type</sf:label><br>
					<c:forEach items="${types}" var="type">
						<sf:checkbox path="type" label="${type.name}" value="${type.name}"/><br>
					</c:forEach>
				</div>
				<div class="row">
					<sf:label path="tstatus">By status</sf:label><br>
					<c:forEach items="${statuses}" var="status">
						<sf:checkbox path="tstatus" label="${status.name}" value="${status.name}"/><br>
					</c:forEach>
				</div>
				<div class="row">
					<sf:label path="priority">By priority</sf:label><br>
					<c:forEach items="${priorities}" var="priority">
						<sf:checkbox path="priority" label="${priority.name}" value="${priority.name}"/><br>
					</c:forEach>
				</div>
				<!-- <div class="row">
					<sf:label path="keyWords">By text</sf:label><br>
					<sf:input type="text" path="keyWords"/>
				</div>-->
				<div class="row">
					<div class="row">
						By date
					</div>
					<div class="row">
						<sf:label path="fromDate">From</sf:label>
						<sf:input type="date" path="fromDate"/>
					</div>
					<div class="row">
						<sf:label path="toDate">To</sf:label>
						<sf:input type="date" path="toDate"/>
					</div>
				</div>
				<div class="row">
					<input type="submit" value="Submit"/>
				</div>
			</sf:form>
		</div>
	</div>
</body>
</html>