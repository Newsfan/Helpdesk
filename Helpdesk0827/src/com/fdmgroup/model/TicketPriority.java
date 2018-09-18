package com.fdmgroup.model;

import javax.persistence.*;

@Entity
@Table(name="ticket_priority")
@NamedQueries({
	@NamedQuery(name="Priority.findAll", query="SELECT p FROM TicketPriority p")
})
public class TicketPriority {

	@Id
	@Column(name="priority_name")
	private String name;
	@Column(name="note")
	private String note;
	
	public TicketPriority() {
		super();
	}
	
	public TicketPriority(String name, String note) {
		super();
		this.name = name;
		this.note = note;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
