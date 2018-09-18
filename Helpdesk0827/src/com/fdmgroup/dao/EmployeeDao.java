package com.fdmgroup.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.fdmgroup.model.Employee;
import com.fdmgroup.model.Ticket;

public class EmployeeDao {

	private DbConnection conn = DbConnection.getInstance();
	
	public Employee create(Employee emp) {
		EntityManager em = conn.getEntityManager();
		em.getTransaction().begin();
		em.persist(emp);
		em.getTransaction().commit();
		em.close();
		return emp;
	}

	public void delete(Employee emp) {
		EntityManager em = conn.getEntityManager();
		em.getTransaction().begin();
		em.remove(em.contains(emp) ? emp : em.merge(emp));
		em.getTransaction().commit();
		em.close();
	}
	
	public Employee update(Employee emp) {
		EntityManager em = conn.getEntityManager();
		em.getTransaction().begin();
		Employee managedEmp = em.find(Employee.class, emp.getId());
		if (!managedEmp.getFirstname().equals(emp.getFirstname())) {
			managedEmp.setFirstname(emp.getFirstname());
		}
		if (!managedEmp.getLastname().equals(emp.getLastname())) {
			managedEmp.setLastname(emp.getLastname());
		}
		em.getTransaction().commit();
		em.close();
		return managedEmp;
	}
	
	public Employee findById(int id) {
		EntityManager em = conn.getEntityManager();
		Employee emp = em.find(Employee.class, id);
		em.close();
		return emp;
	}
	
	public List<Employee> findAllNonAdmins() {
		EntityManager em = conn.getEntityManager();
		TypedQuery<Employee> query = em.createNamedQuery("Employee.findAllNonAdmins", Employee.class);
		List<Employee> emps = query.getResultList();
		em.close();
		return emps;
	}
}
