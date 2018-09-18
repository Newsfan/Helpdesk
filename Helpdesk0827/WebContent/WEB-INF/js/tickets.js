/**
 * 
 */


function setTicketStatusActive(statusListName) {
	$(".messages-list").each(function(){
		$(this).hide();
	});
	$("."+statusListName).show();
	$(".nav-status button").each(function(){
		$(this).removeClass("active");
	});
	$("#"+statusListName+"-tab").addClass("active");
	window.location.hash = statusListName;
}

function expandTicket(ticketId, status) {
	$("."+status+"-messages-list"+" #ticket"+ticketId+" .ticket-list-item-options .ticket-short").hide();
	$("."+status+"-messages-list"+" #ticket"+ticketId+" .ticket-list-item-content .ticket-short").hide();
	$("."+status+"-messages-list"+" #ticket"+ticketId+" .ticket-list-item-options .ticket-detailed").show();
	$("."+status+"-messages-list"+" #ticket"+ticketId+" .ticket-list-item-content .ticket-detailed").show();
}

function collapseTicket(ticketId, status) {
	$("."+status+"-messages-list"+" #ticket"+ticketId+" .ticket-list-item-options .ticket-detailed").hide();
	$("."+status+"-messages-list"+" #ticket"+ticketId+" .ticket-list-item-content .ticket-detailed").hide();
	$("."+status+"-messages-list"+" #ticket"+ticketId+" .ticket-list-item-options .ticket-short").show();
	$("."+status+"-messages-list"+" #ticket"+ticketId+" .ticket-list-item-content .ticket-short").show();
}

function changeTicketStatus(ticketId, targetStatus) {
	var url = (window.location.pathname + window.location.search + window.location.hash);
	post('/Helpdesk0827/tickets/changeStatus', {ticketId: ticketId, targetStatus: targetStatus, srcUrl: url});
}


$(function() {
	var hash = window.location.hash;
	var listClassName = "all-messages-list";
	if (hash != "") {
		listClassName = hash.substring(1);
	}
	setTicketStatusActive(listClassName);
	$(".ticket-detailed").each(function() {
		$(this).hide();
	});
	$(".ticket-short").each(function(){
		$(this).show();
	});
	$(".message-content-short").each(function(){
		$(this).text($(this).text().substr(0,300)+"...");
	});
	$(".message.New").each(function(){
		$(this).children(".ticket-header").children(".ticket-list-item-options").children(".option-untake").hide();
		$(this).children(".ticket-header").children(".ticket-list-item-options").children(".option-unresolve").hide();
		$(this).children(".ticket-header").children(".ticket-list-item-options").children(".option-take").show();
		$(this).children(".ticket-header").children(".ticket-list-item-options").children(".option-resolve").show();
	});
	$(".message.Taken").each(function(){
		$(this).children(".ticket-header").children(".ticket-list-item-options").children(".option-take").hide();
		$(this).children(".ticket-header").children(".ticket-list-item-options").children(".option-unresolve").hide();
		$(this).children(".ticket-header").children(".ticket-list-item-options").children(".option-untake").show();
		$(this).children(".ticket-header").children(".ticket-list-item-options").children(".option-resolve").show();
	});
	$(".message.Resolved").each(function(){
		$(this).children(".ticket-header").children(".ticket-list-item-options").children(".option-take").hide();
		$(this).children(".ticket-header").children(".ticket-list-item-options").children(".option-resolve").hide();
		$(this).children(".ticket-header").children(".ticket-list-item-options").children(".option-untake").show();
		$(this).children(".ticket-header").children(".ticket-list-item-options").children(".option-unresolve").show();
	});
});