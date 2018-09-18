package com.fdmgroup.model;

import java.time.LocalDate;
import java.util.*;

import javax.persistence.*;

//import com.fdmgroup.dao.TicketTypeDao;

@Entity
@Table(name="ticket")
@NamedQueries({
	@NamedQuery(name="Ticket.findAll", query="SELECT t FROM Ticket t ORDER BY t.date DESC"),
	@NamedQuery(name="Ticket.findByStatus", query="SELECT t FROM Ticket t WHERE t.status = :status"),
	@NamedQuery(name="Ticket.filter", query="SELECT t FROM Ticket t WHERE t.type IN :typeList "
			+ "AND t.status IN :statusList AND t.priority IN :priorityList "
			+ "AND t.date >= :fromDate AND t.date <= :toDate ORDER BY t.date ASC")
})
public class Ticket {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_ticket_id")
	@SequenceGenerator(name="seq_ticket_id",sequenceName="seq_ticket_id", allocationSize=1)
	@Column(name="ticket_id")
	private int id;
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	@ManyToOne
	@JoinColumn(name="type_name")
	private TicketType type;
	@ManyToOne
	@JoinColumn(name="priority_name")
	private TicketPriority priority;
	@Column(name="subject")
	private String subject;
	@Column(name="content")
	private String message;
	@ManyToOne
	@JoinColumn(name="status_name", insertable=false, updatable=true)
	private TicketStatus status;
	@Column(name="time_created", columnDefinition = "DATE DEFAULT SYSDATE", insertable=false, updatable=false)
	private LocalDate date;
	
	public Ticket() {}
	
	public Ticket(int id, Customer customer, TicketType type, TicketPriority priority, String subject,
			String message) {
		super();
		this.id = id;
		this.customer = customer;
		this.type = type;
		this.priority = priority;
		this.subject = subject;
		this.message = message;
	}
	
	public int getId() {
		return id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public TicketType getType() {
		return type;
	}
	public TicketPriority getPriority() {
		return priority;
	}
	public String getSubject() {
		return subject;
	}
	public String getMessage() {
		return message;
	}
	public TicketStatus getStatus() {
		return status;
	}
	

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setType(TicketType type) {
		this.type = type;
	}

	public void setPriority(TicketPriority priority) {
		this.priority = priority;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setStatus(TicketStatus status) {
		this.status = status;
	}

	public boolean addEmployees(Employee...emps) {
		return false;
	}
	
	public void take() {
		
	}
	
	public void resolve() {
		
	}
	
	public boolean isTaken() {
		return false;
	}
	
	public boolean isResolved() {
		return false;
	}
}
