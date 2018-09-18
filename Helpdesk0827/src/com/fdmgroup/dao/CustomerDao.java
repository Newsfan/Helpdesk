package com.fdmgroup.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.fdmgroup.model.Customer;

public class CustomerDao {

	private DbConnection conn;
	
	public CustomerDao() {
		conn = DbConnection.getInstance();
	}
	
	public Customer create(Customer customer) {
		EntityManager em = conn.getEntityManager();
		em.getTransaction().begin();
		em.persist(customer);
		em.getTransaction().commit();
		em.close();
		return customer;
	}
	
	public void delete(Customer customer) {
		EntityManager em = conn.getEntityManager();
		em.getTransaction().begin();
		em.remove(em.contains(customer) ? customer : em.merge(customer));
		em.getTransaction().commit();
		em.close();
	}
	
	public Customer update(Customer customer) {
		EntityManager em = conn.getEntityManager();
		Customer managedCustomer = em.find(Customer.class, customer.getId());
		em.getTransaction().begin();
		if (customer.getFname() != managedCustomer.getFname()) {
			managedCustomer.setFname(customer.getFname());
		}
		if (customer.getLname() != managedCustomer.getLname()) {
			managedCustomer.setLname(customer.getLname());
		}
		if (customer.getEmail() != managedCustomer.getEmail()) {
			managedCustomer.setEmail(customer.getEmail());
		}
		if (customer.getPhone() != managedCustomer.getPhone()) {
			managedCustomer.setPhone(customer.getPhone());
		}
		em.getTransaction().commit();
		em.close();
		return customer;
	}
	
	public List<Customer> findAll() {
		EntityManager em = conn.getEntityManager();
		TypedQuery<Customer> query = em.createNamedQuery("Customer.findAll", Customer.class);
		List<Customer> customers = query.getResultList();
		em.close();
		return customers;
	}
	

	public List<Customer> findById() {
		EntityManager em = conn.getEntityManager();
		TypedQuery<Customer> query = em.createNamedQuery("Customer.findById", Customer.class);
		List<Customer> customers = query.getResultList();
		em.close();
		return customers;
	}
}
