package com.fdmgroup.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.fdmgroup.model.Ticket;
import com.fdmgroup.model.TicketPriority;
import com.fdmgroup.model.TicketStatus;
import com.fdmgroup.model.TicketType;

public class TicketDao {

	private DbConnection conn = DbConnection.getInstance();
	
	public Ticket create(Ticket priority) {
		EntityManager em = conn.getEntityManager();
		em.getTransaction().begin();
		em.persist(priority);
		em.getTransaction().commit();
		em.close();
		return priority;
	}
	
	public void delete(Ticket ticket) {
		EntityManager em = conn.getEntityManager();
		em.getTransaction().begin();
		em.remove(em.contains(ticket) ? ticket : em.merge(ticket));
		em.getTransaction().commit();
		em.close();
	}
	
	// problem: current db schema does not allow change of names
	public Ticket update(Ticket ticket) {
		EntityManager em = conn.getEntityManager();
		em.getTransaction().begin();
		Ticket managedTicket = em.find(Ticket.class, ticket.getId());
		if (!managedTicket.getStatus().equals(ticket.getStatus())) {
			managedTicket.setStatus(ticket.getStatus());
		}
		em.getTransaction().commit();
		em.close();
		return managedTicket;
	}
	
	public List<Ticket> findAll() {
		EntityManager em = conn.getEntityManager();
		TypedQuery<Ticket> query = em.createNamedQuery("Ticket.findAll", Ticket.class);
		List<Ticket> tickets = query.getResultList();
		em.close();
		return tickets;
	}

	public Ticket findById(int id) {
		EntityManager em = conn.getEntityManager();
		Ticket pri = em.find(Ticket.class, id);
		em.close();
		return pri;
	}

	public List<Ticket> findByStatus(TicketStatus s) {
		EntityManager em = conn.getEntityManager();
		TypedQuery<Ticket> query = em.createNamedQuery("Ticket.findByStatus", Ticket.class);
		query.setParameter("status", s);
		List<Ticket> tickets = query.getResultList();
		em.close();
		return tickets;
	}
	
	public List<Ticket> filter(List<TicketType> types, List<TicketStatus> statuses, List<TicketPriority> priorities, LocalDate fromDate, LocalDate toDate) {
		EntityManager em = conn.getEntityManager();
		TypedQuery<Ticket> query = em.createNamedQuery("Ticket.filter", Ticket.class);
		query.setParameter("typeList", types);
		query.setParameter("statusList", statuses);
		query.setParameter("priorityList", priorities);
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate.plusDays(1));
		List<Ticket> tickets = query.getResultList();
		em.close();
		
		return tickets;
	}
}
