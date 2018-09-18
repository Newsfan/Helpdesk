package com.fdmgroup.controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.fdmgroup.dao.AdminDao;
import com.fdmgroup.dao.EmployeeDao;
import com.fdmgroup.dao.UserDao;
import com.fdmgroup.model.Admin;
import com.fdmgroup.model.Employee;
import com.fdmgroup.model.User;

@Controller
public class SettingsController {

	public class AddUserForm {
		private Employee newUser;
		private String newUserPword;
		private boolean admin;
		private String adminPword;
		public Employee getNewUser() {
			return newUser;
		}
		public void setNewUser(Employee newUser) {
			this.newUser = newUser;
		}
		public boolean getAdmin() {
			return admin;
		}
		public void setAdmin(boolean isAdmin) {
			this.admin = isAdmin;
		}
		public String getAdminPword() {
			return adminPword;
		}
		public void setAdminPword(String adminPword) {
			this.adminPword = adminPword;
		}
		public String getNewUserPword() {
			return newUserPword;
		}
		public void setNewUserPword(String newUserPword) {
			this.newUserPword = newUserPword;
		}
	}
	
	public class RemoveUserForm {
		private LinkedList<String> userList = new LinkedList<String>();
		private String adminPword;
		public LinkedList<String> getUserList() {
			return userList;
		}
		public void setUserList(LinkedList<String> userList) {
			this.userList = userList;
		}
		public String getAdminPword() {
			return adminPword;
		}
		public void setAdminPword(String adminPword) {
			this.adminPword = adminPword;
		}
	}

	@RequestMapping("/settings")
	public String renderSettingsMain() {
		return "settingsMain";
	}
	
	@RequestMapping(value="/settings/addUser")
	public String addUser(Model model) {
		model.addAttribute("addUserForm", new AddUserForm());
		return "settingAddUser";
	}
	
	@RequestMapping(value="/settings/addUser/submit")
	public String processAddUser(AddUserForm form, @SessionAttribute Employee EMPLOYEE, Model model) {
		model.addAttribute("backUrl", "/Helpdesk0827/settings/addUser");
		UserDao userDao = new UserDao();
		User adminUser = userDao.findByUsernameAndPassword(EMPLOYEE.getUsername(), form.getAdminPword());
		if (adminUser == null) {
			return "noPrivilege";
		}
		AdminDao aDao = new AdminDao();
		Admin admin = aDao.findById(adminUser.getId());
		if (admin == null) {
			return "noPrivilege";
		}
		
		Employee emp = form.getNewUser();
		
		// register new employee/admin
		if (form.getAdmin()) {
			Admin newAdmin = new Admin(emp);
			newAdmin = aDao.create(newAdmin);
			User newUser = new User();
			newUser.setId(newAdmin.getId());
			newUser.setUsername(newAdmin.getUsername());
			newUser.setPassword(form.getNewUserPword());
			userDao.create(newUser);
		}
		else {
			EmployeeDao empDao = new EmployeeDao();
			emp = empDao.create(emp);
			User newUser = new User();
			newUser.setId(emp.getId());
			newUser.setUsername(emp.getUsername());
			newUser.setPassword(form.getNewUserPword());
			userDao.create(newUser);
		}
		
		return "success";
	}
	
	@RequestMapping(value="/settings/removeUser")
	public String removeUser(Model model, @SessionAttribute Employee EMPLOYEE) {
		EmployeeDao eDao = new EmployeeDao();
		// find all employees that are not admin
		List<Employee> emps = eDao.findAllNonAdmins();
		// find all admin (except yourself)
		AdminDao aDao = new AdminDao();
		List<Admin> admins = aDao.findAll();
		for (Admin a : admins) {
			if (a.getId() == EMPLOYEE.getId()) {
				admins.remove(a);
				break;
			}
		}
		// add to lists, model
		model.addAttribute("employees", emps);
		model.addAttribute("admins", admins);
		model.addAttribute("userToRemove", new RemoveUserForm());
		return "settingRemoveUser";
	}
	
	@RequestMapping(value="/settings/removeUser/submit")
	public String processRemoveUser(RemoveUserForm form, @SessionAttribute Employee EMPLOYEE, Model model) {
		model.addAttribute("backUrl", "/Helpdesk0827/settings/removeUser");
		// verify admin's credentials
		UserDao uDao = new UserDao();
		User adminUser = uDao.findByUsernameAndPassword(EMPLOYEE.getUsername(), form.getAdminPword());
		if (adminUser == null) {
			return "noPrivilege";
		}
		AdminDao aDao = new AdminDao();
		Admin admin = aDao.findById(adminUser.getId());
		if (admin == null) {
			return "noPrivilege";
		}
		
		EmployeeDao eDao = new EmployeeDao();
		List<String> ids = form.getUserList();
		for (String idStr : ids) {
			int id = Integer.parseInt(idStr);
			User tmpUser = uDao.findById(id);
			uDao.delete(tmpUser);
			Admin tmpAdmin = aDao.findById(id);
			if (tmpAdmin == null) {
				Employee tmpEmp = eDao.findById(id);
				if (tmpEmp != null) {
					eDao.delete(tmpEmp);
				}
			}
			else {
				aDao.delete(tmpAdmin);
				eDao.delete(tmpAdmin);
			}
		}
		
		return "success";
	}
}
