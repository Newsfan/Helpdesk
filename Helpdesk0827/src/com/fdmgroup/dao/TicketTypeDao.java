package com.fdmgroup.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.fdmgroup.model.TicketType;

public class TicketTypeDao {

	private DbConnection conn = DbConnection.getInstance();
	
	public TicketType create(TicketType type) {
		EntityManager em = conn.getEntityManager();
		em.getTransaction().begin();
		em.persist(type);
		em.getTransaction().commit();
		em.close();
		return type;
	}
	
	public void delete(TicketType type) {
		EntityManager em = conn.getEntityManager();
		em.getTransaction().begin();
		em.remove(em.contains(type) ? type : em.merge(type));
		em.getTransaction().commit();
		em.close();
	}
	
	// problem: current db schema does not allow change of names
	public TicketType update(TicketType type) {
		EntityManager em = conn.getEntityManager();
		em.getTransaction().begin();
		TicketType managedType = em.find(TicketType.class, type.getName());
		if (!managedType.getNote().equals(type.getNote())) {
			managedType.setNote(type.getNote());
		}
		em.getTransaction().commit();
		em.close();
		return type;
	}
	
	public List<TicketType> findAll() {
		EntityManager em = conn.getEntityManager();
		TypedQuery<TicketType> query = em.createNamedQuery("Type.findAll", TicketType.class);
		List<TicketType> types = query.getResultList();
		em.close();
		return types;
	}

	public TicketType findByName(String typeName) {
		EntityManager em = conn.getEntityManager();
		TicketType type = em.find(TicketType.class, typeName);
		em.close();
		return type;
	}
}
