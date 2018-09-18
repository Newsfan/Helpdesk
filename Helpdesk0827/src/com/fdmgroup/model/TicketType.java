package com.fdmgroup.model;

import javax.persistence.*;

@Entity
@Table(name="ticket_type")
@NamedQueries({
	@NamedQuery(name="Type.findAll", query="SELECT t FROM TicketType t")
})
public class TicketType {

	@Id
	@Column(name="type_name")
	private String name;
	@Column(name="note")
	private String note;
	
	public TicketType() {
		super();
	}
	public TicketType(String name, String note) {
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
