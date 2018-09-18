package com.fdmgroup.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.dao.TicketDao;
import com.fdmgroup.dao.TicketPriorityDao;
import com.fdmgroup.dao.TicketStatusDao;
import com.fdmgroup.dao.TicketTypeDao;
import com.fdmgroup.model.Ticket;
import com.fdmgroup.model.TicketPriority;
import com.fdmgroup.model.TicketStatus;
import com.fdmgroup.model.TicketType;

@Controller
public class TicketsController {
	
	public class FilterOptions {
		private String type;
		private String tstatus;
		private String priority;
		private String keyWords;
		private String fromDate;
		private String toDate;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getTstatus() {
			return tstatus;
		}
		public void setTstatus(String tstatus) {
			this.tstatus = tstatus;
		}
		public String getPriority() {
			return priority;
		}
		public void setPriority(String priority) {
			this.priority = priority;
		}
		public String getKeyWords() {
			return keyWords;
		}
		public void setKeyWords(String keyWords) {
			this.keyWords = keyWords;
		}
		public String getFromDate() {
			return fromDate;
		}
		public void setFromDate(String fromDate) {
			this.fromDate = fromDate;
		}
		public String getToDate() {
			return toDate;
		}
		public void setToDate(String toDate) {
			this.toDate = toDate;
		}
	}
	
	@RequestMapping("/tickets")
	public String renderTicketsMain(Model model) {
		TicketDao dao = new TicketDao();
		List<Ticket> tickets = dao.findAll();
		model.addAttribute("tickets", tickets);
		Map<String, List<Ticket>> ticketsByStatus = new HashMap<String, List<Ticket>>();
		TicketStatusDao statusDao = new TicketStatusDao();
		List<TicketStatus> status = statusDao.findAll();
		model.addAttribute("statuses", status);
		for (TicketStatus s : status) {
			List<Ticket> ticketsOfThisStatus = dao.findByStatus(s);
			ticketsByStatus.put(s.getName(), ticketsOfThisStatus);
		}
		TicketPriorityDao priDao = new TicketPriorityDao();
		List<TicketPriority> priorities = priDao.findAll();
		model.addAttribute("priorities", priorities);
		TicketTypeDao typeDao = new TicketTypeDao();
		List<TicketType> types = typeDao.findAll();
		model.addAttribute("types", types);
		model.addAttribute("ticketsByStatus", ticketsByStatus);
		model.addAttribute("filterOptions", new FilterOptions());
		
		return "allTicketsByStatus";
	}
	
//	@RequestMapping(value = "/tickets/changeStatus", method=RequestMethod.GET)
//	public String changeTicketStatus(int ticketId, String statusName) {
//
//		// db
//		TicketStatusDao tsDao = new TicketStatusDao();
//		TicketStatus status = tsDao.findByName(statusName);
//		TicketDao tDao = new TicketDao();
//		Ticket ticket = tDao.findById(ticketId);
//		ticket.setStatus(status);
//		ticket = tDao.update(ticket);
//		return "redirect:/tickets";
//
//	}
	
	@RequestMapping(value = "/tickets/changeStatus", method=RequestMethod.POST)
	public String changeTicketStatusPost(@RequestParam int ticketId, 
			@RequestParam String targetStatus, @RequestParam String srcUrl, HttpServletRequest req) {
		// db
		TicketStatusDao tsDao = new TicketStatusDao();
		TicketStatus status = tsDao.findByName(targetStatus);
		TicketDao tDao = new TicketDao();
		Ticket ticket = tDao.findById(ticketId);
		ticket.setStatus(status);
		ticket = tDao.update(ticket);
		// back to original url
		String ctxPath = req.getContextPath();
		String path = srcUrl.replaceFirst(ctxPath, "");
		return "redirect:"+path;
	}
	
	@RequestMapping(value="/tickets/filter", method=RequestMethod.GET)
	public String filterTickets(@ModelAttribute FilterOptions ops, Model model) {
		List<TicketType> selectedTypes = new ArrayList<TicketType>();
		List<TicketStatus> selectedStatuses = new ArrayList<TicketStatus>();
		List<TicketPriority> selectedPriorities = new ArrayList<TicketPriority>();
		TicketTypeDao typeDao = new TicketTypeDao();
		TicketStatusDao statusDao = new TicketStatusDao();
		TicketPriorityDao priDao = new TicketPriorityDao();
		
		if (ops.getType() == null) {
			selectedTypes = typeDao.findAll();
		}
		else {
			String[] typeStrs = ops.getType().split(",");
			for (String s : typeStrs) {
				selectedTypes.add(typeDao.findByName(s));
			}
		}
		
		if (ops.getPriority() == null) {
			selectedPriorities = priDao.findAll();
		}
		else {
			String[] priStrs = ops.getPriority().split(",");
			for (String s : priStrs) {
				selectedPriorities.add(priDao.findByName(s));
			}
		}
		
		if (ops.getTstatus() == null) {
			selectedStatuses = statusDao.findAll();
		}
		else {
			String[] statusStrs = ops.getTstatus().split(",");
			for (String s : statusStrs) {
				selectedStatuses.add(statusDao.findByName(s));
			}
		}

		LocalDate fromDate = ops.getFromDate().equals("")?LocalDate.of(0001,01,01):LocalDate.parse(ops.getFromDate());
		LocalDate toDate = ops.getToDate().equals("")?LocalDate.of(9999,12,30):LocalDate.parse(ops.getToDate());
		
		TicketDao dao = new TicketDao();
		List<Ticket> filteredResults = dao.filter(selectedTypes, selectedStatuses, selectedPriorities, fromDate, toDate);
		Map<String, List<Ticket>> ticketsByStatus = new HashMap<String, List<Ticket>>();
		for (TicketStatus s : selectedStatuses) {
			List<Ticket> ticketsOfThisStatus = new ArrayList<Ticket>();
			for (Ticket t : filteredResults) {
				if (t.getStatus().equals(s)) {
					ticketsOfThisStatus.add(t);
				}
			}
			ticketsByStatus.put(s.getName(), ticketsOfThisStatus);
		}
		model.addAttribute("tickets", filteredResults);
		model.addAttribute("ticketsByStatus", ticketsByStatus);
		model.addAttribute("selectedStatuses", selectedStatuses);
		model.addAttribute("selectedTypes", selectedTypes);
		model.addAttribute("selectedPriorities", selectedPriorities);
		model.addAttribute("selectedFromDate", ops.getFromDate());
		model.addAttribute("selectedToDate", ops.getToDate());

		List<TicketStatus> status = statusDao.findAll();
		model.addAttribute("statuses", status);
		List<TicketType> types = typeDao.findAll();
		model.addAttribute("types", types);
		List<TicketPriority> priorites = priDao.findAll();
		model.addAttribute("priorities", priorites);
		
		return "ticketsFiltered";
	}
	
}
