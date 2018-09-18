package com.fdmgroup.filters;

import java.io.IOException;
import java.security.Key;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fdmgroup.model.Employee;

import io.jsonwebtoken.Jwts;

@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {

	// Future use?
//	private ServletContext context;
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest sreq, ServletResponse sres, FilterChain fc)
			throws IOException, ServletException {
		HttpServletRequest hreq = (HttpServletRequest) sreq;
		HttpServletResponse hres = (HttpServletResponse) sres;
		String reqUri = hreq.getRequestURI();
		if ((hreq.getRequestURI()).matches("\\/Helpdesk0827\\/resources.*|\\/Helpdesk0827\\/js.*")) {
			fc.doFilter(sreq, sres);
			return;
		}
		if ((hreq.getRequestURI()).matches("\\/Helpdesk0827\\/login.*|\\/Helpdesk0827\\/feedback.*")) {
			fc.doFilter(sreq, sres);
			return;
		}
		HttpSession session = hreq.getSession(false);
		if (session == null) {
			hres.sendRedirect(hreq.getContextPath()+"/login");
			return;
		}
		Cookie[] cookies = hreq.getCookies();
		for (Cookie c : cookies) {
			if (c.getName().equals("token")) {
				String jws = c.getValue();
				Key key = (Key) session.getAttribute("key");
				Employee EMPLOYEE = (Employee) session.getAttribute("EMPLOYEE");
				if (EMPLOYEE == null) {
					hres.sendRedirect(hreq.getContextPath()+"/login");
					return;
				}
				String subject = "";
				try {
					subject = Jwts.parser().setSigningKey(key).parseClaimsJws(jws).getBody().getSubject();
				}
				catch (Exception e) {
					hres.sendRedirect(hreq.getContextPath()+"/login");
					return;
				}
				if (subject.equals(EMPLOYEE.getUsername())) {
					fc.doFilter(sreq, sres);
					return;
				}
				else {
					hres.sendRedirect(hreq.getContextPath()+"/login");
					return;
				}
			}
		}
		hres.sendRedirect(hreq.getContextPath()+"/login");
		return;
		
	}

	@Override
	public void init(FilterConfig conf) throws ServletException {
		
	}
	
}

