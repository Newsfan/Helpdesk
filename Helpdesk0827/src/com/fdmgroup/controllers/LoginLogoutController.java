package com.fdmgroup.controllers;

import java.security.Key;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.fdmgroup.dao.EmployeeDao;
import com.fdmgroup.dao.UserDao;
import com.fdmgroup.model.*;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Controller
public class LoginLogoutController {

	@RequestMapping("/login") 
	public String renderLogin(Model model, @RequestParam(required=false) Boolean invalid) {
		model.addAttribute("user", new User());
		if (invalid !=null) {
			model.addAttribute("invalid", invalid);
		}
		return "login";
	}
	
	@RequestMapping(value="/login/authenticate", method=RequestMethod.POST)
	public String loginAuthentication(User user, Model model, HttpSession session, HttpServletResponse response) {
		UserDao dao = new UserDao();
		User target = dao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		if (target == null) {
			return "redirect:/login?invalid=true";
		}
		// find employee and add to model
		EmployeeDao eDao = new EmployeeDao();
		Employee EMPLOYEE = eDao.findById(target.getId());
		session.setAttribute("EMPLOYEE", EMPLOYEE);
		// set token
		Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
		session.setAttribute("key", key);
		String jws = Jwts.builder().setIssuer("Helpdesk").setSubject(user.getUsername()).signWith(key).compact();
		Cookie tokenCookie = new Cookie("token", jws);
		tokenCookie.setMaxAge(60*60);
		tokenCookie.setPath("/Helpdesk0827");
		tokenCookie.setHttpOnly(true);
		response.addCookie(tokenCookie);
		return "redirect:/main";
	}
	
	@RequestMapping("/logout")
	public String logout(Employee EMPLOYEE, HttpSession session, HttpServletRequest request) {
		Cookie[] cookies =  request.getCookies();
		for (Cookie c : cookies) {
			if (c.getName().equals("token")) {
				c.setMaxAge(-1);
			}
		}
		session.removeAttribute("EMPLOYEE");
		session.invalidate();
		return "redirect:/login";
	}
}
