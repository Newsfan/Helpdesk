//package com.fdmgroup.aspects;
//
//import java.io.IOException;
//import java.lang.reflect.Method;
//import java.security.Key;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.fdmgroup.model.Employee;
//
//import io.jsonwebtoken.Jwts;
//
//@Aspect
//@Component
//public class AuthenticationAspect {
//
//	@Autowired
//	private HttpSession session;
//	@Autowired
//	private HttpServletRequest request;
//	@Autowired
//	private HttpServletResponse response;
//	
////	"@annotation(org.springframework.web.bind.annotation.RequestMapping)"
////	"execution(* com.fdmgroup.controllers.MainController.*(..)) "
////			+ "|| execution(* com.fdmgroup.controllers.TicketsController.*(..))"
////			+ "|| execution(* com.fdmgroup.controllers.SettingsController.*(..))"
//	
//	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
//	public void authenticationPointcut() {}
//	
//	@Around("authenticationPointcut()")
//	public Object authenticationAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//		System.out.println("auth:"+proceedingJoinPoint.toString());
//		MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
//		Method method = methodSignature.getMethod();
//		String[] requestPath = method.getAnnotation(RequestMapping.class).value();
//		if (requestPath[0].matches("/login.* | /feedback.*")) {
//			return proceedingJoinPoint.proceed();
//		}
////		if (requestMapping.path()[0].matches("/login.* | /feedback.*")) {
////			proceedingJoinPoint.proceed();
////		}
//		else {
//			Cookie[] cookies = request.getCookies();
//			for (Cookie c : cookies) {
//				if (c.getName().equals("token")) {
//					String jws = c.getValue();
//					Key key = (Key) session.getAttribute("key");
//					Employee EMPLOYEE = (Employee) session.getAttribute("employee");
//					if (Jwts.parser().setSigningKey(key).parseClaimsJws(jws).getBody().getSubject().equals(EMPLOYEE.getUsername())) {
//						return proceedingJoinPoint.proceed();
//					}
//					else {
//						response.sendRedirect("/login");
//						break;
//					}
//				}
//			}
//			return null;
//		}
//		
//	}
//}
