package com.fdmgroup.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.fdmgroup.model.Admin;
import com.fdmgroup.model.Employee;

import java.util.List;

public class AdminDao {

	private DbConnection conn = DbConnection.getInstance();

	// NEEDS VERIFICATION!!! MAYNOT WORK!!!	
	// Sql Inheritence and JPA?
	public Admin create(Admin newAdmin) {
		EntityManager em = conn.getEntityManager();
		em.getTransaction().begin();
		em.persist(newAdmin);
		em.getTransaction().commit();
		em.close();
		return newAdmin;
	}
	
	public void delete(Admin admin) {
		EntityManager em = conn.getEntityManager();
		em.getTransaction().begin();
		em.remove(em.contains(admin)? admin : em.merge(admin));
		em.getTransaction().commit();
		em.close();
	}
	
	public Admin findById(int id) {
		EntityManager em = conn.getEntityManager();
		Admin admin = em.find(Admin.class, id);
		em.close();
		return admin;
	}

	public List<Admin> findAll() {
		EntityManager em = conn.getEntityManager();
		TypedQuery<Admin> query = em.createNamedQuery("Admin.findAll", Admin.class);
		List<Admin> admins = query.getResultList();
		em.close();
		return admins;
	}
}
