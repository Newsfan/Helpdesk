package com.fdmgroup.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.fdmgroup.model.TicketStatus;

public class TicketStatusDao {

	private DbConnection conn = DbConnection.getInstance();
	public List<TicketStatus> findAll() {
		EntityManager em = conn.getEntityManager();
		TypedQuery<TicketStatus> query = em.createNamedQuery("Status.findAll", TicketStatus.class);
		List<TicketStatus> list = query.getResultList();
		em.close();
		return list;
	}
	public TicketStatus findByName(String name) {
		EntityManager em = conn.getEntityManager();
		TicketStatus status = em.find(TicketStatus.class, name);
		return status;
	}
}
