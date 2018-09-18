package com.fdmgroup.controllers;

import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fdmgroup.dao.CustomerDao;
import com.fdmgroup.dao.TicketDao;
//import com.fdmgroup.dao.TicketDao;
import com.fdmgroup.dao.TicketPriorityDao;
import com.fdmgroup.dao.TicketTypeDao;
import com.fdmgroup.model.*;

@Controller
public class FeedbackController {

	public class FeedbackForm {
		private String firstname;
		private String lastname;
		private String email;
		private String phone;
		private String type;
		private String priority;
		private String subject;
		private String content;
		private String tag;
		public String getFirstname() {
			return firstname;
		}
		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}
		public String getLastname() {
			return lastname;
		}
		public void setLastname(String lastname) {
			this.lastname = lastname;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getPriority() {
			return priority;
		}
		public void setPriority(String priority) {
			this.priority = priority;
		}
		public String getSubject() {
			return subject;
		}
		public void setSubject(String subject) {
			this.subject = subject;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public String getTag() {
			return tag;
		}
		public void setTag(String tag) {
			this.tag = tag;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
	}
	
	@RequestMapping(value="/feedback")
	public String renderFeedbackPage(Model model) {
		model.addAttribute("feedback", new FeedbackForm());
		TicketTypeDao typeDao = new TicketTypeDao();
		TicketPriorityDao priDao = new TicketPriorityDao();
		List<TicketType> types = typeDao.findAll();
		List<TicketPriority> priorities = priDao.findAll();
		model.addAttribute("types", types);
		model.addAttribute("priorities", priorities);
		return "customerInterface";
	}
	
	@RequestMapping(value="/feedback/submit", method=RequestMethod.POST)
	public String submitFeedback(@ModelAttribute FeedbackForm feedback) {
		
		// after finished debugging, surround this with a try-catch block
		
		// change feedback form to customer and tickets
		Customer customer = new Customer();
		customer.setFname(feedback.getFirstname());
		customer.setLname(feedback.getLastname());
		customer.setEmail(feedback.getEmail());
		customer.setPhone(feedback.getPhone());
		CustomerDao cDao = new CustomerDao();
		cDao.create(customer);
		Ticket ticket = new Ticket();
		ticket.setCustomer(customer);
		TicketTypeDao typeDao = new TicketTypeDao();
		ticket.setType(typeDao.findByName(feedback.type));
		TicketPriorityDao priDao = new TicketPriorityDao();
		ticket.setPriority(priDao.findByName(feedback.priority));
		ticket.setSubject(feedback.subject);
		ticket.setMessage(feedback.content);
		TicketDao tDao = new TicketDao();
		tDao.create(ticket);
		
		return "confirmFeedback";
	}
}
