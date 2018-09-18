package com.fdmgroup.controllers;

import java.time.LocalDate;
import java.time.Year;
import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.dao.AdminDao;
import com.fdmgroup.dao.EmployeeDao;
import com.fdmgroup.dao.TicketDao;
import com.fdmgroup.dao.TicketPriorityDao;
import com.fdmgroup.dao.TicketStatusDao;
import com.fdmgroup.dao.TicketTypeDao;
import com.fdmgroup.dao.UserDao;
import com.fdmgroup.model.Admin;
import com.fdmgroup.model.Employee;
import com.fdmgroup.model.Ticket;
import com.fdmgroup.model.TicketPriority;
import com.fdmgroup.model.TicketStatus;
import com.fdmgroup.model.TicketType;
import com.fdmgroup.model.User;

@Controller
public class MainController {
	
	@RequestMapping("/main")
	public String renderMainView(Model model) {
		return "redirect:/tickets";
	}
	@RequestMapping("/stats")
	public String renderStatsMain() {
		return "statsMain";
	}
}
