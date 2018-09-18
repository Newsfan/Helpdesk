package com.fdmgroup.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.fdmgroup.dao.DbConnection;
import com.fdmgroup.model.TicketPriority;

public class TicketPriorityDao {

	private DbConnection conn = DbConnection.getInstance();
	
	public TicketPriority create(TicketPriority priority) {
		EntityManager em = conn.getEntityManager();
		em.getTransaction().begin();
		em.persist(priority);
		em.getTransaction().commit();
		em.close();
		return priority;
	}
	
	public void delete(TicketPriority priority) {
		EntityManager em = conn.getEntityManager();
		em.getTransaction().begin();
		em.remove(em.contains(priority) ? priority : em.merge(priority));
		em.getTransaction().commit();
		em.close();
	}
	
	// problem: current db schema does not allow change of names
	public TicketPriority update(TicketPriority priority) {
		EntityManager em = conn.getEntityManager();
		em.getTransaction().begin();
		TicketPriority managedPriority = em.find(TicketPriority.class, priority.getName());
		if (!managedPriority.getNote().equals(priority.getNote())) {
			managedPriority.setNote(priority.getNote());
		}
		em.getTransaction().commit();
		em.close();
		return priority;
	}
	
	public List<TicketPriority> findAll() {
		EntityManager em = conn.getEntityManager();
		TypedQuery<TicketPriority> query = em.createNamedQuery("Priority.findAll", TicketPriority.class);
		List<TicketPriority> priorities = query.getResultList();
		em.close();
		return priorities;
	}

	public TicketPriority findByName(String name) {
		EntityManager em = conn.getEntityManager();
		TicketPriority pri = em.find(TicketPriority.class, name);
		em.close();
		return pri;
	}
}
