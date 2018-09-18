package com.fdmgroup.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.fdmgroup.model.Employee;
import com.fdmgroup.model.User;

public class UserDao {

	private DbConnection conn = DbConnection.getInstance();
	
	public User create(User user) {
		EntityManager em = conn.getEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
		return user;
	}
	
	public void delete(User user) {
		EntityManager em = conn.getEntityManager();
		em.getTransaction().begin();
		em.remove(em.contains(user) ? user : em.merge(user));
		em.getTransaction().commit();
		em.close();
	}
	
	public User update(User user) {
		EntityManager em = conn.getEntityManager();
		em.getTransaction().begin();
		User managedUser = em.find(User.class, user.getId());
		if (!managedUser.getUsername().equals(user.getUsername())) {
			managedUser.setUsername(user.getUsername());
		}
		if (!managedUser.getPassword().equals(user.getPassword())) {
			managedUser.setPassword(user.getPassword());
		}
		
		em.getTransaction().commit();
		em.close();
		return managedUser;
	}
	
	public User findById(int id) {
		EntityManager em = conn.getEntityManager();
		em.getTransaction().begin();
		User user = em.find(User.class, id);
		return user;
	}
	
	public User findByUsername(String uname) {
		EntityManager em = conn.getEntityManager();
		em.getTransaction().begin();
		TypedQuery<User> query = em.createNamedQuery("User.findByUsername", User.class);
		query.setParameter("username", uname);
		User user = query.getSingleResult();
		return user;
	}
	
	public User findByUsernameAndPassword(String uname, String pword) {
		EntityManager em = conn.getEntityManager();
		em.getTransaction().begin();
		TypedQuery<User> query = em.createNamedQuery("User.findByUsernameAndPassword", User.class);
		query.setParameter("username", uname);
		query.setParameter("password", pword);
		try {
			User user = query.getSingleResult();
			return user;
		}
		catch (NoResultException e) {
			return null;
		}
	}
}
